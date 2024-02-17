package com.example.food_planner.Calender.view;

import android.os.Bundle;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.food_planner.Calender.presenter.CalenderPresenter;
import com.example.food_planner.Calender.presenter.CalenderPresenterInterface;
import com.example.food_planner.DB.MealsLocalDataSourceImp;
import com.example.food_planner.R;
import com.example.food_planner.detailmeals.view.detailsMealFragment;
import com.example.food_planner.model.Meal;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.schedulers.Schedulers;


public class calenderFragment extends Fragment implements CalenderViewInterface, CalenderOnClickListener {
    CalenderPresenterInterface pressenter;
    RecyclerView saturdayRecycle;
    CalendarAdapter saturdayAdapter;
    LinearLayoutManager saturdaylayer;
    RecyclerView sundayRecycle;
    CalendarAdapter sundayAdapter;
    LinearLayoutManager sundaylayer;
    RecyclerView mondayRecycle;
    CalendarAdapter mondayAdapter;
    LinearLayoutManager mondaylayer;
    RecyclerView tuesdayRecycle;
    CalendarAdapter tuesdayAdapter;
    LinearLayoutManager tuesdaylayer;
    RecyclerView wednesdayRecycle;
    CalendarAdapter wednesdayAdapter;
    LinearLayoutManager wednesdaylayer;
    RecyclerView thursdayRecycle;
    CalendarAdapter thursdayAdapter;
    LinearLayoutManager thursdaylayer;
    RecyclerView fridayRecycle;
    CalendarAdapter fridayAdapter;
    LinearLayoutManager fridaylayer;
    View view;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_calender, container, false);
        pressenter = new CalenderPresenter(this, MealsLocalDataSourceImp.getInstance(getContext()));
        saturdayRecycle = view.findViewById(R.id.saturday);
        saturdaylayer = new LinearLayoutManager(getContext());
        saturdaylayer.setOrientation(RecyclerView.HORIZONTAL);
        saturdayRecycle.setLayoutManager(saturdaylayer);
        saturdayAdapter = new CalendarAdapter(getContext(), this,view.findViewById(R.id.txt_saturday) ,saturdayRecycle);
        saturdayRecycle.setAdapter(saturdayAdapter);

        sundayRecycle = view.findViewById(R.id.sunday);
        sundaylayer = new LinearLayoutManager(getContext());
        sundaylayer.setOrientation(RecyclerView.HORIZONTAL);
        sundayRecycle.setLayoutManager(sundaylayer);
        sundayAdapter = new CalendarAdapter(getContext(), this, view.findViewById(R.id.txt_sunday),sundayRecycle);
        sundayRecycle.setAdapter(sundayAdapter);

        mondayRecycle = view.findViewById(R.id.monday);
        mondaylayer = new LinearLayoutManager(getContext());
        mondaylayer.setOrientation(RecyclerView.HORIZONTAL);
        mondayRecycle.setLayoutManager(mondaylayer);
        mondayAdapter = new CalendarAdapter(getContext(), this, view.findViewById(R.id.txt_monday),mondayRecycle);
        mondayRecycle.setAdapter(mondayAdapter);

        tuesdayRecycle = view.findViewById(R.id.tuesday);
        tuesdaylayer = new LinearLayoutManager(getContext());
        tuesdaylayer.setOrientation(RecyclerView.HORIZONTAL);
        tuesdayRecycle.setLayoutManager(tuesdaylayer);
        tuesdayAdapter = new CalendarAdapter(getContext(), this, view.findViewById(R.id.txt_tuesday),tuesdayRecycle);
        tuesdayRecycle.setAdapter(tuesdayAdapter);

        wednesdayRecycle = view.findViewById(R.id.wednesday);
        wednesdaylayer = new LinearLayoutManager(getContext());
        wednesdaylayer.setOrientation(RecyclerView.HORIZONTAL);
        wednesdayRecycle.setLayoutManager(wednesdaylayer);
        wednesdayAdapter = new CalendarAdapter(getContext(), this, view.findViewById(R.id.txt_wednesday),wednesdayRecycle);
        wednesdayRecycle.setAdapter(wednesdayAdapter);

        thursdayRecycle = view.findViewById(R.id.thuresday);
        thursdaylayer = new LinearLayoutManager(getContext());
        thursdaylayer.setOrientation(RecyclerView.HORIZONTAL);
        thursdayRecycle.setLayoutManager(thursdaylayer);
        thursdayAdapter = new CalendarAdapter(getContext(), this, view.findViewById(R.id.txt_thuresday),thursdayRecycle);
        thursdayRecycle.setAdapter(thursdayAdapter);

        fridayRecycle = view.findViewById(R.id.friday);
        fridaylayer = new LinearLayoutManager(getContext());
        fridaylayer.setOrientation(RecyclerView.HORIZONTAL);
        fridayRecycle.setLayoutManager(fridaylayer);
        fridayAdapter = new CalendarAdapter(getContext(), this, view.findViewById(R.id.txt_friday),fridayRecycle);
        fridayRecycle.setAdapter(fridayAdapter);
        pressenter.getSaturdayMeals();
        pressenter.getSundayMeals();
        pressenter.getMondayMeals();
        pressenter.getTusdayMeals();
        pressenter.getWednsdayMeals();
        pressenter.getThursdayMeals();
        pressenter.getFridayMeals();


        return view;
    }

    @Override
    public void OnClick(Meal meal) {
        removeMeal(meal);
    }


    @Override
    public void showSaturdayMeals(Observable<List<Meal>> meals) {
        meals.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(item -> {saturdayAdapter.setList((ArrayList<Meal>) item);
                    if(item.size() > 0)
                    {
                        saturdayRecycle.setVisibility(View.VISIBLE);
                    }
                });
    }

    @Override
    public void showSundayMeals(Observable<List<Meal>> meals) {
        meals.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(item -> {sundayAdapter.setList((ArrayList<Meal>) item);
                    if(item.size() > 0)
                    {
                        sundayRecycle.setVisibility(View.VISIBLE);
                    }
                });
    }

    @Override
    public void showMondayMeals(Observable<List<Meal>> meals) {
        meals.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(item ->{ mondayAdapter.setList((ArrayList<Meal>) item);
                    if(item.size() > 0)
                    {
                        mondayRecycle.setVisibility(View.VISIBLE);
                    }
                });
    }

    @Override
    public void showTusdayMeals(Observable<List<Meal>> meals) {
        meals.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(item -> {tuesdayAdapter.setList((ArrayList<Meal>) item);
                    if(item.size() > 0)
                    {
                        tuesdayRecycle.setVisibility(View.VISIBLE);
                    }
                });
    }

    @Override
    public void showWednsdayMeals(Observable<List<Meal>> meals) {
        meals.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(item ->{ wednesdayAdapter.setList((ArrayList<Meal>) item);
                    if(item.size() > 0)
                    {
                        wednesdayRecycle.setVisibility(View.VISIBLE);
                    }
                    ;});
    }

    @Override
    public void showThursdayMeals(Observable<List<Meal>> meals) {
        meals.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(item -> {thursdayAdapter.setList((ArrayList<Meal>) item);
                    if(item.size() > 0)
                    {
                        thursdayRecycle.setVisibility(View.VISIBLE);
                    }

                });
    }

    @Override
    public void showFridayMeals(Observable<List<Meal>> meals) {
        meals.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(item -> {fridayAdapter.setList((ArrayList<Meal>) item);
                    if(item.size() > 0)
                    {
                        fridayRecycle.setVisibility(View.VISIBLE);
                    }

                });
    }

    @Override
    public void removeMeal(Meal meal) {
        pressenter.remove(meal);
    }
}