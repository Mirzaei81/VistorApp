package org.visitor.Service.view;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.coordinatorlayout.widget.CoordinatorLayout;

import com.google.android.material.snackbar.Snackbar;

import org.alarmamir.R;
import org.visitor.Api;
import org.visitor.Service.presenter.ResaultConfigPresenter;
import org.visitor.Service.presenter.ResultLoginPresenter;
import org.visitor.Service.presenter.model.UserConfig;
import org.visitor.Service.presenter.model.UserResponse;
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
    private ImageView submit;
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
        } else if (!b.containsKey("Configurations")){
            Log.v("Bundle","Config Detail type is Incorrect");
        }
        ArrayList<UserConfig> serverDetail = (ArrayList<UserConfig>) b.get("Configurations");
        assert serverDetail != null;
        yearItems = new ArrayList<>();
        dafterItems  = new ArrayList<>();
        companyItems = new ArrayList<>();
        for (UserConfig uc:serverDetail) {
            yearItems.add(uc.YYear);
            dafterItems.add(uc.YDaftar);
            companyItems.add(uc.YCompany);
        }
        CoordinatorLayout view = findViewById(R.id.parentView);
        snackbar = Snackbar.make(view,"",10000);
        submit = findViewById(R.id.submit);
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ResaultConfigPresenter resaultConfigPresenter = new ResaultConfigPresenter() {
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
                        dataSaver.setConfig(response);
                        startActivity(new Intent(ConfigActivity.this,MainKalaActivity.class));
                        finish();
                    }
                };
                busApi.getDbName(SelectedYear,SelectedDaftar,SelectedCompany,resaultConfigPresenter);
            }
        });
        initYearSpinner();
        initDaftarSpinner();
        initCompanySpinner();

    }
    public void initCompanySpinner(){
        Spinner company=  findViewById(R.id.company);
        ArrayAdapter<String> companyAdapter = new ArrayAdapter<String>(ConfigActivity.this, com.google.android.material.R.layout.support_simple_spinner_dropdown_item, companyItems);
        companyAdapter.setDropDownViewResource(androidx.appcompat.R.layout.support_simple_spinner_dropdown_item);
        company.setAdapter(companyAdapter);
        ItemSelect companyItemSelect = new ItemSelect(DropDowns.company);
        company.setOnItemSelectedListener(companyItemSelect);
    }
    public void  initDaftarSpinner(){
        Spinner daftar=  findViewById(R.id.daftar);
        ArrayAdapter<String> daftarAdapter = new ArrayAdapter<String>(ConfigActivity.this, com.google.android.material.R.layout.support_simple_spinner_dropdown_item, dafterItems);
        daftarAdapter.setDropDownViewResource(androidx.appcompat.R.layout.support_simple_spinner_dropdown_item);
        daftar.setAdapter(daftarAdapter);
        ItemSelect daftarItemSelect = new ItemSelect(DropDowns.daftar);
        daftar.setOnItemSelectedListener(daftarItemSelect);
    }
    public void  initYearSpinner(){
        Spinner year =  findViewById(R.id.year);
        ArrayAdapter<Float> year1 = new ArrayAdapter<Float>(ConfigActivity.this, com.google.android.material.R.layout.support_simple_spinner_dropdown_item, yearItems);
        year1.setDropDownViewResource(androidx.appcompat.R.layout.support_simple_spinner_dropdown_item);
        year.setAdapter(year1);
        ItemSelect yearItemSelect = new ItemSelect(DropDowns.year);
        year.setOnItemSelectedListener(yearItemSelect);
    }

    enum DropDowns {
        year,daftar,company
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
}