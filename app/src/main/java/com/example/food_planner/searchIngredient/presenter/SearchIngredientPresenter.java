package com.example.food_planner.searchIngredient.presenter;

import com.example.food_planner.searchIngredient.view.SearchIngredientView;
import com.example.food_planner.model.Area;
import com.example.food_planner.model.Category;
import com.example.food_planner.model.Ingredient;
import com.example.food_planner.model.Meal;
import com.example.food_planner.model.MealResponse;
import com.example.food_planner.network.MealsRemoteDataSourceImp;
import com.example.food_planner.network.NetworkCallback;

import java.util.List;

public class SearchIngredientPresenter implements SearchIngredientPresenterInterface, NetworkCallback {
    MealsRemoteDataSourceImp remoteDataSourceImp;

    SearchIngredientView searchIngredientView;
    public SearchIngredientPresenter(MealsRemoteDataSourceImp remoteDataSourceImp, SearchIngredientView searchIngredientView) {
        this.remoteDataSourceImp = remoteDataSourceImp;
        this.searchIngredientView = searchIngredientView;
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
        searchIngredientView.showSearchIngredient(ingredients);

    }

    @Override
    public void onFailureResult(String errorMsg) {

    }

    @Override
    public void onSuccessResultListOfMeals(MealResponse meals) {

    }

    @Override
    public void getSearchIngredient() {
        remoteDataSourceImp.ingredientMealCall(this);

    }
}
