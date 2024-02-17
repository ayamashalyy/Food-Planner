package com.example.food_planner.DB;

import android.content.Context;

import com.example.food_planner.model.Meal;

import java.util.List;

import io.reactivex.rxjava3.core.CompletableObserver;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.schedulers.Schedulers;


public class MealsLocalDataSourceImp implements LocalSourceInterface{

    private Context context ;
    private MealDAO mealDAO;
    private Observable<List<Meal>> storedMeals;

    public MealsLocalDataSourceImp(Context context) {
        this.context = context;
        AppDataBase db =AppDataBase.getInstance(context.getApplicationContext());
        mealDAO = db.mealsDAO();
        storedMeals = mealDAO.getAllmeals();
    }

    private static  MealsLocalDataSourceImp repositry = null ;

    public static  MealsLocalDataSourceImp getInstance(Context context){
        if(repositry ==null){
            repositry = new MealsLocalDataSourceImp(context);
        }
        return repositry;
    }
    @Override
    public Observable<List<Meal>> getStoredmeals() {
        return storedMeals;
    }

    @Override
    public void delete(Meal meal) {

        mealDAO.deleteMeal(meal).subscribeOn(Schedulers.computation()).subscribe(new CompletableObserver() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onComplete() {

            }

            @Override
            public void onError(Throwable e) {

            }
        });

    }

    @Override
    public void insert(Meal meal) {
        mealDAO.insertMeal(meal).subscribeOn(Schedulers.computation()).subscribe(new CompletableObserver() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onComplete() {

            }

            @Override
            public void onError(Throwable e) {

            }
        });

    }

    @Override
    public void deleteAllMeals() {
        mealDAO.deleteAllMeals().subscribeOn(Schedulers.computation()).subscribe(new CompletableObserver() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onComplete() {

            }

            @Override
            public void onError(Throwable e) {

            }
        });

    }

    @Override
    public Observable<List<Meal>> getSutrdaydmeals() {
        return mealDAO.getSaturdaymeals();
    }

    @Override
    public Observable<List<Meal>> getSundaydmeals() {
        return mealDAO.getSundaymeals();
    }

    @Override
    public Observable<List<Meal>> getMondaymeals() {
        return mealDAO.getMondaymeals();
    }

    @Override
    public Observable<List<Meal>> getTusdaymeals() {
        return mealDAO.getTusdaymeals();
    }

    @Override
    public Observable<List<Meal>> getWednsdaymeals() {
        return mealDAO.getWednsdaymeals();
    }

    @Override
    public Observable<List<Meal>> getThursdaymeals() {
        return mealDAO.getThursdaymeals();
    }

    @Override
    public Observable<List<Meal>> getFridaymeals() {
        return mealDAO.getFridaymeals();
    }
}
