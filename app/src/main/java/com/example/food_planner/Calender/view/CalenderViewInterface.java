package com.example.food_planner.Calender.view;

import com.example.food_planner.model.Meal;

import java.util.List;

import io.reactivex.rxjava3.core.Observable;

public interface CalenderViewInterface {

    public void showSaturdayMeals(Observable<List<Meal>> meals);
    public void showSundayMeals(Observable<List<Meal>> meals);
    public void showMondayMeals(Observable<List<Meal>> meals);
    public void showTusdayMeals(Observable<List<Meal>> meals);
    public void showWednsdayMeals(Observable<List<Meal>> meals);
    public void showThursdayMeals(Observable<List<Meal>> meals);
    public void showFridayMeals(Observable<List<Meal>> meals);
    public void removeMeal(Meal meal);
}
