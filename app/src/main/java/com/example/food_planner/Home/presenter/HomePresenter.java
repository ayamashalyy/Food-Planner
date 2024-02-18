package com.example.food_planner.Home.presenter;

import android.util.Log;
import com.example.food_planner.Home.view.HomeView;
import com.example.food_planner.model.Area;
import com.example.food_planner.model.Category;
import com.example.food_planner.model.Ingredient;
import com.example.food_planner.model.Meal;
import com.example.food_planner.model.MealResponse;
import com.example.food_planner.network.MealsRemoteDataSourceImp;
import com.example.food_planner.network.NetworkCallback;

import java.util.List;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.Observer;
import io.reactivex.rxjava3.disposables.Disposable;

public class HomePresenter implements HomePressenterInterface, NetworkCallback {
    MealsRemoteDataSourceImp remoteDataSourceImp;
    private static final String TAG = "HomePresenter";
    HomeView homeView;

    public HomePresenter(MealsRemoteDataSourceImp remoteDataSourceImp,HomeView homeView) {
        this.remoteDataSourceImp = remoteDataSourceImp;
        this.homeView = homeView;
    }

    @Override
    public void getDailyRandomMeals() {

        Observable<MealResponse> observable =remoteDataSourceImp.getCategories();
        observable.observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<MealResponse>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {
                    }

                    @Override
                    public void onNext(@NonNull MealResponse categoryResponse) {
                        homeView.showCategoryMeal(categoryResponse.categories);
                    }
                    @Override
                    public void onError(@NonNull Throwable e) {
                        homeView.showErrorMsg(e.getMessage());

                    }
                    @Override
                    public void onComplete() {
                    }
        });
    }


@Override
    public void getRandomMeal() {
        Observable<MealResponse> observable = remoteDataSourceImp.randomMealCall();
        observable.observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<MealResponse>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {
                    }

                    @Override
                    public void onNext(@NonNull MealResponse mealResponse) {
                        Log.i(TAG, "onNext: "+mealResponse.meals.size());
                        homeView.showRandomMeals(mealResponse.meals.get(0));

                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        homeView.showErrorMsg(e.getMessage());

                    }
                    @Override
                    public void onComplete() {
                    }
       });
}

    @Override
    public void onSuccessResult(List<Meal> meals) {
      homeView.showRandomMeals(meals.get(0));
    }

    @Override
    public void onMealSuccessResult(Meal meal) {

    }

    @Override
    public void onSuccessResultCategory(List<Category> categories) {

        homeView.showCategoryMeal(categories);
    }

    @Override
    public void onSuccessResultArea(List<Area> areas) {

    }

    @Override
    public void onSuccessResultIngredient(List<Ingredient> ingredients) {

    }

    @Override
    public void onFailureResult(String errorMsg) {
        Log.i(TAG, "onFailureResult: ");
    }

    @Override
    public void onSuccessResultListOfMeals(MealResponse meals) {

    }
}
