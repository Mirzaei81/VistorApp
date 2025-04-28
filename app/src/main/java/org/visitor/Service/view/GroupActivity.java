package org.visitor.Service.view;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.ProgressBar;

import androidx.annotation.LayoutRes;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import org.alarmamir.R;
import org.visitor.Api;
import org.visitor.Room.AppExecutors;
import org.visitor.Room.MyRoomDatabase;
import org.visitor.Service.adapter.GroupAdapter;
import org.visitor.Service.presenter.ResultGroupPresenter;
import org.visitor.Service.presenter.SelectItemList;
import org.visitor.Service.presenter.model.Groups;
import org.visitor.Tools.Databace.DataSaver;

import java.util.ArrayList;
import java.util.List;

public class GroupActivity extends BaseActivity {
    private GroupAdapter groupAdapter;
    private RecyclerView list;
    private Api busApi;
    private ProgressBar loading;
    TextView txtTitle;
    ImageView imgBack;
    MyRoomDatabase myRoomDatabase;
    public DataSaver dataSaver;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        dataSaver = new DataSaver( GroupActivity.this);
        if(!(dataSaver.hasLogin() && dataSaver.hasConfig())){
            startActivity(new Intent(this,LoginActivity.class));
            finish();
            return;
        }
        CoordinatorLayout view = findViewById(R.id.coordinator);
        if(view!=null)
            snackbar = Snackbar.make(view,"",10000);
        init();
    }
    @Override
    protected  @LayoutRes int  getLayoutResource()
    {
        return  R.layout.activity_groups;
    };
    @Override
    protected void OnPositiveClick() {
        busApi.getGroups(resultPresenterGetGroups);
    }

    private void init(){
        myRoomDatabase = MyRoomDatabase.getAppDatabase(this);
        FrameLayout flCarts=findViewById(R.id.flCarts);
        flCarts.setVisibility(View.VISIBLE);
        flCarts.setOnClickListener(view -> {
            Intent intent=new Intent( GroupActivity.this,CartsActivity.class);
            startActivity(intent);
        });
        busApi = new Api(GroupActivity.this,dataSaver);

        FloatingActionButton btnAddGroups=findViewById(R.id.btnAddMoshtari);
        btnAddGroups.setOnClickListener(onclickListener);


        loading =findViewById(R.id.progressbar);
        loading.setVisibility(View.GONE);
        list =findViewById(R.id.list);


        txtTitle=findViewById(R.id.txtTitle);
        txtTitle.setText("لیست کالاها");
        imgBack=findViewById(R.id.imgBack);
        imgBack.setVisibility(View.GONE);
        busApi.getGroups(resultPresenterGetGroups);

    }
    View.OnClickListener onclickListener=new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            if (view.getId() == R.id.btnAddMoshtari) {
                busApi.getGroups(resultPresenterGetGroups);
            }
        }
    };
    private void setupRecycler(List<Groups> groupList , List<Groups> listRoom) {
        groupAdapter = new GroupAdapter(this,groupList,listRoom, selectItem);
        list.setAdapter(groupAdapter);
    }

    private SelectItemList<Groups> selectItem = new SelectItemList<Groups>() {
        @Override
        public void onSelectItem(Groups kala, int position, View view, View view2) {
            Intent intent = new Intent(GroupActivity.this, ProductActivity.class);
            intent.putExtra(GroupActivity.class.getName(), kala.id); //second param is Serializable
            startActivity(intent);


        }

        @Override
        public void onSelectItemForCats(Groups group, int position, View view, TextView txtNumber, boolean booleanAdd) {
            AppExecutors.getInstance().diskIO().execute(new Runnable() {
                @Override
                public void run() {
                    if (booleanAdd) {
                        if (null!=myRoomDatabase.groupDao().existItemInDatabase(group.id)){

                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    int number=Integer.parseInt(txtNumber.getText().toString());
                                    // Update UI components here
                                    txtNumber.setText(String.valueOf(number+1));
//                                    group.setNumber((long) (number+1));
                                    myRoomDatabase.groupDao().updateGroup(group);
                                }
                            });

                        }else {
//                            group.setNumber((long) 1);
                            myRoomDatabase.groupDao().insertGroups(group);
                        }


                    }else if(null!=myRoomDatabase.groupDao().existItemInDatabase(group.id)) {
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                int number=Integer.parseInt(txtNumber.getText().toString());
                                if (number==1){
                                    myRoomDatabase.groupDao().deleteGroups(group);
                                    groupAdapter.notifyDataSetChanged();
                                    //   setupRecycler(myRoomDatabase.groupDao().getAllGroupsList());
                                }else {
                                    txtNumber.setText(String.valueOf(number - 1));
//                                    group.setNumber((long) (number - 1));
                                    myRoomDatabase.groupDao().updateGroup(group);
                                }
                            }
                        });

                    }

                }
            });
        }


    };
    private final ResultGroupPresenter resultPresenterGetGroups = new ResultGroupPresenter() {

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
                    snackbar.setText("Check you're Internet connection");
                    snackbar.show();
                    loading.setVisibility(View.GONE);
                }
            });
        }
        @Override
        public void onSuccessResultSearch(ArrayList<Groups> response) {
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    loading.setVisibility(View.GONE);
                    if (response!=null && !response.isEmpty()){
                        setupRecycler(response,myRoomDatabase.groupDao().getAllGroupList());
                    }else
                        setupRecycler(new ArrayList<>(), myRoomDatabase.groupDao().getAllGroupList());
                }
            });

        }
    };
}
