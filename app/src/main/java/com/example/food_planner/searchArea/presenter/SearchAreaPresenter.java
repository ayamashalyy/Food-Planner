package com.example.food_planner.searchArea.presenter;

import android.util.Log;

import com.example.food_planner.model.Area;
import com.example.food_planner.model.Category;
import com.example.food_planner.model.Ingredient;
import com.example.food_planner.model.Meal;
import com.example.food_planner.model.MealResponse;
import com.example.food_planner.network.MealsRemoteDataSourceImp;
import com.example.food_planner.network.NetworkCallback;
import com.example.food_planner.searchArea.view.SearchAreaView;
import com.example.food_planner.searchCategory.presenter.SearchCategoryPresenterInterface;
import com.example.food_planner.searchCategory.view.SearchCategoryView;

import java.util.List;

import retrofit2.http.Query;

public class SearchAreaPresenter implements SearchAreaPresenterInterface, NetworkCallback {
    MealsRemoteDataSourceImp remoteDataSourceImp;

    SearchAreaView searchAreaView;
    public SearchAreaPresenter(MealsRemoteDataSourceImp remoteDataSourceImp, SearchAreaView searchAreaView) {
        this.remoteDataSourceImp = remoteDataSourceImp;
        this.searchAreaView = searchAreaView;
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
        searchAreaView.showSearchArea(areas);

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
    public void getSearchArea() {
      remoteDataSourceImp.areaMealCall(this);

    }
}
