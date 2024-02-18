package com.example.food_planner.network;


import com.example.food_planner.model.AreaResponse;
import com.example.food_planner.model.IngredientResponse;
import com.example.food_planner.model.MealResponse;

import io.reactivex.rxjava3.core.Observable;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;


public interface MealService {
    @GET("random.php")
    Observable<MealResponse> getMeals();

    @GET("lookup.php?i")
    Call<MealResponse> getMealData(@Query("i") String id);

    @GET("categories.php")
    Observable<MealResponse> getCategories();

    @GET("list.php?a=list")
    Call<AreaResponse> getAreas();
    @GET("list.php?i=list")
    Call<IngredientResponse> getIngredientsList();

    @GET("filter.php?c")
    Call<MealResponse> getCategoryMealsList(@Query("c") String catName);
    @GET("filter.php?i")
    Call<MealResponse> getIngredientMealsList(@Query("i") String ingredientName);


    @GET("filter.php?a")
    Call<MealResponse> getAreaMealsList(@Query("a") String area);

    @GET("search.php?s")
    Call<MealResponse> getAllMeals();

}
