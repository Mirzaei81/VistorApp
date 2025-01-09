package org.visitor.Service.view;


import android.annotation.SuppressLint;
import android.os.Bundle;
import android.text.InputType;
import android.text.method.DigitsKeyListener;
import android.util.Log;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;

import androidx.annotation.LayoutRes;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.snackbar.Snackbar;

import org.alarmamir.R;
import org.visitor.Tools.Databace.DataSaver;

import java.util.Locale;

public abstract class BaseActivity extends AppCompatActivity {
    private AlertDialog.Builder builder;
    private DataSaver dataSaver;
    public Snackbar snackbar;


    @SuppressLint("NewApi")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutResource());
        dataSaver = new DataSaver(this);
        ImageView Settings =  findViewById(R.id.Settings);
        ImageView backBtn = findViewById(R.id.imgBack);
        backBtn.setOnClickListener(view -> {
                    onBackPressed();
                }
        );
        Settings.setOnClickListener(view -> {
            builder = new AlertDialog.Builder(BaseActivity.this);
            builder.setTitle("App settings");
            LinearLayout layout = new LinearLayout(BaseActivity.this);
            final EditText duration = new EditText(BaseActivity.this);
            duration.setHint("snackbar duration (Second)");
            duration.setInputType(InputType.TYPE_CLASS_NUMBER);
            layout.setOrientation(LinearLayout.VERTICAL);
            final EditText input = new EditText(BaseActivity.this);
            input.setKeyListener(DigitsKeyListener.getInstance("09123456789."));
            input.setHint("Host Address");
            duration.setText(String.format(Locale.forLanguageTag("en-us"),"%d",dataSaver.getDuraiton()/1000));
            layout.addView(input);
            layout.addView(duration);
            builder.setView(layout);
            builder.setPositiveButton("OK", (dialog, which) -> {
                String ipValidator = "^((25[0-5]|(2[0-4]|1\\d|[1-9]|)\\d)\\.?\\b){4}$";
                String HostAddress = input.getText().toString();
                if(HostAddress.matches(ipValidator)){
                    try{
                        dataSaver.setHost(input.getText().toString());
                    }catch (Exception e){
                        snackbar.setText(e.toString());
                        snackbar.show();
                    }
                    OnPositiveClick();
                }else{
                    if(!HostAddress.isEmpty()) {
                        snackbar.setText("Ip address is invalid please");
                        snackbar.show();
                    }
                }
                String snackBarDurationText= duration.getText().toString();
                if(!snackBarDurationText.isEmpty()){
                    int SnackBarDuration  =  Integer.parseInt(snackBarDurationText);
                    dataSaver.setDuration(SnackBarDuration*1000);
                }
            });
            builder.setNegativeButton("Cancel", (dialog, which) -> dialog.cancel());
            builder.show();
        });
    }
    protected  abstract @LayoutRes int getLayoutResource();
    protected void OnPositiveClick(){}
}
