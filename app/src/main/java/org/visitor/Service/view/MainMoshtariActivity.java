package org.visitor.Service.view;

import static android.content.ContentValues.TAG;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.SearchView;
import android.widget.TextView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import org.alarmamir.R;
import org.visitor.Api;

import org.visitor.Room.MyRoomDatabase;
import org.visitor.Service.adapter.MoshtariAdapter;
import org.visitor.Service.presenter.ResultFactorPresenter;
import org.visitor.Service.presenter.ResultGroupPresenter;
import org.visitor.Service.presenter.ResultMoshtariPresenter;
import org.visitor.Service.presenter.model.AccHsbPrsnsKoliResponse;
import org.visitor.Service.presenter.model.Moshtari;
import org.visitor.Service.presenter.model.MoshtariResponse;
import org.visitor.Service.presenter.SelectItemList;
import org.visitor.Tools.Databace.DataSaver;

import java.util.ArrayList;
import java.util.List;

public class MainMoshtariActivity extends BaseActivity{
    private static final int PERMISSION_SEND_SMS = 123;
    private MoshtariAdapter moshtariAdapter;
   private RecyclerView list;
    private Api busApi;
    private ProgressBar loading;
    private SearchView searchView;

    TextView txtTitle;
    ImageView imgBack;
    private DataSaver dataServer;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        init();
    }
    @Override
    protected void OnPositiveClick() {
        busApi.getMoshtaris(resultPresenterGetMoshtaries);
    }
    @Override
    protected  int getLayoutResource(){
        return R.layout.activity_main_ghorop;
    }
    private void init(){
        dataServer = new DataSaver(MainMoshtariActivity.this);
        busApi = new Api(MainMoshtariActivity.this,dataServer);

        snackbar = Snackbar.make(this.findViewById(R.id.constraint),"",dataServer.getDuraiton());

        FloatingActionButton btnAddGroup=findViewById(R.id.btnAddMoshtari);
        btnAddGroup.setOnClickListener(onclickListener);

        loading =findViewById(R.id.progressbar);
        loading.setVisibility(View.GONE);
        list =findViewById(R.id.list);
        searchView= findViewById(R.id.search_view);


        txtTitle=findViewById(R.id.txtTitle);
        txtTitle.setText("لیست مشتری ها");
        imgBack=findViewById(R.id.imgBack);
        imgBack.setVisibility(View.GONE);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                moshtariAdapter.setFilter(s);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                return false;
            }
        });
        setupRecyclerGrup(MyRoomDatabase.getAppDatabase(MainMoshtariActivity.this).moshtariDao().getAll()); ;
        busApi.getMoshtaris(resultPresenterGetMoshtaries);
    }
    View.OnClickListener onclickListener=new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            if (view.getId() == R.id.btnAddMoshtari) {
                busApi.getMoshtaris(resultPresenterGetMoshtaries);
            }
        }
    };
    private void setupRecyclerGrup(List<Moshtari> moshtariList) {
        moshtariAdapter = new MoshtariAdapter(this,moshtariList, selectItem);
        list.setAdapter(moshtariAdapter);
    }

    private final SelectItemList<Moshtari> selectItem = new SelectItemList<Moshtari>() {
        @Override
        public void onSelectItem(Moshtari moshtari, int position, View view, View view2) {
            Intent returnIntent = new Intent(MainMoshtariActivity.this,MainFactorActivity.class);
            returnIntent.putExtra(Moshtari.class.getName(),moshtari);
            setResult(Activity.RESULT_OK,returnIntent);
            startActivity(returnIntent);
            finish();
        }

        @Override
        public void onSelectItemForCats(Moshtari object, int position, View view, TextView txtMojodi, boolean booleanAdd) {

        }
    };

    private ResultMoshtariPresenter resultPresenterGetMoshtaries = new ResultMoshtariPresenter() {
        @Override
        public void onErrorServer(String e) {
            runOnUiThread(() -> {
                snackbar.setText(e);
                snackbar.show();
                loading.setVisibility(View.GONE);
            });
        }

        @Override
        public void onErrorInternetConnection() {
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    snackbar.setText("please check you're internet connection");
                    snackbar.show();
                    loading.setVisibility(View.GONE);
                }
            });
        }
        @Override
        public void onSuccessResultSearch(MoshtariResponse response) {
            runOnUiThread(() -> {
                Log.i(TAG, "success");
                loading.setVisibility(View.GONE);
                if (!response.getMoshtaris().isEmpty()){
                    var  moshtariDao= MyRoomDatabase.getAppDatabase(MainMoshtariActivity.this).moshtariDao();
                    moshtariDao.deleteAll();
                    moshtariDao.deleteAllGorohMs();
                    moshtariDao.insertAll(response.getMoshtaris());
                    moshtariDao.insertGorohMs(response.getGorohMs());
                    setupRecyclerGrup(response.getMoshtaris());
                }else
                    setupRecyclerGrup(new ArrayList<>());
            });

        }
    };
}