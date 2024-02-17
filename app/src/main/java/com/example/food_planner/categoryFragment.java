package com.example.food_planner;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.food_planner.model.Category;
import com.example.food_planner.model.Meal;

public class categoryFragment extends Fragment {
    View view;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view =  inflater.inflate(R.layout.fragment_category, container, false);
        if(getArguments()!=null){
            Category category= (Category) getArguments().getSerializable("category");
            Log.e("Category","Category is: "+category.getStrCategory() );
        }
        return view;
    }
}
