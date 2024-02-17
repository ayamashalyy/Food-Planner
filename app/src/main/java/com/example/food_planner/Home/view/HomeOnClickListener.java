package com.example.food_planner.Home.view;


import com.example.food_planner.model.Category;
import com.example.food_planner.model.Meal;

public interface HomeOnClickListener {
    void onAddToFavorite(Meal meal);
    void showMealDetails(Meal meal);
    void showCategoryDetails(Category category);

}
