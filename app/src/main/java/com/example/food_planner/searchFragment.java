package com.example.food_planner;

import android.os.Bundle;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.food_planner.searchArea.view.searchAreaFragment;
import com.example.food_planner.searchCategory.view.search_categoryFragment;
import com.example.food_planner.searchIngredient.view.search_ingredientFragment;
import com.example.food_planner.searchMeal.view.search_mealFragment;


public class searchFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_search, container, false);
        Button searchMeal =view.findViewById(R.id.btn_allmeals);
        searchMeal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                search_mealFragment first = new search_mealFragment();
                FragmentTransaction transaction = getParentFragmentManager().beginTransaction();
                transaction.replace(R.id.frame_layout, first);
                transaction.addToBackStack(null);
                transaction.commit();
            }
        });
        Button searchIngredient =view.findViewById(R.id.btn_ingradiant);
        searchIngredient.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                search_ingredientFragment second = new search_ingredientFragment();
                FragmentTransaction transaction = getParentFragmentManager().beginTransaction();
                transaction.replace(R.id.frame_layout, second);
                transaction.addToBackStack(null);
                transaction.commit();
            }
        });
        Button searchArea =view.findViewById(R.id.btn_area);
        searchArea.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                searchAreaFragment three = new searchAreaFragment();
                FragmentTransaction transaction = getParentFragmentManager().beginTransaction();
                transaction.replace(R.id.frame_layout, three);
                transaction.addToBackStack(null);
                transaction.commit();
            }
        });
        Button searchCategory =view.findViewById(R.id.btn_gategory);
        searchCategory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                search_categoryFragment four = new  search_categoryFragment();
                FragmentTransaction transaction = getParentFragmentManager().beginTransaction();
                transaction.replace(R.id.frame_layout, four);
                transaction.addToBackStack(null);
                transaction.commit();
            }
        });
        return view;
    }

}