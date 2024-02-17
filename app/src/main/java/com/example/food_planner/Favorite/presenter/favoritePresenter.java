package com.example.food_planner.Favorite.presenter;

import android.util.Log;

import com.example.food_planner.DB.LocalSourceInterface;
import com.example.food_planner.DB.MealsLocalDataSourceImp;
import com.example.food_planner.Favorite.view.favoriteView;
import com.example.food_planner.model.Meal;

import java.util.List;

import io.reactivex.rxjava3.core.Observable;

public class favoritePresenter implements favoritePresenterInterface{
    favoriteView favorite_View;

    MealsLocalDataSourceImp Repo;



    public favoritePresenter( MealsLocalDataSourceImp repo) {
        Repo = repo;
    }

    @Override
    public void getFav() {

    }

    @Override
    public void remove(Meal meal) {
    Repo.delete(meal);
    }

    @Override
    public void addMeal(Meal meal) {
        Repo.insert(meal);
        Log.i("TAG", "addMeal: " + meal.getStrMeal());
    }

    @Override
    public Observable<List<Meal>> getMeals() {
      return Repo.getStoredmeals();

    }
}
