package com.example.demo;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import me.relex.circleindicator.CircleIndicator;

public class ManHinhChinh extends AppCompatActivity {
    private ViewPager viewPager;
    private CircleIndicator circleIndicator;
    private PhotoAdapter2 photoAdapter2;
    private List<Photo2> mListPhoto2;
    private Timer mTimer;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_man_hinh_chinh);


        viewPager = findViewById(R.id.viewpagerSlider);
        circleIndicator = findViewById(R.id.circle_indicatorslider);
        mListPhoto2 = getListPhoto2();

        photoAdapter2 = new PhotoAdapter2(this, mListPhoto2);
        viewPager.setAdapter(photoAdapter2);

        circleIndicator.setViewPager(viewPager);
        photoAdapter2.registerDataSetObserver(circleIndicator.getDataSetObserver());

//
        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation);

        bottomNavigationView.setSelectedItemId(R.id.menu_home);

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.menu_home:
                        return true;
                    case R.id.menu_favorite:
                        startActivity(new Intent(getApplicationContext(), ThemTruyen.class));
                        overridePendingTransition(0,0);

                        return true;
                    case R.id.menu_audio:
                        startActivity(new Intent(getApplicationContext(), MainActivity4.class));
                        overridePendingTransition(0,0);

                        return true;
                }
                return false;
            }

        });

        autoSlideImage();

    }

    private List<Photo2> getListPhoto2() {
        List<Photo2> list = new ArrayList<>();
        list.add(new Photo2(R.drawable.sl1));
        list.add(new Photo2(R.drawable.sl2));
        list.add(new Photo2(R.drawable.sl3));

        return list;
    }

    private void autoSlideImage() {
        if (mListPhoto2 == null || mListPhoto2.isEmpty() || viewPager == null) {
            return;
        }
        if (mTimer == null) {
            mTimer = new Timer();
        }
        mTimer.schedule(new TimerTask() {
            @Override
            public void run() {
                new Handler(Looper.getMainLooper()).post(new Runnable() {
                    @Override
                    public void run() {
                        int currentItem = viewPager.getCurrentItem();
                        int totalItem = mListPhoto2.size() - 1;
                        if (currentItem < totalItem) {
                            currentItem++;
                            viewPager.setCurrentItem(currentItem);
                        } else {
                            viewPager.setCurrentItem(0);

                        }
                    }
                });
            }
        }, 500, 3000);
    }
}