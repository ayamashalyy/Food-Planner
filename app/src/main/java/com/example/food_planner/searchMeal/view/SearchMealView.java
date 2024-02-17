package com.example.food_planner.searchMeal.view;

import com.example.food_planner.model.Meal;

import java.util.List;

public interface SearchMealView {
    public void showSearchMeal(List<Meal> meals);
    public  void getMeal(Meal meal);

}
