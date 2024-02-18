package com.example.food_planner.Home.presenter;

import com.example.food_planner.model.MealResponse;

import io.reactivex.rxjava3.core.Observable;

public interface HomePressenterInterface {
    public void getDailyRandomMeals();

    void getRandomMeal();
}
