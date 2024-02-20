package com.example.food_planner.network;


import android.util.Log;

import com.example.food_planner.model.AreaResponse;
import com.example.food_planner.model.IngredientResponse;
import com.example.food_planner.model.MealResponse;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.schedulers.Schedulers;
import hu.akarnokd.rxjava3.retrofit.RxJava3CallAdapterFactory;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MealsRemoteDataSourceImp {
    private static final String TAG="RESPONSE";
    private static final String BASE_URL="https://www.themealdb.com/api/json/v1/1/";
    private static final String Area_BASE_URL="https://www.themealdb.com/api/json/v1/1/list.php?a=list";
    private static MealsRemoteDataSourceImp client=null;
    private MealService mealService;

    private MealsRemoteDataSourceImp(){
        Retrofit retrofit = new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
                .baseUrl(BASE_URL).build();
         mealService=retrofit.create(MealService.class);

    }

    public static MealsRemoteDataSourceImp getInstance(){
        if (client==null){
            client=new MealsRemoteDataSourceImp();
        }
        return client;
    }

public Observable<MealResponse> randomMealCall() {
    return mealService.getMeals()
            .subscribeOn(Schedulers.io());


}
    public Observable<MealResponse> getCategories() {
        return mealService.getCategories()
                .subscribeOn(Schedulers.io());
    }

    public void mealDetails(NetworkCallback networkCallback, String id) {

        if (mealService == null || networkCallback == null) {

        }

        Call<MealResponse> call = mealService.getMealData(id);

        call.enqueue(new Callback<MealResponse>() {
            @Override
            public void onResponse(Call<MealResponse> call, Response<MealResponse> response) {
                if (response.isSuccessful() && response.body() != null) {
                    networkCallback.onMealSuccessResult(response.body().meals.get(0));
                    Log.i(TAG, "onResponseeeeeee: " + response.body());
                }
            }

            @Override
            public void onFailure(Call<MealResponse> call, Throwable t) {
                Log.e("Meal Journey", "OnFailure: " + t.getMessage());
                networkCallback.onFailureResult(t.getMessage());
            }
        });
    }



    public void searchMealCall(NetworkCallback networkCallback){
        Call<MealResponse>call=mealService.getAllMeals();
        call.enqueue(new Callback<MealResponse>() {
            @Override
            public void onResponse(Call<MealResponse> call, Response<MealResponse> response) {
                if (response.isSuccessful() && response.body() != null) {
                    networkCallback.onSuccessResult(response.body().meals);
                    Log.i(TAG, "onResponseeeeeee: " + response.body());
                }
            }
            @Override
            public void onFailure(Call<MealResponse> call, Throwable t) {
                networkCallback.onFailureResult(t.getMessage());

            }
        });

    }

    public void areaMealCall(NetworkCallback networkCallback) {
        Call<AreaResponse> call = mealService.getAreas();
        call.enqueue(new Callback<AreaResponse>() {
            @Override
            public void onResponse(Call<AreaResponse> call, Response<AreaResponse> response) {
                if (response.isSuccessful() && response.body() != null) {

                    networkCallback.onSuccessResultArea(response.body().meals);
                }
            }

            @Override
            public void onFailure(Call<AreaResponse> call, Throwable t) {
                networkCallback.onFailureResult(t.getMessage());

            }
        });
    }

    public void ingredientMealCall(NetworkCallback networkCallback) {
        Call<IngredientResponse> call = mealService.getIngredientsList();
        call.enqueue(new Callback<IngredientResponse>() {
            @Override
            public void onResponse(Call<IngredientResponse> call, Response<IngredientResponse> response) {
                if (response.isSuccessful() && response.body() != null) {

                    networkCallback.onSuccessResultIngredient(response.body().meals);
                }
            }

            @Override
            public void onFailure(Call<IngredientResponse> call, Throwable t) {
                networkCallback.onFailureResult(t.getMessage());

            }
        });
    }

    public void ListOfCategoryMealsCall(NetworkCallback networkCallback, String categoryName){
        Call<MealResponse>call=mealService.getCategoryMealsList(categoryName);
        call.enqueue(new Callback<MealResponse>() {
            @Override
            public void onResponse(Call<MealResponse> call, Response<MealResponse> response) {
                if (response.isSuccessful() && response.body() != null) {
                    networkCallback.onSuccessResult(response.body().meals);
                    Log.i(TAG, "onResponseeeeeee: " + response.body());
                }
            }
            @Override
            public void onFailure(Call<MealResponse> call, Throwable t) {
                networkCallback.onFailureResult(t.getMessage());

            }
        });

    }




    public void ListOfIngredientMealsCall(NetworkCallback networkCallback,String ingredientName){
        Call<MealResponse>call=mealService.getIngredientMealsList(ingredientName);
        call.enqueue(new Callback<MealResponse>() {
            @Override
            public void onResponse(Call<MealResponse> call, Response<MealResponse> response) {
                if (response.isSuccessful() && response.body() != null) {
                    networkCallback.onSuccessResult(response.body().meals);
                    Log.i(TAG, "onResponseeeeeee: " + response.body());
                }
            }
            @Override
            public void onFailure(Call<MealResponse> call, Throwable t) {
                networkCallback.onFailureResult(t.getMessage());

            }
        });

    }

    public void ListOfAreaMealsCall(NetworkCallback networkCallback,String area){
        Call<MealResponse>call=mealService.getAreaMealsList(area);
        call.enqueue(new Callback<MealResponse>() {
            @Override
            public void onResponse(Call<MealResponse> call, Response<MealResponse> response) {
                if (response.isSuccessful() && response.body() != null) {
                    networkCallback.onSuccessResult(response.body().meals);
                    Log.i(TAG, "onResponseeeeeee: " + response.body());
                }
            }
            @Override
            public void onFailure(Call<MealResponse> call, Throwable t) {
                networkCallback.onFailureResult(t.getMessage());

            }
        });

    }


    }
