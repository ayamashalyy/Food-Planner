package com.example.food_planner.Favorite.view;

import android.os.Bundle;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.example.food_planner.DB.MealsLocalDataSourceImp;
import com.example.food_planner.Favorite.presenter.favoritePresenter;
import com.example.food_planner.Favorite.presenter.favoritePresenterInterface;
import com.example.food_planner.R;
import com.example.food_planner.model.Meal;
import java.util.ArrayList;
import java.util.List;
import io.reactivex.rxjava3.schedulers.Schedulers;
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.core.Observable;


public class favoriteFragment extends Fragment implements  favoriteView , favoriteOnClickListener{
    RecyclerView recyclerView;
    LinearLayoutManager layoutManager;
    MealsLocalDataSourceImp localDataSourceImp;

    favoriteAdapter favAdapter;
    View view;
    favoritePresenterInterface favoritePresenter;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_favorite, container, false);
        recyclerView =view.findViewById(R.id.fav_recyclerView);
          layoutManager = new LinearLayoutManager(getActivity());
         layoutManager.setOrientation(RecyclerView.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);
        favAdapter = new favoriteAdapter(getActivity(),this);
        recyclerView.setAdapter(favAdapter);
       favoritePresenter = new favoritePresenter(MealsLocalDataSourceImp.getInstance(getContext()));
        favoritePresenter.getMeals()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(meals -> {
                    favAdapter.setList(new ArrayList<>(meals));
                });

        return view;
    }

    @Override
    public void onClick(Meal meal) {
        removeMeal(meal);

    }

    @Override
    public void showMealDetails(Meal meal) {

    }


    @Override
    public void showFavorite(Observable<List<Meal>> meals) {

    }

    @Override
    public void removeMeal(Meal meal) {
        favoritePresenter.remove(meal);

    }
}