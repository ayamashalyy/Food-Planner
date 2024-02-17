package com.example.food_planner.Common;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;

import com.example.food_planner.R;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.List;

public class onboardingScreen extends AppCompatActivity {
    private ViewPager screenPager;
    onboardingViewPagerAdapter adapter;
    TabLayout layout;
    Button btnNext;
    int position = 0;
    Button btnGetStarted;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);


        if (restorePrefData()) {
            Intent mainActivity = new Intent(getApplicationContext(), SplashScreen.class);
            startActivity(mainActivity);
            finish();
        }

        setContentView(R.layout.activity_onboarding_screen);
        getSupportActionBar().hide();
        btnNext = findViewById(R.id.btn_next);
        btnGetStarted = findViewById(R.id.btn_get_start);

        layout = findViewById(R.id.tab_indicater);
        List<ScreenItems_onboarding> mList = new ArrayList<>();
        mList.add(new ScreenItems_onboarding("Delicious Food", "Let's eat some diet food while steak to cook ", R.drawable.img));
        mList.add(new ScreenItems_onboarding("Let's Eat...", "Food is really and truly the most effective medicine", R.drawable.w));
        mList.add(new ScreenItems_onboarding("Healthy & Tasty", "Eat today live another memorable day", R.drawable.s));
        screenPager = findViewById(R.id.screen_viewPager);
        adapter = new onboardingViewPagerAdapter(this, mList);
        screenPager.setAdapter(adapter);
        layout.setupWithViewPager(screenPager);
        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                position = screenPager.getCurrentItem();
                if (position < mList.size()) {
                    position++;
                    screenPager.setCurrentItem(position);
                }
                if (position == mList.size() - 1) {
                    loadLastScreen();
                }
            }
        });
        layout.addOnTabSelectedListener(new TabLayout.BaseOnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                if (tab.getPosition() == mList.size() - 1) {
                    loadLastScreen();
                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {
            }
        });
        btnGetStarted.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent mainActivity = new Intent(getApplicationContext(), SplashScreen.class);
                startActivity(mainActivity);
                savePrefsData();
                finish();
            }
        });



    }

    private boolean restorePrefData() {
        SharedPreferences pref = getApplicationContext().getSharedPreferences("myPrefs", MODE_PRIVATE);
        Boolean isIntroActivityOpnendBefore = pref.getBoolean("isOnboardingOpened", false);
        return isIntroActivityOpnendBefore;
    }

    private void savePrefsData() {
        SharedPreferences pref = getApplicationContext().getSharedPreferences("myPrefs", MODE_PRIVATE);
        SharedPreferences.Editor editor = pref.edit();
        editor.putBoolean("isOnboardingOpened", true);
        editor.commit();
    }

    private void loadLastScreen() {

        btnNext.setVisibility(View.INVISIBLE);
        btnGetStarted.setVisibility(View.VISIBLE);
        layout.setVisibility(View.INVISIBLE);
    }
}