package com.example.food_planner.searchMeal.presenter;

import com.example.food_planner.model.Meal;

public interface SearchMealPresenterInterface {
    public void getSearchMeal();

    public void getlistofCategoryMeals(String categoryName);

    public void getlistofIngredientMeals(String ingredient);

    public void getlistofAreaMeals(String area);



}
