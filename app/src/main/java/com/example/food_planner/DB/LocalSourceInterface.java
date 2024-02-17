package com.example.food_planner.DB;

import com.example.food_planner.model.Meal;

import java.util.List;

import io.reactivex.rxjava3.core.Observable;

public interface LocalSourceInterface {
    public Observable<List<Meal>> getStoredmeals();
    public void delete(Meal meal);
    public void insert(Meal meal);
    public void deleteAllMeals();
    public Observable<List<Meal>> getSutrdaydmeals();
    public Observable<List<Meal>> getSundaydmeals();
    public Observable<List<Meal>> getMondaymeals();
    public Observable<List<Meal>> getTusdaymeals();
    public Observable<List<Meal>> getWednsdaymeals();
    public Observable<List<Meal>> getThursdaymeals();
    public Observable<List<Meal>> getFridaymeals();
}
