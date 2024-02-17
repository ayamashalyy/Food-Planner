package com.example.food_planner.Favorite.view;

import com.example.food_planner.model.Meal;

import java.util.List;

import io.reactivex.rxjava3.core.Observable;

public interface favoriteView {
    public void showFavorite(Observable<List<Meal>> meals);
    public void removeMeal(Meal meal);
}
