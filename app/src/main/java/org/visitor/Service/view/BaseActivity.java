package org.visitor.Service.view;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.text.InputType;
import android.text.method.DigitsKeyListener;
import android.util.Log;
import android.util.SparseArray;
import android.widget.EditText;
import android.widget.ImageView;

import androidx.annotation.LayoutRes;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.snackbar.Snackbar;

import org.alarmamir.R;
import org.visitor.Tools.Databace.DataSaver;

public abstract class BaseActivity extends AppCompatActivity {
    private AlertDialog.Builder builder;
    private DataSaver dataSaver;
    public Snackbar snackbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d("BASE_ACTIVTY","Creating Base");
        setContentView(getLayoutResource());
        dataSaver = new DataSaver(this);
        ImageView Settings =  findViewById(R.id.Settings);
        Settings.setOnClickListener(view -> {
            builder = new AlertDialog.Builder(BaseActivity.this);
            builder.setTitle("Server Address");
            final EditText input = new EditText(BaseActivity.this);
            input.setKeyListener(DigitsKeyListener.getInstance("09123456789."));
            builder.setView(input);
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
                   snackbar.setText("Ip address is invalid please");
                   snackbar.show();
                }
            });
            builder.setNegativeButton("Cancel", (dialog, which) -> dialog.cancel());
            builder.show();
        });
    }
    protected  abstract @LayoutRes int getLayoutResource();
    protected void OnPositiveClick(){}
}
