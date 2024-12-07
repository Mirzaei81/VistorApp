package org.visitor.Service.view;

import static android.content.ContentValues.TAG;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.AtomicFile;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import org.alarmamir.R;
import org.visitor.Api;
import org.visitor.Room.AppExecutors;
import org.visitor.Room.MyRoomDatabase;
import org.visitor.Service.adapter.CartAdapter;
import org.visitor.Service.presenter.ResultKalaPresenter;
import org.visitor.Service.presenter.SelectItemList;
import org.visitor.Service.presenter.model.Kala;
import org.visitor.Service.presenter.model.KalaResponse;
import org.visitor.Tools.Databace.DataSaver;

import java.util.ArrayList;
import java.util.List;

public class CartsActivity extends AppCompatActivity {
    private CartAdapter cartAdapter;
    private RecyclerView list;
    private Api busApi;
    private ProgressBar loading;
    private DataSaver dataSaver;

    TextView txtTitle;
    ImageView imgBack;
    MyRoomDatabase myRoomDatabase;
    private TextView txtBuy;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        dataSaver = new DataSaver(CartsActivity.this);
        setContentView(R.layout.activity_carts);
        if(!dataSaver.hasLogin()){
           startActivity(new Intent(this,LoginActivity.class));
           finish();
        }
        init();
    }

    private void init(){
        myRoomDatabase = MyRoomDatabase.getAppDatabase(this);
        busApi = new Api(CartsActivity.this,dataSaver);
        loading =findViewById(R.id.progressbar);
        loading.setVisibility(View.GONE);
        list =findViewById(R.id.list);
        txtTitle=findViewById(R.id.txtTitle);
        txtTitle.setText("سبد خرید");
        imgBack=findViewById(R.id.imgBack);
        imgBack.setVisibility(View.GONE);
        txtBuy=findViewById(R.id.txtBuy);
        txtBuy.setOnClickListener(onclickListener);
        setupRecycler(myRoomDatabase.kalaDao().getAllKalaList());
    }
    View.OnClickListener onclickListener=new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            if (view.getId()==R.id.btnAddMoshtari){
                busApi.getKalas(resultPresenterGetGroup);
            }
        }
    };











    private void setupRecycler(List<Kala> kalaList) {
        cartAdapter = new CartAdapter(this,kalaList, selectItem);
        list.setAdapter(cartAdapter);
    }

    private SelectItemList<Kala> selectItem = new SelectItemList<Kala>() {
        @Override
        public void onSelectItem(Kala kala, int position, View view, View view2) {
            Intent intent = new Intent(CartsActivity.this, ProductActivity.class);
            intent.putExtra(kala.getClass().getName(), kala); //second param is Serializable
            startActivity(intent);


        }

        @Override
        public void onSelectItemForCats(Kala kala, int position, View view, TextView txtNumber, boolean booleanAdd) {

            AppExecutors.getInstance().diskIO().execute(new Runnable() {
                @Override
                public void run() {
                    if (booleanAdd) {
                        if (null!=myRoomDatabase.kalaDao().existItemInDatabase(kala.getkCode())){

                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    int number=Integer.parseInt(txtNumber.getText().toString());
                                    // Update UI components here
                                    txtNumber.setText(String.valueOf(number+1));
                                    kala.setNumber((long) (number+1));
                                    myRoomDatabase.kalaDao().updateKala(kala);
                                }
                            });

                        }else {
                            kala.setNumber((long) 1);
                            myRoomDatabase.kalaDao().insertKalas(kala);
                        }

                    }else if(null!=myRoomDatabase.kalaDao().existItemInDatabase(kala.getkCode())) {

                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                int number=Integer.parseInt(txtNumber.getText().toString());
                                if (number==1){
                                    myRoomDatabase.kalaDao().deleteKalas(kala);
                                    setupRecycler(myRoomDatabase.kalaDao().getAllKalaList());


                                }else {
                                    txtNumber.setText(String.valueOf(number - 1));
                                    kala.setNumber((long) (number - 1));
                                    myRoomDatabase.kalaDao().updateKala(kala);
                                }
                            }
                        });

                    }

                }
            });

        }
    };

    private ResultKalaPresenter resultPresenterGetGroup = new ResultKalaPresenter() {
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
        public void onSuccessResultSearch(KalaResponse response) {
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    Log.i(TAG, "success");
                    if (response.getKalas().size()>0){
                        setupRecycler(response.getKalas());
                    }else
                        setupRecycler(new ArrayList<>());
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