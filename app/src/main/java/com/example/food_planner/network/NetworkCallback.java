package com.example.food_planner.network;

import com.example.food_planner.model.Area;
import com.example.food_planner.model.Category;
import com.example.food_planner.model.Ingredient;
import com.example.food_planner.model.Meal;
import com.example.food_planner.model.MealResponse;

import java.util.List;

public interface NetworkCallback {
    public void onSuccessResult(List<Meal> meals);

    public void onMealSuccessResult(Meal meal);
    public void onSuccessResultCategory(List<Category>categories);
    public void onSuccessResultArea(List<Area>areas);
    public void onSuccessResultIngredient(List<Ingredient>ingredients);
//    public void onSuccessResultSearchMeal(List<Meal> meals);
    public void onFailureResult(String errorMsg);
    public void onSuccessResultListOfMeals(MealResponse meals);

}
