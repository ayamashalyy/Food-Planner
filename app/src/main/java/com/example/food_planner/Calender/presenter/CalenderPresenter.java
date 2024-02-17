package com.example.food_planner.Calender.presenter;

import com.example.food_planner.Calender.view.CalenderViewInterface;
import com.example.food_planner.DB.MealsLocalDataSourceImp;
import com.example.food_planner.model.Meal;

public class CalenderPresenter implements CalenderPresenterInterface{
    CalenderViewInterface calenderViewInterface;
    MealsLocalDataSourceImp localDataSourceImp;
    public CalenderPresenter(CalenderViewInterface calenderViewInterface, MealsLocalDataSourceImp localDataSourceImp) {
        this.calenderViewInterface = calenderViewInterface;
        this.localDataSourceImp = localDataSourceImp;
    }


    @Override
    public void getSaturdayMeals() {
    calenderViewInterface.showSaturdayMeals(localDataSourceImp.getSutrdaydmeals());
    }

    @Override
    public void getSundayMeals() {
    calenderViewInterface.showSundayMeals(localDataSourceImp.getSundaydmeals());
    }

    @Override
    public void getMondayMeals() {
        calenderViewInterface.showMondayMeals(localDataSourceImp.getMondaymeals());

    }

    @Override
    public void getTusdayMeals() {
    calenderViewInterface.showTusdayMeals(localDataSourceImp.getTusdaymeals());
    }

    @Override
    public void getWednsdayMeals() {
        calenderViewInterface.showWednsdayMeals(localDataSourceImp.getWednsdaymeals());
    }

    @Override
    public void getThursdayMeals() {
    calenderViewInterface.showThursdayMeals(localDataSourceImp.getThursdaymeals());
    }

    @Override
    public void getFridayMeals() {
    calenderViewInterface.showFridayMeals(localDataSourceImp.getFridaymeals());
    }

    @Override
    public void remove(Meal meal) {
    localDataSourceImp.delete(meal);
    }
}
