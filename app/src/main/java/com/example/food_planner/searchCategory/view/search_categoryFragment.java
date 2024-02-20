package com.example.food_planner.searchCategory.view;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.text.Editable;
import android.text.Html;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.food_planner.Common.Helper;
import com.example.food_planner.Login.Login;
import com.example.food_planner.R;
import com.example.food_planner.detailmeals.view.detailsMealFragment;
import com.example.food_planner.model.Category;
import com.example.food_planner.network.MealsRemoteDataSourceImp;
import com.example.food_planner.network.NetworkConnection;
import com.example.food_planner.searchCategory.presenter.SearchCategoryPresenter;
import com.example.food_planner.searchCategory.presenter.SearchCategoryPresenterInterface;
import com.example.food_planner.searchMeal.view.search_mealFragment;

import java.util.ArrayList;
import java.util.List;

public class search_categoryFragment extends Fragment implements SearchCategoryView, SearchCategoryOnClickLisener {
    RecyclerView recyclerView;
    private static final String TAG = "search_categoryFragment";
    LinearLayoutManager linearLayoutManager;
    SearchCategoryAdapter searchCategoryAdapter;
    SearchCategoryPresenterInterface categoryPresenterInterface;
    EditText search;
    List<Category>categories;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_search_category, container, false);
        recyclerView = view.findViewById(R.id.rec_searchCatee);
        linearLayoutManager = new LinearLayoutManager(getActivity());
        linearLayoutManager.setOrientation(RecyclerView.HORIZONTAL);
        search = view.findViewById(R.id.search_category);
        recyclerView.setLayoutManager(linearLayoutManager);
        searchCategoryAdapter = new SearchCategoryAdapter(getActivity(), this);
        categoryPresenterInterface = new SearchCategoryPresenter(MealsRemoteDataSourceImp.getInstance(), this);
        categoryPresenterInterface.getSearchCategory();
        categories = searchCategoryAdapter.getList();
        searchCategoryAdapter.setList(categories);
        recyclerView.setAdapter(searchCategoryAdapter);

        searchCategoryAdapter.setOnCategoryCLick(pos -> {
                Bundle bundle = new Bundle();
                bundle.putString("FLAG", "category");
                Helper.showSelectedCategory(
                        categories.get(pos),
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
                    List<Category> filteredCategories = filterCategories(s.toString());
                    searchCategoryAdapter.setList(filteredCategories);
                }

                @Override
                public void afterTextChanged(Editable s) {

                }
            });
        }   else {

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

        return view;
    }

    private List<Category> filterCategories(String searchTerm) {
        List<Category> filteredCategories = new ArrayList<>();
        for (Category category : categories) {
            if (category.getStrCategory().toLowerCase().contains(searchTerm.toLowerCase())) {
                filteredCategories.add(category);
            }
        }
        return filteredCategories;
    }

    @Override
    public void showSearchCategory(List<Category> categories) {
        searchCategoryAdapter.setList(categories);
        searchCategoryAdapter.notifyDataSetChanged();
        this.categories = categories;
    }

}