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
import org.visitor.Service.presenter.ResultLoginPresenter;
import org.visitor.Service.presenter.ResultUserNamePreasenter;
import org.visitor.Service.presenter.model.UserResponse;
import org.visitor.WebServiceNetwork;
import org.visitor.userModel.ResultUserPresenter;
import org.visitor.Tools.Databace.DataSaver;

import java.util.Collections;

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
            input.setKeyListener(DigitsKeyListener.getInstance("09123456789.:"));
            builder.setView(input);
            builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    try{
                        dataSaver.setHost(input.getText().toString());
                        getUser();
                    }catch (Exception e){
                        snackbar.setText(e.getMessage());
                        snackbar.show();
                    }
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
                    CheckBox checkBox = findViewById(androidx.appcompat.R.id.checkbox);
                    if(checkBox.isChecked()){
                        dataSaver.setLogin(response);
                    }
                    Intent ConfigActivity = new Intent(LoginActivity.this,ConfigActivity.class);
                    Bundle bundle =new Bundle();
                    bundle.putBoolean("RememberMe",checkBox.isChecked());
                    bundle.putSerializable("Configurations",response.serverDetail);
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
        ArrayAdapter<String> companyAdapter =  new ArrayAdapter<>(LoginActivity.this, com.google.android.material.R.layout.support_simple_spinner_dropdown_item, userNameItem);
        companyAdapter.setDropDownViewResource(androidx.appcompat.R.layout.support_simple_spinner_dropdown_item);
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