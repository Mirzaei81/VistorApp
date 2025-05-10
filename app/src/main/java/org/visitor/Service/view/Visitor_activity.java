package org.visitor.Service.view;

import static android.view.View.GONE;
import static android.view.View.VISIBLE;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.Spinner;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.snackbar.Snackbar;
import com.google.gson.Gson;

import org.alarmamir.R;
import org.visitor.Api;
import org.visitor.Room.MyRoomDatabase;
import org.visitor.Service.adapter.MoshtariDropDown;
import org.visitor.Service.presenter.ResultConfigPresenter;
import org.visitor.Service.presenter.ResultMoshtariPresenter;
import org.visitor.Service.presenter.model.ConfigResponse;
import org.visitor.Service.presenter.model.Moshtari;
import org.visitor.Service.presenter.model.MoshtariResponse;
import org.visitor.Service.presenter.model.SpinnerItem;
import org.visitor.Tools.Databace.DataSaver;

import java.util.ArrayList;
import java.util.List;

public class Visitor_activity extends AppCompatActivity {
    private Snackbar snackbar;
    private String SelectedVisitor;
    MyRoomDatabase myRoomDatabase;
    private List<Moshtari> moshtaris;

    private ProgressBar progressBar;
    private List<SpinnerItem>  items = new ArrayList<SpinnerItem>();
    private Spinner visitorSpinner;
    private Api busApi;
    private MyRoomDatabase mRoom;
    private ArrayList<Moshtari> vistiorItem = new ArrayList<>();
    private ArrayList<String> vistiorNames ;
    private ImageView submit;

    private DataSaver dataSaver;
    @Override
    protected void  onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pick_visitor);
        init();
    }

    public void init(){

        dataSaver = new DataSaver(this);
        myRoomDatabase = MyRoomDatabase.getAppDatabase(this);
        moshtaris =  myRoomDatabase.moshtariDao().getAll();
        progressBar = findViewById(R.id.progressbar);
        progressBar.setVisibility(VISIBLE);

        for (Moshtari m: moshtaris){
            String hamkarString = switch (m.getmHmkar()) {
                case 0 -> "مشتری";
                case 1 -> "همکار";
                case 2 -> "عمده";
                default -> "مشتری";
            };
            items.add(new SpinnerItem(m.getmName(),hamkarString));
        }
        busApi = new Api(this,dataSaver);
        busApi.getMoshtaris(resultPresenterGetMoshtaries);

        snackbar = Snackbar.make(findViewById(R.id.parent_view),"",dataSaver.getDuraiton());

        mRoom = MyRoomDatabase.getAppDatabase(this);
        List<Moshtari> moshtaris  = mRoom.moshtariDao().getAll();
        vistiorItem = (ArrayList<Moshtari>) moshtaris;

        if(!moshtaris.isEmpty()){
            for(Moshtari m : moshtaris){
                vistiorNames.add(m.mName);
            }
            initVisitorSpinner();
        }

        visitorSpinner = findViewById(R.id.visitor);
        vistiorItem = new ArrayList<>();
        submit = findViewById(R.id.submit);
        submit.setOnClickListener(view1 -> {
                    ConfigResponse config = dataSaver.getConfig();
                    config.visitorName = vistiorNames.get(visitorSpinner.getSelectedItemPosition());
                    Moshtari moshtari =null;
                    for (Moshtari m : vistiorItem) {
                        if (m.getmName().equals(config.visitorName)) {
                            moshtari = m;
                        }
                    }
                    config.mCode =  moshtari ==null?0:moshtari.mCode;
                    dataSaver.setConfig(config);
                    Intent intent = new Intent(Visitor_activity.this,MainActivity.class);
                    startActivity(intent);
                    finish();
                    return;
        });
    }

    public void  initVisitorSpinner(){
        Spinner year =  findViewById(R.id.visitor);
        ArrayAdapter<String> year1 = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, vistiorNames);
        year1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        year.setAdapter(year1);
        ItemSelect yearItemSelect = new ItemSelect();
        year.setOnItemSelectedListener(yearItemSelect);
    }

    private class ItemSelect extends Activity implements AdapterView.OnItemSelectedListener{

        public ItemSelect(){

        }
        @Override
        public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
            Visitor_activity.this.SelectedVisitor= (String)adapterView.getItemAtPosition(i);
        }

        @Override
        public void onNothingSelected(AdapterView<?> adapterView) {

        }
    }

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
                    MyRoomDatabase.getAppDatabase(Visitor_activity.this).moshtariDao().insertAll(response.getMoshtaris());
                    MyRoomDatabase.getAppDatabase(Visitor_activity.this).moshtariDao().insertGorohMs(response.getGorohMs());
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
