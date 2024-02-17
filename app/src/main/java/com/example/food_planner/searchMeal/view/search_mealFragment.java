package com.example.food_planner.searchMeal.view;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

import com.example.food_planner.Common.GetAllMealDetails;
import com.example.food_planner.Common.Helper;
import com.example.food_planner.R;
import com.example.food_planner.detailmeals.view.detailsMealFragment;
import com.example.food_planner.model.Area;
import com.example.food_planner.model.Category;
import com.example.food_planner.model.Ingredient;
import com.example.food_planner.model.Meal;
import com.example.food_planner.network.MealsRemoteDataSourceImp;
import com.example.food_planner.network.NetworkConnection;
import com.example.food_planner.searchMeal.presenter.SearchMealPresenter;
import com.example.food_planner.searchMeal.presenter.SearchMealPresenterInterface;

import java.util.ArrayList;
import java.util.List;


public class search_mealFragment extends Fragment implements SearchMealView, SearchMealOnClickLisener {
    View view;
    SearchMealAdapter searchMealAdapter;
    GetAllMealDetails mealDetails;
    SearchMealPresenterInterface searchMealPresenterInterface;
    EditText search;
    RecyclerView recyclerView;
    LinearLayoutManager linearLayoutManager;
    Category category;
    Ingredient ingredient;
    Area area;
    List<Meal> meals;
    Meal selectedMeal;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_search_meal, container, false);
        searchMealPresenterInterface = new SearchMealPresenter(MealsRemoteDataSourceImp.getInstance(), this);

        if (getArguments() != null)
            checkMealsData(getArguments().getString("FLAG"));
        else
            searchMealPresenterInterface.getSearchMeal();


        recyclerView = view.findViewById(R.id.rec_searchMeal);
        linearLayoutManager = new LinearLayoutManager(getActivity(), RecyclerView.VERTICAL, false);
        search = view.findViewById(R.id.search_meal);
        searchMealAdapter = new SearchMealAdapter(getActivity(), this);

        // Log.e("Trace Category", "Array List is: " + meals.size());

        searchMealAdapter.setList(meals);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(searchMealAdapter);

        searchMealAdapter.setOnMealCLick(meal -> {


            Helper.showSelectedMeal(

                    meal,
                    new Bundle(),
                    getFragmentManager(),
                    new detailsMealFragment()
            );
        });
        if (NetworkConnection.getConnectivity(getContext())) {
        search.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                List<Meal> filteredMeals = filterMeals(s.toString());
                searchMealAdapter.setList(filteredMeals);
                searchMealAdapter.notifyDataSetChanged();
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }
     else {
        Toast.makeText(getContext(), "There is no internet connection. Please reconnect and try again", Toast.LENGTH_SHORT).show();
    }
        return view;
    }
    private void checkMealsData(String flag){
            switch (flag) {
                case "category": {
                    category = (Category) getArguments().getSerializable("category");
                    searchMealPresenterInterface.getlistofCategoryMeals(category.getStrCategory());
                    break;
                }
                case "ingredient": {
                    ingredient = (Ingredient) getArguments().getSerializable("ingredient");
                    searchMealPresenterInterface.getlistofIngredientMeals(ingredient.getStrIngredient());
                    break;
                }
                case "area": {
                    area = (Area) getArguments().getSerializable("area");
                    searchMealPresenterInterface.getlistofAreaMeals(area.getStrArea());
                    break;
                }
                default: {
                    searchMealPresenterInterface.getSearchMeal();
                    break;
                }

            }
        }


    private List<Meal> filterMeals(String searchTerm) {
        List<Meal> filteredMeals = new ArrayList<>();
        if (meals != null) {
            for (Meal meal : meals) {
                if (meal.getStrMeal() != null && meal.getStrMeal().toLowerCase().contains(searchTerm.toLowerCase())) {
                    filteredMeals.add(meal);
                }
            }
        }
        return filteredMeals;
    }

    @Override
    public void showSearchMeal(List<Meal> meals) {
        searchMealAdapter.setList(meals);
        this.meals = meals;
    }

    @Override
    public void getMeal(Meal meal) {
        selectedMeal = meal;
        Log.e("Trace Meal","Fragment==> \n\t" + meal.toString());
    }

    @Override
    public void ShowMealDetails(Meal meal) {


    }

}