package com.example.yrj.gaoxiaobang1;

import android.app.FragmentManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    ViewPager mainActivityViewPager;
    MainActivityAdapter adapter;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    mainActivityViewPager.setCurrentItem(0);
                    break;
                case R.id.navigation_dashboard:
                    mainActivityViewPager.setCurrentItem(1);
                    break;
                case R.id.navigation_notifications:
                    mainActivityViewPager.setCurrentItem(2);
                    break;
            }
            return true;
        }
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        getSupportActionBar().setCustomView(R.layout.custom_actionbar);
        final BottomNavigationView navigation =findViewById(R.id.navigation);
        FragmentManager fManager=getFragmentManager();
        mainActivityViewPager=findViewById(R.id.main_viewpager);
        adapter=new MainActivityAdapter(getSupportFragmentManager());
        adapter.addFragment(new PcFragment());
        adapter.addFragment(new JzFragment());
        adapter.addFragment(new JdFragment());
        mainActivityViewPager.setAdapter(adapter);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        mainActivityViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            }
            @Override
            public void onPageSelected(int position) {
                navigation.getMenu().getItem(position).setChecked(true);
            }
            @Override
            public void onPageScrollStateChanged(int state) {
            }
        });
    }
}
