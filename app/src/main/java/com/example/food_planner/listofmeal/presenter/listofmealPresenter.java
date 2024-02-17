package com.example.food_planner.listofmeal.presenter;

import android.util.Log;

import com.example.food_planner.listofmeal.view.listofmealView;
import com.example.food_planner.model.Area;
import com.example.food_planner.model.Category;
import com.example.food_planner.model.Ingredient;
import com.example.food_planner.model.Meal;
import com.example.food_planner.model.MealResponse;
import com.example.food_planner.network.MealsRemoteDataSourceImp;
import com.example.food_planner.network.NetworkCallback;

import java.util.List;


public class listofmealPresenter implements listofmealPressenterInterface, NetworkCallback {
    MealsRemoteDataSourceImp remoteDataSourceImp;
    listofmealView list;
    private static final String TAG = "listofmealPresenter";

    public listofmealPresenter(MealsRemoteDataSourceImp remoteDataSourceImp, listofmealView list) {
        this.remoteDataSourceImp = remoteDataSourceImp;
        this.list = list;
    }


    @Override
    public void onSuccessResult(List<Meal> meals) {

    }

    @Override
    public void onMealSuccessResult(Meal meal) {

    }

    @Override
    public void onSuccessResultCategory(List<Category> categories) {

    }

    @Override
    public void onSuccessResultArea(List<Area> areas) {

    }

    @Override
    public void onSuccessResultIngredient(List<Ingredient> ingredients) {

    }

    @Override
    public void onFailureResult(String errorMsg) {
        Log.i(TAG, "onFailureResult: ");
    }

    @Override
    public void onSuccessResultListOfMeals(MealResponse meals) {
        list.showlistofmeal(meals);
        Log.i(TAG, "onSuccessResultListOfMeals: " + meals.meals.size());
    }

    @Override
    public void getlistofMeals(String categoryName) {
        remoteDataSourceImp.ListOfCategoryMealsCall(this,categoryName);

    }
}


