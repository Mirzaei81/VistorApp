package org.visitor.Service.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import org.alarmamir.R;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        AppCompatButton btnPro=findViewById(R.id.btnPro);
        btnPro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(MainActivity.this,MainKalaActivity.class);
                startActivity(intent);
            }
        });
        AppCompatButton btnFac=findViewById(R.id.btnFac);
        btnFac.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(MainActivity.this,MainFactorActivity.class);
                startActivity(intent);
            }
        });
    }
}