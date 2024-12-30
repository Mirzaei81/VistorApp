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
import android.widget.TextView;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.LayoutRes;
import androidx.appcompat.widget.AppCompatButton;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import org.alarmamir.R;
import org.jetbrains.annotations.NotNull;
import org.visitor.Api;
import org.visitor.Service.adapter.FactorAdapter;
import org.visitor.Service.presenter.ResultFactorPresenter;
import org.visitor.Service.presenter.SelectItemList;
import org.visitor.Service.presenter.model.AccHsbPrsnsKoliResponse;
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

public class MainFactorActivity extends BaseActivity {
    private RecyclerView list;
    private Api busApi;
    private ProgressBar loading;
    public Snackbar snackbar;

    TextView txtTitle;
    ImageView imgBack;
    Moshtari moshtari;
    AppCompatButton btnMoshtari,btnStartDate,btnEndDate,btnSearch;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getIntent().getExtras() != null) {
            moshtari = (Moshtari) getIntent().getExtras().getSerializable(Moshtari.class.getName());
        }else{
            snackbar.setText("Moshtari is null please Contact devs");
        }
        init();
    }
    private void init(){
        DataSaver dataServer = new DataSaver(MainFactorActivity.this);
        busApi = new Api(this, dataServer);
        FloatingActionButton btnAddGroup=findViewById(R.id.btnAddMoshtari);
        btnAddGroup.setOnClickListener(onclickListener);

        btnMoshtari=findViewById(R.id.btnMoshtari);
        btnStartDate=findViewById(R.id.btnStartDate);
        btnEndDate=findViewById(R.id.btnEndDate);
        btnSearch=findViewById(R.id.btnSearch);

        btnMoshtari.setOnClickListener(onClickListener);
        btnStartDate.setOnClickListener(onClickListener);
        btnEndDate.setOnClickListener(onClickListener);
        btnSearch.setOnClickListener(onClickListener);

        btnStartDate.setText(dataServer.getConfig().dateTo);
        btnEndDate.setText(dataServer.getConfig().dateFrom);

        loading =findViewById(R.id.progressbar);
        loading.setVisibility(View.GONE);
        list =findViewById(R.id.list);

        txtTitle=findViewById(R.id.txtTitle);
        txtTitle.setText("صورت حساب مشتری");
        imgBack=findViewById(R.id.imgBack);
        String EndDate = btnEndDate.getText().toString();
        String StartDate =  btnStartDate.getText().toString();
        busApi.getFactor(EndDate, StartDate, String.format(Locale.forLanguageTag("en-US"),"%d",moshtari.getmCode()), resultPresenterGetGroup);
    }

    View.OnClickListener onClickListener=new View.OnClickListener() {
        @Override
        public void onClick(View view) {

            switch (view.getId()){
                case R.id.btnMoshtari:
                    Intent i = new Intent(MainFactorActivity.this, MainMoshtariActivity.class);
                    someActivityResultLauncher.launch(i);
                    break;
                case R.id.btnStartDate:
                    showCalendar(btnStartDate);
                    break;
                case R.id.btnEndDate:
                    showCalendar(btnEndDate);
                    break;
                case R.id.btnSearch:
                    if (btnEndDate.getText().length()>2&&
                            btnStartDate.getText().length()>2&&
                            btnSearch.getText().length()>2)
                    busApi.getFactor(btnEndDate.getText().toString(),btnStartDate.getText().toString(),btnMoshtari.getText().toString(),resultPresenterGetGroup);
                    break;
                default:
                    break;

            }

        }
    };

    @Override
    protected  @LayoutRes int getLayoutResource(){
        return R.layout.activity_main_factor;
    };
    private void showCalendar(AppCompatButton btnDate) {
        PersianDatePickerDialog picker = new PersianDatePickerDialog(this)
                .setPositiveButtonString("باشه")
                .setNegativeButton("بیخیال")
                .setTodayButton("امروز")
                .setTodayButtonVisible(true)
              //  .setMinYear(1300)
                .setMaxYear(PersianDatePickerDialog.THIS_YEAR)
                .setMaxMonth(PersianDatePickerDialog.THIS_MONTH)
                .setMaxDay(PersianDatePickerDialog.THIS_DAY)
              //  .setInitDate(1370, 3, 13)
                .setActionTextColor(Color.GRAY)
           //     .setTypeFace(typeface)
                .setTitleType(PersianDatePickerDialog.WEEKDAY_DAY_MONTH_YEAR)
                .setShowInBottomSheet(true)
                .setListener(new PersianPickerListener() {
                    @Override
                    public void onDateSelected(@NotNull PersianPickerDate persianPickerDate) {
                        Log.d(TAG, "onDateSelected: " + persianPickerDate.getTimestamp());//675930448000
                        Log.d(TAG, "onDateSelected: " + persianPickerDate.getGregorianDate());//Mon Jun 03 10:57:28 GMT+04:30 1991
                        Log.d(TAG, "onDateSelected: " + persianPickerDate.getPersianLongDate());// دوشنبه  13  خرداد  1370
                        Log.d(TAG, "onDateSelected: " + persianPickerDate.getPersianMonthName());//خرداد
                        Log.d(TAG, "onDateSelected: " + PersianCalendarUtils.isPersianLeapYear(persianPickerDate.getPersianYear()));//true
                        String date=persianPickerDate.getPersianYear() + "/" + persianPickerDate.getPersianMonth() + "/" + persianPickerDate.getPersianDay();
                        if (btnDate==btnStartDate){
                            btnStartDate.setText(date);
                        }else
                            btnEndDate.setText(date);
                    }

                    @Override
                    public void onDismissed() {

                    }
                });

        picker.show();
    }

    ActivityResultLauncher<Intent> someActivityResultLauncher = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            new ActivityResultCallback<ActivityResult>() {
                @Override
                public void onActivityResult(ActivityResult result) {
                    if (result.getResultCode() == Activity.RESULT_OK) {
                        // There are no request codes
                        Intent data = result.getData();
                        if (null!=data) {
                            Moshtari moshtari = (Moshtari) data.getSerializableExtra(Moshtari.class.getName());
                            btnMoshtari.setText(moshtari.getmCode()+"");
                            txtTitle.setText("صورت حساب مشتری:"+moshtari.getmName());
                        }
                    }
                }
            });

    View.OnClickListener onclickListener=new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            switch (view.getId()){
               case R.id.btnAddMoshtari:
                    busApi.getFactor(btnEndDate.getText().toString(), btnStartDate.getText().toString(), btnMoshtari.getText().toString(), resultPresenterGetGroup);
                    break;
                case R.id.imgBack:
                    onBackPressed();
                    break;

            }
        }
    };
    private void setupRecyclerGrup(List<HsbPrsnsKoli> hsbPrsnsKolis) {
        FactorAdapter factorAdapter = new FactorAdapter(this, hsbPrsnsKolis);
        list.setAdapter(factorAdapter);
    }
    private final ResultFactorPresenter resultPresenterGetGroup = new ResultFactorPresenter() {
        @Override
        public void onErrorServer(String e) {
            runOnUiThread(new Runnable() {
                @Override
                public void run() {

                    loading.setVisibility(View.GONE);
                }
            });
        }
        @Override
        public void onErrorInternetConnection() {
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    loading.setVisibility(View.GONE);
                }
            });
        }
        @Override
        public void onSuccessResultSearch(AccHsbPrsnsKoliResponse response) {
            if(response!=null && response.getHsbPrsnsKoli()!=null){
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        if (!response.getHsbPrsnsKoli().isEmpty()){
                            setupRecyclerGrup(response.getHsbPrsnsKoli());
                        }else setupRecyclerGrup(new ArrayList<>());
                    }
                });
            }
        }
    };
}