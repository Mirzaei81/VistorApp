package org.visitor.Service.view;

import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.widget.ViewPager2;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TableLayout;
import android.widget.TextView;


import com.google.android.material.tabs.TabLayout;

import org.alarmamir.R;
import org.visitor.Service.adapter.MainPagerAdapter;
import org.visitor.Service.presenter.model.ConfigResponse;
import org.visitor.Tools.Databace.DataSaver;

import java.util.Locale;

public class MainActivity extends FragmentActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        Bundle b = getIntent().getExtras();
        DataSaver dataSaver = new DataSaver(this);
        if(!dataSaver.hasConfig()||!dataSaver.hasLogin()) {
            if(b==null){
                startActivity(new Intent(MainActivity.this,LoginActivity.class));
                finish();
                return;
            }
            ConfigResponse config = dataSaver.getConfig();
            if(config==null || config.visitorName ==null){
                startActivity(new Intent(MainActivity.this,LoginActivity.class));
                finish();
                return;
            }
        }
        TabLayout tabLayout = findViewById(R.id.tab_layout);
        ViewPager2 viewPager2 = findViewById(R.id.pager);
        MainPagerAdapter adapter = new MainPagerAdapter(getSupportFragmentManager(), getLifecycle());
        TextView fullName = findViewById(R.id.personName);
        ConfigResponse config = dataSaver.getConfig();
        fullName.setText(String.format(new Locale("FA","IR"),"خوش امدید: %s ",config.visitorName));
        viewPager2.setAdapter(adapter);
        viewPager2.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                tabLayout.selectTab(tabLayout.getTabAt(position%3));
            }
        });
        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager2.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {}

            @Override
            public void onTabReselected(TabLayout.Tab tab) {}
        });

    }
}
