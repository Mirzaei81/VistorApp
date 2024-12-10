package org.visitor.Service.view;

import static android.content.ContentValues.TAG;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.coordinatorlayout.widget.CoordinatorLayout;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.text.method.DigitsKeyListener;
import android.util.Log;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
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
import org.visitor.Service.presenter.model.UserResponse;
import org.visitor.WebServiceNetwork;
import org.visitor.userModel.ResultUserPresenter;
import org.visitor.Tools.Databace.DataSaver;

public class LoginActivity extends AppCompatActivity {
    private Api busApi;
    private EditText username,password ;
    private ImageView login;

    private ImageView settings;
    private DataSaver dataSaver;
    private Snackbar snackbar;
    private AlertDialog.Builder builder;
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
//        if (dataSaver.hasLogin()) {
//            goMain();
//            finish();
//        }
        busApi = new Api(this,dataSaver);
        username=findViewById(R.id.username);
        password=findViewById(R.id.password);
        login=findViewById(R.id.login);
        settings=findViewById(R.id.Settings);
        settings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
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
            }
        });
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
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
                        dataSaver.setLogin(response);
                        Intent ConfigActivity = new Intent(LoginActivity.this,ConfigActivity.class);
                        Bundle bundle =new Bundle();
                        bundle.putSerializable("Configurations",response.serverDetail);
                        ConfigActivity.putExtras(bundle);
                        startActivity(ConfigActivity);
                    }
                };
                busApi.login(username.getText().toString(),password.getText().toString(),resultLoginPresenter);
            }
        });
    }
    private void goMain() {
        Intent intent=new Intent(LoginActivity.this, MainKalaActivity.class);
        startActivity(intent);
    }
}