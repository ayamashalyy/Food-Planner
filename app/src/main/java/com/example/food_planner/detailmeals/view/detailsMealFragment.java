package com.example.food_planner.detailmeals.view;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

import com.bumptech.glide.Glide;
import com.example.food_planner.DB.MealsLocalDataSourceImp;
import com.example.food_planner.Favorite.presenter.favoritePresenter;
import com.example.food_planner.Favorite.presenter.favoritePresenterInterface;
import com.example.food_planner.R;
import com.example.food_planner.detailmeals.presenter.detailMealPressenterInterface;
import com.example.food_planner.detailmeals.presenter.detailMealsPresenter;
import com.example.food_planner.model.Meal;
import com.example.food_planner.network.MealsRemoteDataSourceImp;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.PlayerConstants;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.AbstractYouTubePlayerListener;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.views.YouTubePlayerView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class detailsMealFragment extends Fragment implements detailMealView {
    View view;
    ImageView imagFood;
    TextView mealName, mealContry, mealingredient;
    YouTubePlayerView youTube;
    favoritePresenterInterface presenterInterface;
    ToggleButton addToFavorite;
    TextView instractions;
    RecyclerView recyclerView;
    LinearLayoutManager layoutManager;
    DetailMealsAdapter adapter;
    ArrayList<MealIngredients> resultToShow;
    AutoCompleteTextView autoCompleteTextView;
    Meal meal;
    detailMealPressenterInterface presenter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_details_meal, container, false);
        mealName = view.findViewById(R.id.mealdetailname);
        String[] daysArray = {"Saturday", "Sunday", "Monday", "Tuesday", "Wednesday", "Thursday", "Friday"};
        imagFood = view.findViewById(R.id.detailImag);
        mealContry = view.findViewById(R.id.mealContry);
        mealingredient = view.findViewById(R.id.mealingredient);
        youTube = view.findViewById(R.id.ybv);
        addToFavorite = view.findViewById(R.id.btn_addToFavorite);
        instractions = view.findViewById(R.id.steps);
        recyclerView = view.findViewById(R.id.recyclerView2);
        autoCompleteTextView = view.findViewById(R.id.days_drop_dawn);
        layoutManager = new LinearLayoutManager(getActivity());
        layoutManager.setOrientation(RecyclerView.HORIZONTAL);
        recyclerView.setLayoutManager(layoutManager);
        adapter = new DetailMealsAdapter(getActivity(), resultToShow);
        presenterInterface = new favoritePresenter(new MealsLocalDataSourceImp(getContext()));
        recyclerView.setAdapter(adapter);
        presenter = new detailMealsPresenter(MealsRemoteDataSourceImp.getInstance(), this);
        addToFavorite.setOnCheckedChangeListener((buttonView, isChecked) -> {
            presenterInterface.addMeal(meal);
        });
        getLifecycle().addObserver(youTube);
        if (getArguments() != null) {
            meal = (Meal) getArguments().getSerializable("meal");
            presenter.getMealID(meal.getIdMeal());
            showdetailMeal(meal);

        } else {
            Log.e("Trace Meal", "No Meal is Passed");
        }
        ArrayAdapter<String> adapter = new ArrayAdapter<>(getContext(), android.R.layout.simple_dropdown_item_1line, daysArray);
        autoCompleteTextView.setAdapter(adapter);

        autoCompleteTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                autoCompleteTextView.showDropDown();
            }

        });

        autoCompleteTextView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String[] daysOfWeek = {"Saturday", "Sunday", "Monday", "Tuesday", "Wednesday", "Thursday", "Friday"};
                String day = parent.getItemAtPosition(position).toString();
                Map<String, String> dayCodeMap = new HashMap<>();
                dayCodeMap.put("Saturday", "1");
                dayCodeMap.put("Sunday", "2");
                dayCodeMap.put("Monday", "3");
                dayCodeMap.put("Tuesday", "4");
                dayCodeMap.put("Wednesday", "5");
                dayCodeMap.put("Thursday", "6");
                dayCodeMap.put("Friday", "7");
                if (dayCodeMap.containsKey(day)) {
                    presenterInterface.remove(meal);
                    meal.setDAY(Integer.parseInt(dayCodeMap.get(day)));
                    presenterInterface.addMeal(meal);
                    Toast.makeText(getContext(), "Meal added to " + day, Toast.LENGTH_SHORT).show();
                }
            }
        });

        return view;
    }

    public ArrayList<MealIngredients> demoDate(Meal meal) {
        ArrayList<MealIngredients> ingredientsList = new ArrayList<>();

        String[] ingredients = {
                meal.getStrIngredient1(), meal.getStrIngredient2(), meal.getStrIngredient3(), meal.getStrIngredient4(), meal.getStrIngredient5(),
                meal.getStrIngredient6(), meal.getStrIngredient7(), meal.getStrIngredient8(), meal.getStrIngredient9(), meal.getStrIngredient10(),
                meal.getStrIngredient11(), meal.getStrIngredient12(), meal.getStrIngredient13(), meal.getStrIngredient14(), meal.getStrIngredient15(),
                meal.getStrIngredient16(), meal.getStrIngredient17(), meal.getStrIngredient18(), meal.getStrIngredient19(), meal.getStrIngredient20()
        };

        String[] measures = {
                meal.getStrMeasure1(), meal.getStrMeasure2(), meal.getStrMeasure3(), meal.getStrMeasure4(), meal.getStrMeasure5(),
                meal.getStrMeasure6(), meal.getStrMeasure7(), meal.getStrMeasure8(), meal.getStrMeasure9(), meal.getStrMeasure10(),
                meal.getStrMeasure11(), meal.getStrMeasure12(), meal.getStrMeasure13(), meal.getStrMeasure14(), meal.getStrMeasure15(),
                meal.getStrMeasure16(), meal.getStrMeasure17(), meal.getStrMeasure18(), meal.getStrMeasure19(), meal.getStrMeasure20()
        };

        for (int i = 0; i < ingredients.length; i++) {
            if (ingredients[i] != null && !ingredients[i].isEmpty()) {
                ingredientsList.add(new MealIngredients(ingredients[i], measures[i]));
            }
        }

        return ingredientsList;
    }

    @Override
    public void showdetailMeal(Meal meal) {
        if (meal != null) {
            mealName.setText(meal.getStrMeal());
            mealContry.setText(meal.getStrCategory());
            resultToShow = demoDate(meal);
            adapter.setList(resultToShow);
            mealingredient.setText(meal.getStrArea());
            Glide.with(imagFood.getContext()).load(meal.getStrMealThumb()).into(imagFood);
            instractions.setText(meal.getStrInstructions());
            youTube.addYouTubePlayerListener(new AbstractYouTubePlayerListener() {
                @Override
                public void onReady(@NonNull YouTubePlayer youTubePlayer2) {
                    String url = meal.getStrYoutube() != null ?
                            meal.getStrYoutube() :
                            "https://www.youtube.com/watch?v=QJmKw33k0mA";
                    youTubePlayer2.cueVideo(GetIdFromYoutubeUrl.getId(url), 0);
                }
            });
        } else {
            Log.e("detailsMealFragment", "Meal object is null");
        }
    }

}