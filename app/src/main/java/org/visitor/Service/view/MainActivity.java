package org.visitor.Service.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.viewpager2.widget.ViewPager2;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TableLayout;

import com.google.android.material.tabs.TabLayout;

import org.alarmamir.R;
import org.visitor.Service.adapter.MainPagerAdapter;
import org.visitor.Tools.Databace.DataSaver;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        DataSaver dataSaver = new DataSaver(this);
        if(!dataSaver.hasConfig()&&!dataSaver.hasLogin()) {
            startActivity(new Intent(MainActivity.this,LoginActivity.class));
            finish();
            return;
        }
        ViewPager2 viewPager2 = findViewById(R.id.pager);
        MainPagerAdapter adapter = new MainPagerAdapter(getSupportFragmentManager(), getLifecycle());
        viewPager2.setAdapter(adapter);

    }
}