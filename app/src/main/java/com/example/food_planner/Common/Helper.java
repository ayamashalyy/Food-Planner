package com.example.food_planner.Common;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.food_planner.R;
import com.example.food_planner.detailmeals.view.detailsMealFragment;
import com.example.food_planner.model.Area;
import com.example.food_planner.model.Category;
import com.example.food_planner.model.Ingredient;
import com.example.food_planner.model.Meal;

public class Helper {
    public static void showSelectedMeal(Meal meal, Bundle bundle, FragmentManager manager, Fragment fragment) {
        bundle.putSerializable("meal", meal);
        fragment.setArguments(bundle);
        FragmentTransaction trans = manager
                .beginTransaction()
                .replace(R.id.frame_layout, fragment);
        trans.addToBackStack(null);
        trans.commit();
    }

    public static void showSelectedCategory(Category category, Bundle bundle, FragmentManager manager, Fragment fragment) {
        bundle.putSerializable("category", category);
        fragment.setArguments(bundle);
        FragmentTransaction trans = manager
                .beginTransaction()
                .replace(R.id.frame_layout, fragment);
        trans.addToBackStack(null);
        trans.commit();
    }

    public static void showSelectedIngredient(Ingredient ingredient, Bundle bundle, FragmentManager manager, Fragment fragment) {
        bundle.putSerializable("ingredient", ingredient);
        fragment.setArguments(bundle);
        FragmentTransaction trans = manager
                .beginTransaction()
                .replace(R.id.frame_layout, fragment);
        trans.addToBackStack(null);
        trans.commit();
    }

    public static void showSelectedArea(Area area, Bundle bundle, FragmentManager manager, Fragment fragment) {
        bundle.putSerializable("area", area);
        fragment.setArguments(bundle);
        FragmentTransaction trans = manager
                .beginTransaction()
                .replace(R.id.frame_layout, fragment);
        trans.addToBackStack(null);
        trans.commit();
    }

    public static void getMealDetails(String id){


    }
}
