package com.example.food_planner.searchCategory.presenter;

import com.example.food_planner.model.Area;
import com.example.food_planner.model.Category;
import com.example.food_planner.model.Ingredient;
import com.example.food_planner.model.Meal;
import com.example.food_planner.model.MealResponse;
import com.example.food_planner.network.MealsRemoteDataSourceImp;
import com.example.food_planner.network.NetworkCallback;
import com.example.food_planner.searchCategory.view.SearchCategoryView;

import java.util.List;

public class SearchCategoryPresenter implements SearchCategoryPresenterInterface, NetworkCallback {
    MealsRemoteDataSourceImp remoteDataSourceImp;

    public SearchCategoryPresenter(MealsRemoteDataSourceImp remoteDataSourceImp, SearchCategoryView searchCategoryView) {
        this.remoteDataSourceImp = remoteDataSourceImp;
        this.searchCategoryView = searchCategoryView;
    }

    private static final String TAG = "SearchCategoryPresenter";
    SearchCategoryView searchCategoryView;
    @Override
    public void onSuccessResult(List<Meal> meals) {

    }

    @Override
    public void onMealSuccessResult(Meal meal) {

    }

    @Override
    public void onSuccessResultCategory(List<Category> categories) {
        searchCategoryView.showSearchCategory(categories);

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
    public void getSearchCategory() {
        remoteDataSourceImp.categoryMealCall(this);

    }
}
