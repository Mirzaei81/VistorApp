package org.visitor.Service.view;

import static android.content.ContentValues.TAG;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.LayoutRes;
import androidx.appcompat.widget.AppCompatButton;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import org.alarmamir.R;
import org.visitor.Api;
import org.visitor.Service.adapter.FactorDetailAdapter;
import org.visitor.Service.presenter.ResultFactorDetailPresenter;
import org.visitor.Service.presenter.SelectItemList;
import org.visitor.Service.presenter.model.FactorDetail;
import org.visitor.Service.presenter.model.HsbPrsnsKoli;
import org.visitor.Service.presenter.model.Moshtari;
import org.visitor.Tools.Databace.DataSaver;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import ir.hamsaa.persiandatepicker.PersianDatePickerDialog;
import ir.hamsaa.persiandatepicker.api.PersianPickerDate;
import ir.hamsaa.persiandatepicker.api.PersianPickerListener;
import ir.hamsaa.persiandatepicker.util.PersianCalendarUtils;

public class FactorDetailActivity extends BaseActivity {
    private RecyclerView list;
    private Api busApi;
    private ProgressBar loading;
    public Snackbar snackbar;

    TextView txtTitle;
    ImageView imgBack;
    private int F_no;
    AppCompatButton btnMoshtari,btnStartDate,btnEndDate,btnSearch;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        RelativeLayout view = findViewById(R.id.parent);
        snackbar = Snackbar.make(view,"",10000) ;
        Bundle b = getIntent().getExtras();
        if (b != null) {
            F_no = b.getInt(FactorDetailActivity.class.getName());
            assert  F_no!=0;
        }else{
            snackbar.setText("FactorDetail No is null please Contact devs");
        }
        init();
    }
    private void init(){
        DataSaver dataServer = new DataSaver(FactorDetailActivity.this);
        busApi = new Api(this, dataServer);
        FloatingActionButton btnAddGroup=findViewById(R.id.btnAddMoshtari);

        loading =findViewById(R.id.progressbar);
        loading.setVisibility(View.GONE);
        list =findViewById(R.id.list);

        txtTitle=findViewById(R.id.txtTitle);
        txtTitle.setText("صورت حساب مشتری");
        imgBack=findViewById(R.id.imgBack);
        busApi.getFactorDetail(F_no,resultFactorDetailPresenter);
    }

    View.OnClickListener onClickListener=new View.OnClickListener() {
        @Override
        public void onClick(View view) {

            switch (view.getId()){
                case R.id.btnMoshtari:
                    Intent i = new Intent(FactorDetailActivity.this, MainMoshtariActivity.class);
                case R.id.btnSearch:
                    if (btnEndDate.getText().length()>2&&
                            btnStartDate.getText().length()>2&&
                            btnSearch.getText().length()>2)
                        busApi.getFactorDetail(F_no,resultFactorDetailPresenter);
                    break;
                default:
                    break;

            }

        }
    };

    @Override
    protected  @LayoutRes int getLayoutResource(){
        return R.layout.factor_detail_activity;
    };

    View.OnClickListener onclickListener=new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            switch (view.getId()){
                case R.id.btnAddMoshtari:
                    busApi.getFactorDetail(F_no,resultFactorDetailPresenter);
                    break;
                case R.id.imgBack:
                    onBackPressed();
                    break;

            }
        }
    };
    private void setupRecyclerGrup(ArrayList<FactorDetail>  factorDetails) {
        FactorDetailAdapter factorAdapter = new FactorDetailAdapter(factorDetails);
        list.setAdapter(factorAdapter);
    }

    private final SelectItemList<HsbPrsnsKoli> selectItem = new SelectItemList<HsbPrsnsKoli>() {
        @Override
        public void onSelectItem(HsbPrsnsKoli moshtari, int position, View view, View view2) {
            Intent intent=new Intent( FactorDetailActivity.this, MainFactorActivity.class);
            intent.putExtra(Moshtari.class.getName(),moshtari);
            startActivity(intent);

        }

        @Override
        public void onSelectItemForCats(HsbPrsnsKoli object, int position, View view, TextView txtMojodi, boolean booleanAdd) {

        }
    };
    private final ResultFactorDetailPresenter resultFactorDetailPresenter= new ResultFactorDetailPresenter() {
        @Override
        public void onErrorServer(String e) {
            runOnUiThread(() ->{
                loading.setVisibility(View.GONE);
                snackbar.setText(e);
                snackbar.show();
            });
        }
        @Override
        public void onErrorInternetConnection() {
            runOnUiThread(() -> {
                loading.setVisibility(View.GONE);
                snackbar.setText("Check you're internet connection");
                snackbar.show();
            });
        }
        @Override
        public void onSuccessResultSearch(ArrayList<FactorDetail> response) {
            if(response!=null){
                runOnUiThread(() -> {
                    setupRecyclerGrup(response);
                });
            }
        }
    };
}
