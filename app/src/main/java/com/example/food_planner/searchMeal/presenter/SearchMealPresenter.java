package com.example.food_planner.searchMeal.presenter;

import android.util.Log;

import com.example.food_planner.model.Area;
import com.example.food_planner.model.Category;
import com.example.food_planner.model.Ingredient;
import com.example.food_planner.model.Meal;
import com.example.food_planner.model.MealResponse;
import com.example.food_planner.network.MealsRemoteDataSourceImp;
import com.example.food_planner.network.NetworkCallback;
import com.example.food_planner.searchMeal.view.SearchMealView;

import java.util.List;

public class SearchMealPresenter implements SearchMealPresenterInterface, NetworkCallback {
    MealsRemoteDataSourceImp remoteDataSourceImp;

    SearchMealView searchMealView;
    public SearchMealPresenter(MealsRemoteDataSourceImp remoteDataSourceImp, SearchMealView searchMealView) {
        this.remoteDataSourceImp = remoteDataSourceImp;
        this.searchMealView = searchMealView;
    }

    @Override
    public void onSuccessResult(List<Meal> meals) {
        searchMealView.showSearchMeal(meals);

    }

    @Override
    public void onMealSuccessResult(Meal meal) {
        searchMealView.getMeal(meal);
        Log.e("Meal Journey","onMealSuccessResult");
//        remoteDataSourceImp.mealDetails(this,meal.getIdMeal());
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

    }

    @Override
    public void onSuccessResultListOfMeals(MealResponse meals) {

    }

    @Override
    public void getSearchMeal() {
        remoteDataSourceImp.searchMealCall(this);
    }

    @Override
    public void getlistofCategoryMeals(String categoryName) {
        remoteDataSourceImp.ListOfCategoryMealsCall(this,categoryName);
    }

    @Override
    public void getlistofIngredientMeals(String ingredient) {
        remoteDataSourceImp.ListOfIngredientMealsCall(this,ingredient);

    }

    @Override
    public void getlistofAreaMeals(String area) {
        remoteDataSourceImp.ListOfAreaMealsCall(this,area);

    }



}
