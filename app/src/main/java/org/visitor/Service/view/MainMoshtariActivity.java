package org.visitor.Service.view;

import static android.content.ContentValues.TAG;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import org.alarmamir.R;
import org.visitor.Api;

import org.visitor.Service.adapter.MoshtariAdapter;
import org.visitor.Service.presenter.ResultGroupPresenter;
import org.visitor.Service.presenter.model.Moshtari;
import org.visitor.Service.presenter.model.MoshtariResponse;
import org.visitor.Service.presenter.SelectItemList;
import org.visitor.Tools.Databace.DataSaver;

import java.util.ArrayList;
import java.util.List;

public class MainMoshtariActivity extends AppCompatActivity {
    private static final int PERMISSION_SEND_SMS = 123;



    private MoshtariAdapter moshtariAdapter;

   private RecyclerView list;





    private Api busApi;
    private ProgressBar loading;

    TextView txtTitle;
    ImageView imgBack;
    private DataSaver dataServer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_ghorop);
        init();


    }




    private void init(){
        dataServer = new DataSaver(MainMoshtariActivity.this);
        busApi = new Api(MainMoshtariActivity.this,dataServer);

        FloatingActionButton btnAddGroup=findViewById(R.id.btnAddMoshtari);
        btnAddGroup.setOnClickListener(onclickListener);


        loading =findViewById(R.id.progressbar);
        loading.setVisibility(View.GONE);
        list =findViewById(R.id.list);


        txtTitle=findViewById(R.id.txtTitle);
        txtTitle.setText("لیست مشتری ها");
        imgBack=findViewById(R.id.imgBack);
        imgBack.setVisibility(View.GONE);

        busApi.getMoshtaris(resultPresenterGetGroup);






    }



    View.OnClickListener onclickListener=new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            switch (view.getId()){
                case R.id.btnAddMoshtari:
                    busApi.getMoshtaris(resultPresenterGetGroup);
                    break;

            }
        }
    };











    private void setupRecyclerGrup(List<Moshtari> moshtariList) {
        moshtariAdapter = new MoshtariAdapter(this,moshtariList, selectItem);
        list.setAdapter(moshtariAdapter);
    }

    private SelectItemList<Moshtari> selectItem = new SelectItemList<Moshtari>() {
        @Override
        public void onSelectItem(Moshtari moshtari, int position, View view, View view2) {

            Intent returnIntent = new Intent();
            returnIntent.putExtra(Moshtari.class.getName(),moshtari);
            setResult(Activity.RESULT_OK,returnIntent);
            finish();

        }

        @Override
        public void onSelectItemForCats(Moshtari object, int position, View view, TextView txtMojodi, boolean booleanAdd) {

        }
    };







    private ResultGroupPresenter resultPresenterGetGroup = new ResultGroupPresenter() {
        @Override
        public void onStart() {
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    loading.setVisibility(View.VISIBLE);
                }
            });

        }

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
        public void onSuccessResultSearch(MoshtariResponse response) {
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    Log.i(TAG, "success");
                    if (response.getMoshtaris().size()>0){
                        setupRecyclerGrup(response.getMoshtaris());
                    }else
                        setupRecyclerGrup(new ArrayList<>());
                }
            });

        }
        @Override
        public void noBus() {
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    loading.setVisibility(View.GONE);
                }
            });


        }

        @Override
        public void onError(final String msg) {
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    loading.setVisibility(View.GONE);
                }
            });
        }

        @Override
        public void onFinish() {
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    loading.setVisibility(View.GONE);

                }
            });
        }
    };




}