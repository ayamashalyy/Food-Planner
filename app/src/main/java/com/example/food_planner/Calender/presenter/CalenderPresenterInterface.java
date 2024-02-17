package com.example.food_planner.Calender.presenter;

import com.example.food_planner.model.Meal;

public interface CalenderPresenterInterface {
    public void getSaturdayMeals();
    public void getSundayMeals();
    public void getMondayMeals();
    public void getTusdayMeals();
    public void getWednsdayMeals();
    public void getThursdayMeals();
    public void getFridayMeals();
    public void remove(Meal meal);
}
