package org.visitor.Service.view;

import static android.content.ContentValues.TAG;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.coordinatorlayout.widget.CoordinatorLayout;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.text.method.DigitsKeyListener;
import android.util.Log;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ViewFlipper;

import com.google.android.material.snackbar.Snackbar;
import com.google.gson.Gson;

import org.alarmamir.R;
import org.visitor.Api;
import org.visitor.KeyConst;

import org.visitor.NetworkListener;
import org.visitor.ResponseUser;
import org.visitor.Room.MyRoomDatabase;
import org.visitor.Service.presenter.ResultLoginPresenter;
import org.visitor.Service.presenter.ResultMoshtariPresenter;
import org.visitor.Service.presenter.ResultUserNamePreasenter;
import org.visitor.Service.presenter.model.Markaz;
import org.visitor.Service.presenter.model.MoshtariResponse;
import org.visitor.Service.presenter.model.User;
import org.visitor.Service.presenter.model.UserConfig;
import org.visitor.Service.presenter.model.UserResponse;
import org.visitor.WebServiceNetwork;
import org.visitor.userModel.ResultUserPresenter;
import org.visitor.Tools.Databace.DataSaver;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Objects;

public class LoginActivity extends AppCompatActivity {
    private Api busApi;
    private EditText password ;
    private DataSaver dataSaver;
    private Snackbar snackbar;
    private AlertDialog.Builder builder;
    public String SelectedUser;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        CoordinatorLayout view = findViewById(R.id.ParentView);
        snackbar = Snackbar.make(view,"",10000);
        init();
    }
    public  void init(){
        dataSaver =new DataSaver(LoginActivity.this);
        busApi = new Api(this,dataSaver);
        password=findViewById(R.id.password);
        ImageView login = findViewById(R.id.login);
        ImageView settings = findViewById(R.id.Settings);
        settings.setOnClickListener(view -> {
            builder = new AlertDialog.Builder(LoginActivity.this);
            builder.setTitle("Server Address");
            final EditText input = new EditText(LoginActivity.this);
            input.setInputType(InputType.TYPE_CLASS_TEXT);
//            input.setKeyListener(DigitsKeyListener.getInstance("09123456789.:"));
            builder.setView(input);
            builder.setPositiveButton("OK", (dialog, which) -> {
                try{
                    dataSaver.setHost(input.getText().toString());
                    getUser();
                }catch (Exception e){
                    snackbar.setText(Objects.requireNonNull(e.getMessage()));
                    snackbar.show();
                }
            });
            builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.cancel();
                }
            });
            builder.show();
        });
        login.setOnClickListener(view -> {
            ResultLoginPresenter resultLoginPresenter = new ResultLoginPresenter() {
                @Override
                public void onErrorServer(String e) {
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            snackbar.setText(e);
                            snackbar.show();
                        }
                    });
                }

                @Override
                public void onErrorInternetConnection() {
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            snackbar.setText("Couldn't connect to internet please check you're connection");
                            snackbar.show();
                        }
                    });
                }

                @Override
                public void onFinish(UserResponse response) {
                    CheckBox checkBox = findViewById(R.id.checkbox);
                    if(checkBox.isChecked()){
                        dataSaver.setLogin(response);
                    }
                    Intent ConfigActivity = new Intent(LoginActivity.this,ConfigActivity.class);
                    Bundle bundle =new Bundle();
                    bundle.putBoolean("RememberMe",checkBox.isChecked());
                    bundle.putSerializable(UserConfig.class.getName(),response.serverDetail);
                    bundle.putSerializable(Markaz.class.getName(),response.markazs);
                    bundle.putFloat(User.class.getName(),response.UserObject.UNo);
                    ConfigActivity.putExtras(bundle);
                    startActivity(ConfigActivity);
                }
            };
            busApi.login(LoginActivity.this.SelectedUser,password.getText().toString(),resultLoginPresenter);
        });
        getUser();
    }
    private void getUser(){
        busApi.getUsers(new ResultUserNamePreasenter() {
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
            public void onFinish(String[] response) {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        initUserNamesSpinner(response);
                    }
                });
            }
        });
    }
    private void initUserNamesSpinner(String[] userNameItem){
        Spinner userName=  findViewById(R.id.usernames);
        ArrayAdapter<String> companyAdapter =  new ArrayAdapter<>(LoginActivity.this, android.R.layout.simple_spinner_dropdown_item, userNameItem);
        companyAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        userName.setAdapter(companyAdapter);
        LoginActivity.this.SelectedUser =  companyAdapter.getItem(0);
        ItemSelect companyItemSelect = new ItemSelect();
        userName.setOnItemSelectedListener(companyItemSelect);
    }
    private class ItemSelect extends Activity implements AdapterView.OnItemSelectedListener{
        @Override
        public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
            LoginActivity.this.SelectedUser = (String)adapterView.getItemAtPosition(i);
        }

        @Override
        public void onNothingSelected(AdapterView<?> adapterView) {}
    };
}