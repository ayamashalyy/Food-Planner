package com.example.food_planner.Favorite.presenter;

import com.example.food_planner.model.Meal;

import java.util.List;

import io.reactivex.rxjava3.core.Observable;

public interface favoritePresenterInterface {
    public void getFav();
    public void remove(Meal meal);
    public void addMeal(Meal meal);
    public Observable<List<Meal>> getMeals();


}
