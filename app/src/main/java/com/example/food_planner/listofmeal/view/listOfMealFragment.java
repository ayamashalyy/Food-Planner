package com.example.food_planner.listofmeal.view;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.food_planner.DB.MealsLocalDataSourceImp;
import com.example.food_planner.Favorite.presenter.favoritePresenter;
import com.example.food_planner.Favorite.presenter.favoritePresenterInterface;
import com.example.food_planner.Home.presenter.HomePresenter;
import com.example.food_planner.Home.presenter.HomePressenterInterface;
import com.example.food_planner.Home.view.HomeAdapter;
import com.example.food_planner.R;
import com.example.food_planner.detailmeals.view.detailsMealFragment;
import com.example.food_planner.listofmeal.presenter.listofmealPresenter;
import com.example.food_planner.listofmeal.presenter.listofmealPressenterInterface;
import com.example.food_planner.model.Category;
import com.example.food_planner.model.Meal;
import com.example.food_planner.model.MealResponse;
import com.example.food_planner.network.MealsRemoteDataSourceImp;

import java.util.ArrayList;
import java.util.List;


public class listOfMealFragment extends Fragment implements listofmealView,listofmealOnClickListener {
View view;
    RecyclerView recyclerView;
    LinearLayoutManager linearLayoutManager;
    ListofmealAdapter adapter;
    Meal meal;
Category category;
    ImageView imageView;
    TextView listofmealName;
    favoritePresenterInterface presenterInterface;
    CardView card;
    List<Category> cats;
    listofmealPressenterInterface listofmealPressenterInterface;
    listofmealPressenterInterface listofmeal;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view =  inflater.inflate(R.layout.fragment_list_of_meal, container, false);
        recyclerView = view.findViewById(R.id.listmeal_recycleView);
        linearLayoutManager = new LinearLayoutManager(getActivity());
        linearLayoutManager.setOrientation(RecyclerView.HORIZONTAL);
        recyclerView.setLayoutManager(linearLayoutManager);
        adapter = new ListofmealAdapter(getActivity(),new ArrayList<>(),this);
        listofmealPressenterInterface = new listofmealPresenter(MealsRemoteDataSourceImp.getInstance(),this);
       // adapter.getlistofMeals();
        recyclerView.setAdapter(adapter);
        card=view.findViewById(R.id.home_cardView_constraint1);
        adapter.setOnMealItemClick(pos -> showallListMeals(meal));

        if (getArguments() != null) {
            category = (Category) getArguments().getSerializable("category");

        }
        return view;
    }

    @Override
    public void showlistofmeal(MealResponse mealResponse) {
        ArrayList<Meal> meals = mealResponse.meals;

        Log.e("Response", "Meals Size is: "+meals.size() );
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        listofmeal = new listofmealPresenter(MealsRemoteDataSourceImp.getInstance(),this);
        listofmeal.getlistofMeals("Beef");

    }

    @Override
    public void showallListMeals(Meal meal) {
        Bundle bundle= new Bundle();
        bundle.putSerializable("category",category);
        bundle.putSerializable("meal", meal); // Add the meal to the bundle

        Fragment fragment = new listOfMealFragment();
        fragment.setArguments(bundle);
        FragmentTransaction trans = getFragmentManager()
                .beginTransaction()
                .replace(R.id.frame_layout,fragment);
        trans.addToBackStack(null);
        trans.commit();
    }
}