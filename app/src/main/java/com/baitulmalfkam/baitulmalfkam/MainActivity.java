package com.baitulmalfkam.baitulmalfkam;

import android.support.annotation.NonNull;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import com.baitulmalfkam.baitulmalfkam.Fragment.Akun;
import com.baitulmalfkam.baitulmalfkam.Fragment.DonasiFragment;
import com.baitulmalfkam.baitulmalfkam.Fragment.HomeFragment;
import com.baitulmalfkam.baitulmalfkam.Fragment.LayananFragment;
import com.baitulmalfkam.baitulmalfkam.Fragment.MajalahFragment;
import com.ittianyu.bottomnavigationviewex.BottomNavigationViewEx;

public class MainActivity extends AppCompatActivity {
    private BottomNavigationViewEx bnve;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        bnve = (BottomNavigationViewEx)findViewById(R.id.bottom_bar);
        bnve.enableAnimation(false);
        bnve.enableItemShiftingMode(false);
        bnve.enableShiftingMode(false);

        final FragmentManager fm = getSupportFragmentManager();
        fm.beginTransaction().add(R.id.main_content, new HomeFragment(),"1").commit();

        bnve.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
    }

    private BottomNavigationViewEx.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener = new BottomNavigationViewEx.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            FragmentManager fm = getSupportFragmentManager();
            FragmentTransaction ft =fm.beginTransaction();

            switch (item.getItemId()){
                case R.id.item_home:
                    ft.replace(R.id.main_content, new HomeFragment());
                    ft.addToBackStack(null).commit();
                    return true;

                case R.id.item_majalah:
                    ft.replace(R.id.main_content, new MajalahFragment());
                    ft.addToBackStack(null).commit();
                    return true;

                case R.id.item_donasi:
                    ft.replace(R.id.main_content, new DonasiFragment());
                    ft.addToBackStack(null).commit();

                    return true;

                case R.id.item_layanan:
                    ft.replace(R.id.main_content, new LayananFragment());
                    ft.addToBackStack(null).commit();
                    return true;
                case R.id.item_akun:
                    ft.replace(R.id.main_content, new Akun());
                    ft.addToBackStack(null).commit();
                    return true;
            }
            return false;
        }
    };
}


