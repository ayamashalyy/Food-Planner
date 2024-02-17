package com.example.food_planner;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.food_planner.Calender.view.calenderFragment;
import com.example.food_planner.Favorite.view.favoriteFragment;
import com.example.food_planner.Home.view.homeFragment;
import com.example.food_planner.databinding.ActivityMainBinding;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity {
    ActivityMainBinding binding;
    FirebaseAuth firebaseAuth;
    public static final int HOME = R.id.home;
    public static final int SEARCH = R.id.search;
    public static final int FAV = R.id.fav;
    public static final int CALENDAR = R.id.calender;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        binding.bottomNavigationView.setItemIconTintList(null);
        replaceFragment(new homeFragment());
        binding.bottomNavigationView.setBackground(null);
        firebaseAuth = FirebaseAuth.getInstance();
        FirebaseUser user = firebaseAuth.getCurrentUser();
        binding.bottomNavigationView.setOnItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                if (item.getItemId() == HOME) {
                    replaceFragment(new homeFragment());
                } else if (item.getItemId() == SEARCH) {
                    replaceFragment(new searchFragment());
                } else if (item.getItemId() == FAV) {
                    if (user != null) {
                        replaceFragment(new favoriteFragment());
                    } else {
                        Toast.makeText(MainActivity.this, "You must log in to enjoy this feature", Toast.LENGTH_SHORT).show();
                        replaceFragment(new homeFragment());
                    }
                    return true;

                } else if (item.getItemId() == CALENDAR) {
                    if (user != null) {
                        replaceFragment(new calenderFragment());
                    } else {
                        Toast.makeText(MainActivity.this, "You must log in to enjoy this feature", Toast.LENGTH_SHORT).show();
                        replaceFragment(new homeFragment());
                    }
                    return true;

                }

                return true;
            }
        });

    }
    private void replaceFragment(Fragment fragment) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.frame_layout, fragment);
        fragmentTransaction.commit();
    }
    }
