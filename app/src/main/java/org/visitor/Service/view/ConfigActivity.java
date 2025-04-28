package org.visitor.Service.view;

import static android.content.ContentValues.TAG;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
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
import org.visitor.Service.presenter.model.MoshtariResponse;
import org.visitor.Service.presenter.model.User;
import org.visitor.Service.presenter.model.UserConfig;
import org.visitor.Tools.Databace.DataSaver;

import java.util.ArrayList;

public class ConfigActivity extends AppCompatActivity {
    private Snackbar snackbar;
    private float SelectedYear;
    private String SelectedDaftar;
    private String SelectedCompany;
    private DataSaver dataSaver;
    private Api busApi;
    private ArrayList<Float> yearItems;
    private ArrayList<String> dafterItems;
    private ArrayList<String> companyItems;
    private ArrayList<String> markazItem;
    private ImageView submit;
    private  Spinner markaz;
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
        yearItems = new ArrayList<>();
        dafterItems  = new ArrayList<>();
        companyItems = new ArrayList<>();
        markazItem = new ArrayList<>();
        for (Markaz markaz:markazs){
            markazItem.add(markaz.zName);
        }
        for (UserConfig uc:serverDetail) {
            yearItems.add(uc.YYear);
            dafterItems.add(uc.YDaftar);
            companyItems.add(uc.YCompany);
        }
        CoordinatorLayout view = findViewById(R.id.parentView);
        snackbar = Snackbar.make(view,"",10000);
        submit = findViewById(R.id.submit);
        submit.setOnClickListener(view1 -> {
            ResultConfigPresenter resultConfigPresenter = new ResultConfigPresenter() {
                @Override
                public void onErrorServer(String e) {
                    snackbar.setText(e);
                    snackbar.show();
                }

                @Override
                public void onErrorInternetConnection() {
                    snackbar.setText("Check you're internet connection");
                    snackbar.show();
                }

                @Override
                public void onFinish(String response) {
                    ConfigResponse config = new  Gson().fromJson(response, ConfigResponse.class);
                    config.markaz =markazs.get(markaz.getSelectedItemPosition()).zCode;
                    config.loginId =(int)loginId;
                    dataSaver.setConfig(config);
                    Intent intent = new Intent(ConfigActivity.this,MainActivity.class);
                    Bundle bundle = new Bundle();

                    busApi.getMoshtaris(resultPresenterGetMoshtaries);
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

    enum DropDowns {
        year,daftar,company,markaz
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
            }
        }

        @Override
        public void onNothingSelected(AdapterView<?> adapterView) {}
    };

    private final ResultMoshtariPresenter resultPresenterGetMoshtaries = new ResultMoshtariPresenter() {
        @Override
        public void onErrorServer(String e) {
            runOnUiThread(() -> {
                snackbar.setText(e);
                snackbar.show();
            });
        }

        @Override
        public void onErrorInternetConnection() {
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    snackbar.setText("please check you're internet connection");
                    snackbar.show();
                }
            });
        }
        @Override
        public void onSuccessResultSearch(MoshtariResponse response) {
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    Log.i(TAG, "success");
                    if (!response.getMoshtaris().isEmpty()){
                        MyRoomDatabase.getAppDatabase(ConfigActivity.this).moshtariDao().insertAll(response.getMoshtaris());
                        MyRoomDatabase.getAppDatabase(ConfigActivity.this).moshtariDao().insertGorohMs(response.getGorohMs());
                    }
                }
            });

        }
    };
}