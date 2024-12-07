package org.visitor.Service.view;

import static android.content.ContentValues.TAG;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ViewFlipper;

import org.alarmamir.R;
import org.visitor.Api;
import org.visitor.KeyConst;

import org.visitor.ResponseUser;
import org.visitor.Service.presenter.model.UserResponse;
import org.visitor.userModel.ResultUserPresenter;
import org.visitor.Service.presenter.model.SendLogin;
import org.visitor.Tools.Databace.DataSaver;

public class LoginActivity extends AppCompatActivity {
    private Api busApi;
    private EditText username,password ;
    private TextView txtTitle;
    private ImageView login;
    private SendLogin sendLogin;
    private ProgressBar loading;
    private ViewFlipper viewFlipper;
    int CHOOSE_ORIGIN_PAGE = 0;
    int CHOOSE_DEST_PAGE = 1;
    private DataSaver dataSaver;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        init();
    }
    public  void init(){
        dataSaver =new DataSaver(LoginActivity.this);
        if (dataSaver.hasLogin()) {
            goMain();
            finish();
        }
        busApi = new Api(this,dataSaver);
        username=findViewById(R.id.username);
        password=findViewById(R.id.password);
        login=findViewById(R.id.login);
        loading=findViewById(R.id.loading);
        viewFlipper = findViewById(R.id.viewflipper);
        txtTitle=findViewById(R.id.txtTitle);


        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (viewFlipper.getDisplayedChild() == CHOOSE_ORIGIN_PAGE) {
                    sendLogin=new SendLogin();
                    sendLogin.setKey(KeyConst.key);
                    sendLogin.setStep(KeyConst.register);
                    sendLogin.setMobile(username.getText().toString());
                //    busApi.searchMobileLogin(resultPresenterMobileLogin,sendLogin);
                }else if (viewFlipper.getDisplayedChild() == CHOOSE_DEST_PAGE){
                    txtTitle.setText(R.string.confirm_your_number);
                    sendLogin=new SendLogin();
                    sendLogin.setKey(KeyConst.key);
                    sendLogin.setStep(KeyConst.step2);
                    sendLogin.setMobile(username.getText().toString());
                    sendLogin.setVerificationCode(password.getText().toString());
                //    busApi.searchCodeLogin(resultPresenterCodeLogin,sendLogin);
                }

            }
        });
    }
    private void goMain() {
        Intent intent=new Intent(LoginActivity.this, MainMoshtariActivity.class);
        startActivity(intent);
    }
    private void SwitchLeft(int page) {
        viewFlipper.setOutAnimation(AnimationUtils.loadAnimation(LoginActivity.this, R.anim.slide_in_left));
        viewFlipper.setInAnimation(AnimationUtils.loadAnimation(LoginActivity.this, R.anim.slide_in_left));
        viewFlipper.setDisplayedChild(page);
    }


    private void SwitchRight(int page) {
        viewFlipper.setInAnimation(AnimationUtils.loadAnimation(LoginActivity.this, R.anim.slide_from_right));
        viewFlipper.setOutAnimation(AnimationUtils.loadAnimation(LoginActivity.this, R.anim.slide_from_right));
        viewFlipper.setDisplayedChild(page);

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        if (viewFlipper.getDisplayedChild() == CHOOSE_ORIGIN_PAGE) {
           finish();
        }else if (viewFlipper.getDisplayedChild() == CHOOSE_DEST_PAGE){
            SwitchLeft (CHOOSE_ORIGIN_PAGE);
            txtTitle.setText(R.string.number_mobile_your);
        }
    }
}