package com.example.food_planner.Home.view;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.food_planner.Common.Helper;
import com.example.food_planner.DB.MealsLocalDataSourceImp;
import com.example.food_planner.Home.presenter.HomePresenter;
import com.example.food_planner.Home.presenter.HomePressenterInterface;
import com.example.food_planner.Login.Login;
import com.example.food_planner.R;
import com.example.food_planner.categoryFragment;
import com.example.food_planner.detailmeals.view.detailsMealFragment;
import com.example.food_planner.model.Category;
import com.example.food_planner.model.Meal;
import com.example.food_planner.network.MealsRemoteDataSourceImp;
import com.example.food_planner.network.NetworkConnection;
import com.example.food_planner.searchMeal.view.search_mealFragment;

import java.util.ArrayList;
import java.util.List;

public class homeFragment extends Fragment implements HomeView, HomeOnClickListener{
    RecyclerView recyclerView;
    ImageButton logout;
    LinearLayoutManager linearLayoutManager;
    private static final String SHARED_PREFS = "sharedPreferences";

    HomeAdapter homeAdapter;
    MealsLocalDataSourceImp localDataSourceImp;
    HomePressenterInterface homePresenter;
    Meal meal;
    ImageView imageView;
    TextView randomName,categoryName;
    CardView card;
    CardView cardCategory;
    List<Category> cats;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        recyclerView = view.findViewById(R.id.home_recyclerView);
        imageView = view.findViewById(R.id.randomImag);
        logout = view.findViewById(R.id.logout);
        randomName = view.findViewById(R.id.randomText);
        linearLayoutManager = new LinearLayoutManager(getActivity());
        linearLayoutManager.setOrientation(RecyclerView.HORIZONTAL);
        recyclerView.setLayoutManager(linearLayoutManager);
        homeAdapter=new HomeAdapter(getActivity(),new ArrayList<>(),new ArrayList<>(),this);
        homePresenter = new HomePresenter(MealsRemoteDataSourceImp.getInstance(),this);
        homePresenter.getDailyRandomMeals();
        recyclerView.setAdapter(homeAdapter);
        card=view.findViewById(R.id.cardView2);
        homePresenter.getRandomMeal();
        homePresenter.getDailyRandomMeals();
        logout.setOnClickListener(v -> {
            SharedPreferences sharedPreferences = getActivity().getSharedPreferences(SHARED_PREFS, 0);
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putString("name","false");
            editor.apply();
            localDataSourceImp = MealsLocalDataSourceImp.getInstance(getActivity().getApplicationContext());
            localDataSourceImp.deleteAllMeals();
            Intent intent = new Intent(getActivity(), Login.class);
            startActivity(intent);
        });

        card.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(NetworkConnection.getConnectivity(getContext())) {
                    showMealDetails(meal);
                }
                else {

                    String yes = "OK";
                    AlertDialog.Builder builder = new AlertDialog.Builder(getContext()); // Use the activity context
                    builder.setMessage("Please reconnect and try again ");
                    builder.setTitle("There is no internet connection");
                    builder.setCancelable(false);
                    builder.setPositiveButton(Html.fromHtml("<font color='#F8B66C'>" + yes + "</font>"), (DialogInterface.OnClickListener) (dialog, which) -> {
                        dialog.cancel();
                    });

                    AlertDialog alertDialog = builder.create();
                    alertDialog.show();
                }
            }
        });

        homeAdapter.setOnMealItemClick(pos -> showCategoryDetails(cats.get(pos)));
        return view;
    }

    @Override
    public void showRandomMeals(Meal meal) {
        Glide.with(imageView.getContext()).load(meal.getStrMealThumb()).into(imageView);
        randomName.setText(meal.getStrMeal());
        this.meal = meal;
    }

    @Override
    public void showCategoryMeal(List<Category> categories) {
        homeAdapter.setList(categories);
        homeAdapter.notifyDataSetChanged();
        cats = categories;
    }

    @Override
    public void showErrorMsg(String e) {

    }

    @Override
    public void onAddToFavorite(Meal meal) {

    }

    @Override
    public void showMealDetails(Meal meal) {
        Helper.showSelectedMeal(
                meal,
                new Bundle(),
                getFragmentManager(),
                new detailsMealFragment()
        );
}

    @Override
    public void showCategoryDetails(Category category) {
        Bundle bundle= new Bundle();
        bundle.putString("FLAG","category");
        Helper.showSelectedCategory(
                category,
                bundle,
                getFragmentManager(),
                new search_mealFragment()
        );
    }
}