package org.visitor.Service.view;

import static android.content.ContentValues.TAG;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.text.method.DigitsKeyListener;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import org.alarmamir.R;
import org.visitor.Api;
import org.visitor.BaseConfig;
import org.visitor.Room.AppExecutors;
import org.visitor.Room.MyRoomDatabase;
import org.visitor.Service.adapter.KalaAdapter;
import org.visitor.Service.presenter.model.Kala;
import org.visitor.Service.presenter.model.KalaResponse;
import org.visitor.Service.presenter.ResultKalaPresenter;
import org.visitor.Service.presenter.SelectItemList;
import org.visitor.Tools.Databace.DataSaver;
import org.w3c.dom.CDATASection;

import java.util.ArrayList;
import java.util.List;

public class MainKalaActivity extends AppCompatActivity {
    private KalaAdapter kalaAdapter;
    private RecyclerView list;
    private Api busApi;
    private ProgressBar loading;
    private Snackbar snackbar;
    private AlertDialog.Builder builder;

    TextView txtTitle;
    ImageView imgBack;
    MyRoomDatabase myRoomDatabase;
    public DataSaver dataSaver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        dataSaver = new DataSaver(MainKalaActivity.this);
        setContentView(R.layout.activity_main_kala);
        if(!(dataSaver.hasLogin() && dataSaver.hasConfig())){
             startActivity(new Intent(this,LoginActivity.class));
             finish();
             return;
        }
        CoordinatorLayout view = findViewById(R.id.coordinator);
        snackbar = Snackbar.make(view,"",10000);

        ImageView Settings =  findViewById(R.id.Settings);
        Settings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                builder = new AlertDialog.Builder(MainKalaActivity.this);
                builder.setTitle("Server Address");
                final EditText input = new EditText(MainKalaActivity.this);
                input.setInputType(InputType.TYPE_CLASS_TEXT);
                builder.setView(input);
                builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        try{
                            dataSaver.setHost(input.getText().toString());
                        }catch (Exception e){
                            snackbar.setText(e.toString());
                            snackbar.show();
                        }
                        busApi.getKalas(resultPresenterGetGroup);
                    }
                });
                builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });
                builder.show();
            }
        });
        init();
    }




    private void init(){
        myRoomDatabase = MyRoomDatabase.getAppDatabase(this);
        FrameLayout flCarts=findViewById(R.id.flCarts);
        flCarts.setVisibility(View.VISIBLE);
        flCarts.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(MainKalaActivity.this,CartsActivity.class);
                startActivity(intent);
            }
        });
        busApi = new Api(MainKalaActivity.this,dataSaver);

        FloatingActionButton btnAddGroup=findViewById(R.id.btnAddMoshtari);
        btnAddGroup.setOnClickListener(onclickListener);


        loading =findViewById(R.id.progressbar);
        loading.setVisibility(View.GONE);
        list =findViewById(R.id.list);


        txtTitle=findViewById(R.id.txtTitle);
        txtTitle.setText("لیست کالاها");
        imgBack=findViewById(R.id.imgBack);
        imgBack.setVisibility(View.GONE);

        busApi.getKalas(resultPresenterGetGroup);

    }
    View.OnClickListener onclickListener=new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            if (view.getId() == R.id.btnAddMoshtari) {
                busApi.getKalas(resultPresenterGetGroup);
            }
        }
    };











    private void setupRecycler(List<Kala> kalaList, List<Kala> listRoom) {
        kalaAdapter = new KalaAdapter(this,kalaList,listRoom, selectItem);
        list.setAdapter(kalaAdapter);
    }

    private SelectItemList<Kala> selectItem = new SelectItemList<Kala>() {
        @Override
        public void onSelectItem(Kala kala, int position, View view, View view2) {
            Intent intent = new Intent(MainKalaActivity.this, ProductActivity.class);
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
                                    kalaAdapter.notifyDataSetChanged();
                                 //   setupRecycler(myRoomDatabase.kalaDao().getAllKalaList());
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
                    snackbar.setText(e);
                    snackbar.show();
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
                    if (response.getKalas()!=null&&response.getKalas().size()>0){
                        setupRecycler(response.getKalas(),myRoomDatabase.kalaDao().getAllKalaList());
                    }else
                        setupRecycler(new ArrayList<>(), myRoomDatabase.kalaDao().getAllKalaList());
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