package com.example.food_planner.detailmeals.presenter;

import android.util.Log;

import com.example.food_planner.Home.presenter.HomePressenterInterface;
import com.example.food_planner.Home.view.HomeView;
import com.example.food_planner.detailmeals.view.detailMealView;
import com.example.food_planner.model.Area;
import com.example.food_planner.model.Category;
import com.example.food_planner.model.Ingredient;
import com.example.food_planner.model.Meal;
import com.example.food_planner.model.MealResponse;
import com.example.food_planner.network.MealsRemoteDataSourceImp;
import com.example.food_planner.network.NetworkCallback;

import java.util.List;

public class detailMealsPresenter implements detailMealPressenterInterface, NetworkCallback {
    MealsRemoteDataSourceImp remoteDataSourceImp;
    detailMealView detail;
    private static final String TAG = "detailMealsPresenter";
    public detailMealsPresenter(MealsRemoteDataSourceImp remoteDataSourceImp, detailMealView detail) {
        this.remoteDataSourceImp = remoteDataSourceImp;
        this.detail = detail;
    }

    @Override
    public void getdetailsMeals(Meal meal) {
        remoteDataSourceImp.randomMealCall(this);
    }

    @Override
    public void getMealID(String id) {
        remoteDataSourceImp.mealDetails(this,id);

    }




    @Override
    public void onSuccessResult(List<Meal> meals) {

    }

    @Override
    public void onMealSuccessResult(Meal meal) {
    detail.showdetailMeal(meal);
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

    }
}
