package com.example.food_planner.Home.view;


import com.airbnb.lottie.L;
import com.example.food_planner.model.Category;
import com.example.food_planner.model.Meal;

import java.util.ArrayList;
import java.util.List;

public interface HomeView {
    public void showRandomMeals(Meal meal);
    public void showCategoryMeal(List<Category>categories);

}
