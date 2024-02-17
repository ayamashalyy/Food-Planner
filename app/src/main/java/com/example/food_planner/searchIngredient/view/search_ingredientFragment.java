package com.example.food_planner.searchIngredient.view;

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

import com.example.food_planner.Common.Helper;
import com.example.food_planner.R;
import com.example.food_planner.model.Area;
import com.example.food_planner.model.Ingredient;
import com.example.food_planner.network.MealsRemoteDataSourceImp;
import com.example.food_planner.network.NetworkConnection;
import com.example.food_planner.searchArea.presenter.SearchAreaPresenter;
import com.example.food_planner.searchArea.presenter.SearchAreaPresenterInterface;
import com.example.food_planner.searchArea.view.SearchAreaAdapter;
import com.example.food_planner.searchArea.view.SearchAreaOnClickLisener;
import com.example.food_planner.searchArea.view.SearchAreaView;
import com.example.food_planner.searchIngredient.presenter.SearchIngredientPresenter;
import com.example.food_planner.searchIngredient.presenter.SearchIngredientPresenterInterface;
import com.example.food_planner.searchMeal.view.search_mealFragment;

import java.util.ArrayList;
import java.util.List;

public class search_ingredientFragment extends Fragment implements SearchIngredientView, SearchIngredientOnClickLisener {
    View view;
    SearchIngredientAdapter searchIngredientAdapter;
    SearchIngredientPresenterInterface searchIngredientPresenterInterface;
    EditText search;
    RecyclerView recyclerView;
    LinearLayoutManager linearLayoutManager;
    List<Ingredient> ingredients;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_search_ingredient, container, false);
        recyclerView = view.findViewById(R.id.rec_searchCateee);
        linearLayoutManager = new LinearLayoutManager(getActivity());
        linearLayoutManager.setOrientation(RecyclerView.HORIZONTAL);
        search = view.findViewById(R.id.search_ingredient);
        recyclerView.setLayoutManager(linearLayoutManager);
        searchIngredientAdapter = new SearchIngredientAdapter(getActivity(),this);
        searchIngredientPresenterInterface = new SearchIngredientPresenter(MealsRemoteDataSourceImp.getInstance(), this);
        searchIngredientPresenterInterface.getSearchIngredient();
        ingredients = searchIngredientAdapter.getList();
        searchIngredientAdapter.setList(ingredients);
        recyclerView.setAdapter(searchIngredientAdapter);

        searchIngredientAdapter.setOnIngredientCLick(pos -> {
            Bundle bundle = new Bundle();
            bundle.putString("FLAG","ingredient");
            Helper.showSelectedIngredient(
                    ingredients.get(pos),
                    bundle,
                    getFragmentManager(),
                    new search_mealFragment()
            );
        });
        if (NetworkConnection.getConnectivity(getContext())) {

            search.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                }

                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {
                    List<Ingredient> filteredIngredients = filterIngredients(s.toString());
                    searchIngredientAdapter.setList(filteredIngredients);
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

    private List<Ingredient> filterIngredients(String searchTerm) {
        List<Ingredient> filteredIngredients = new ArrayList<>();
        if (ingredients != null) {
            for (Ingredient ingredient : ingredients) {
                if (ingredient.getStrIngredient() != null && ingredient.getStrIngredient().toLowerCase().contains(searchTerm.toLowerCase())) {
                    filteredIngredients.add(ingredient);
                }
            }
        }
        return filteredIngredients;
    }

    @Override
    public void showSearchIngredient(List<Ingredient> ingredients) {
        searchIngredientAdapter.setList(ingredients);
        searchIngredientAdapter.notifyDataSetChanged();
        this.ingredients = ingredients;
    }
}