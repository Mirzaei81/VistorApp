package org.visitor.Service.view;

import static android.content.ContentValues.TAG;
import static android.view.View.GONE;
import static android.view.View.VISIBLE;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Spinner;

import androidx.appcompat.app.AppCompatActivity;
import androidx.coordinatorlayout.widget.CoordinatorLayout;

import com.google.android.material.snackbar.Snackbar;
import com.google.gson.Gson;

import org.alarmamir.R;
import org.visitor.Api;
import org.visitor.Room.MyRoomDatabase;
import org.visitor.Service.presenter.ResultConfigPresenter;
import org.visitor.Service.presenter.ResultMoshtariPresenter;
import org.visitor.Service.presenter.model.ConfigResponse;
import org.visitor.Service.presenter.model.Markaz;
import org.visitor.Service.presenter.model.Moshtari;
import org.visitor.Service.presenter.model.MoshtariResponse;
import org.visitor.Service.presenter.model.User;
import org.visitor.Service.presenter.model.UserConfig;
import org.visitor.Tools.Databace.DataSaver;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ConfigActivity extends AppCompatActivity {
    private Snackbar snackbar;
    private float SelectedYear;
    private String SelectedDaftar;
    private String SelectedCompany;
    private String SelectedVisitor;
    private DataSaver dataSaver;
    private Api busApi;
    private ArrayList<Float> yearItems;
    private ArrayList<String> dafterItems;
    private ArrayList<String> companyItems;
    private ArrayList<String> markazItem;
    private ArrayList<String> vistiorNames = new ArrayList<>();
    private ArrayList<Moshtari> vistiorItem;
    private MyRoomDatabase mRoom;
    private ImageView submit;
    private  Spinner markaz;
    private  Spinner visitorSpinner;
    private ProgressBar progressBar;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_config);
        init();
    }
    public void init(){
        Bundle b =getIntent().getExtras();
        dataSaver = new DataSaver(this);
        busApi = new Api(this,dataSaver);
        if (b == null) {
            Log.v("Config","Config Detail not Found");
            return;
        }
        Boolean rememberMe = b.getBoolean("RememberMe");
        ArrayList<UserConfig> serverDetail = (ArrayList<UserConfig>) b.get(UserConfig.class.getName());
        ArrayList<Markaz> markazs = (ArrayList<Markaz>) b.get(Markaz.class.getName());
        float loginId = (float) b.get(User.class.getName());
        assert loginId !=0.0f;
        assert serverDetail != null;
        assert markazs != null;
        progressBar = findViewById(R.id.progressbar);
        progressBar.setVisibility(VISIBLE);
        visitorSpinner = findViewById(R.id.visitor);
        yearItems = new ArrayList<>();
        dafterItems  = new ArrayList<>();
        companyItems = new ArrayList<>();
        markazItem = new ArrayList<>();
        vistiorItem = new ArrayList<>();

        for (Markaz markaz:markazs){
            markazItem.add(markaz.zName);
        }
        mRoom = MyRoomDatabase.getAppDatabase(this);
        List<Moshtari> moshtaris  = mRoom.moshtariDao().getAll();
        vistiorItem = (ArrayList<Moshtari>) moshtaris;
        if(!moshtaris.isEmpty()){
            for(Moshtari m : moshtaris){
                vistiorNames.add(m.mName);
            }
            initVisitorSpinner();
        }
        busApi.getMoshtaris(resultPresenterGetMoshtaries);
        for (UserConfig uc:serverDetail) {
            yearItems.add(uc.YYear);
            dafterItems.add(uc.YDaftar);
            companyItems.add(uc.YCompany);
        }
        CoordinatorLayout view = findViewById(R.id.parentView);
        snackbar = Snackbar.make(view,"",10000);
        submit = findViewById(R.id.submit);
        submit.setOnClickListener(view1 -> {
            progressBar.setVisibility(VISIBLE);
            ResultConfigPresenter resultConfigPresenter = new ResultConfigPresenter() {
                @Override
                public void onErrorServer(String e) {
                    runOnUiThread(()->{
                    });
                }

                @Override
                public void onErrorInternetConnection() {
                    runOnUiThread(()->{
                        snackbar.setText("Check you're internet connection");
                        snackbar.show();
                    });
                }

                @Override
                public void onFinish(String response) {

                    runOnUiThread(()->{
                        progressBar.setVisibility(GONE);
                    });
                    ConfigResponse config = new  Gson().fromJson(response, ConfigResponse.class);
                    config.markaz =markazs.get(markaz.getSelectedItemPosition()).zCode;
                    config.loginId =(int)loginId;
                    config.visitorName = vistiorItem.get(visitorSpinner.getSelectedItemPosition()).getmName();
                    config.mCode = vistiorItem.get(visitorSpinner.getSelectedItemPosition()).getmCode();
                    dataSaver.setConfig(config);
                    Intent intent = new Intent(ConfigActivity.this,MainActivity.class);
                    Bundle bundle = new Bundle();
                    bundle.putBoolean("RememberMe",rememberMe);
                    intent.putExtras(bundle);
                    startActivity(intent);
                    finish();
                }
            };
            busApi.getDbName(SelectedYear,SelectedDaftar,SelectedCompany,resultConfigPresenter);
        });
        initYearSpinner();
        initDaftarSpinner();
        initCompanySpinner();
        initMarkazSpinner();


    }

    public void initMarkazSpinner(){
        markaz=  findViewById(R.id.markaz);
        ArrayAdapter<String> markazAdapter = new ArrayAdapter<String>(ConfigActivity.this, android.R.layout.simple_spinner_dropdown_item,markazItem);
        markazAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        markaz.setAdapter(markazAdapter);
        ItemSelect markazItemSelect = new ItemSelect(DropDowns.markaz);
        markaz.setOnItemSelectedListener(markazItemSelect);
    }
    public void initCompanySpinner(){
        Spinner company=  findViewById(R.id.company);
        ArrayAdapter<String> companyAdapter = new ArrayAdapter<String>(ConfigActivity.this, android.R.layout.simple_spinner_dropdown_item, companyItems);
        companyAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        company.setAdapter(companyAdapter);
        ItemSelect companyItemSelect = new ItemSelect(DropDowns.company);
        company.setOnItemSelectedListener(companyItemSelect);
    }
    public void  initDaftarSpinner(){
        Spinner daftar=  findViewById(R.id.daftar);
        ArrayAdapter<String> daftarAdapter = new ArrayAdapter<String>(ConfigActivity.this, android.R.layout.simple_spinner_dropdown_item, dafterItems);
        daftarAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        daftar.setAdapter(daftarAdapter);
        ItemSelect daftarItemSelect = new ItemSelect(DropDowns.daftar);
        daftar.setOnItemSelectedListener(daftarItemSelect);
    }
    public void  initYearSpinner(){
        Spinner year =  findViewById(R.id.year);
        ArrayAdapter<Float> year1 = new ArrayAdapter<Float>(ConfigActivity.this, android.R.layout.simple_spinner_dropdown_item, yearItems);
        year1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        year.setAdapter(year1);
        ItemSelect yearItemSelect = new ItemSelect(DropDowns.year);
        year.setOnItemSelectedListener(yearItemSelect);
    }

    public void  initVisitorSpinner(){
        Spinner year =  findViewById(R.id.visitor);
        ArrayAdapter<String> year1 = new ArrayAdapter<String>(ConfigActivity.this, android.R.layout.simple_spinner_dropdown_item, vistiorNames);
        year1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        year.setAdapter(year1);
        ItemSelect yearItemSelect = new ItemSelect(DropDowns.visitor);
        year.setOnItemSelectedListener(yearItemSelect);
    }

    enum DropDowns {
        year,daftar,company,markaz,visitor
    }

    private class ItemSelect extends Activity implements AdapterView.OnItemSelectedListener{
        DropDowns dropDowns;
        public ItemSelect(DropDowns d){
            dropDowns = d;
        }
        @Override
        public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
            switch (dropDowns){
                case year:
                    ConfigActivity.this.SelectedYear = (float)adapterView.getItemAtPosition(i);
                    break;
                case daftar:
                    ConfigActivity.this.SelectedDaftar= (String)adapterView.getItemAtPosition(i);
                    break;
                case company:
                    ConfigActivity.this.SelectedCompany= (String)adapterView.getItemAtPosition(i);
                    break;
                case  visitor:
                    ConfigActivity.this.SelectedVisitor= (String)adapterView.getItemAtPosition(i);
            }
        }

        @Override
        public void onNothingSelected(AdapterView<?> adapterView) {}
    };

    private final ResultMoshtariPresenter resultPresenterGetMoshtaries = new ResultMoshtariPresenter() {
        @Override
        public void onErrorServer(String e) {
            runOnUiThread(() -> {
                progressBar.setVisibility(GONE);
                snackbar.setText(e);
                snackbar.show();
            });
        }

        @Override
        public void onErrorInternetConnection() {
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    progressBar.setVisibility(GONE);
                    snackbar.setText("please check you're internet connection");
                    snackbar.show();
                }
            });
        }
        @Override
        public void onSuccessResultSearch(MoshtariResponse response) {
            runOnUiThread(() -> {
                progressBar.setVisibility(GONE);
                if (!response.getMoshtaris().isEmpty()){
                    MyRoomDatabase.getAppDatabase(ConfigActivity.this).moshtariDao().insertAll(response.getMoshtaris());
                    MyRoomDatabase.getAppDatabase(ConfigActivity.this).moshtariDao().insertGorohMs(response.getGorohMs());
                    vistiorNames = new ArrayList<>();
                    vistiorItem = (ArrayList<Moshtari>) response.getMoshtaris();
                    for(Moshtari m : response.getMoshtaris()){
                        vistiorNames.add(m.mName);
                    }
                    initVisitorSpinner();
                }
            });

        }
    };
}