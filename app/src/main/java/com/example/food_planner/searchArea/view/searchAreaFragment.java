package com.example.food_planner.searchArea.view;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.text.Editable;
import android.text.Html;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

import com.example.food_planner.Common.GetAllMealDetails;
import com.example.food_planner.Common.Helper;
import com.example.food_planner.Login.Login;
import com.example.food_planner.R;
import com.example.food_planner.model.Area;
import com.example.food_planner.network.MealsRemoteDataSourceImp;
import com.example.food_planner.network.NetworkConnection;
import com.example.food_planner.searchArea.presenter.SearchAreaPresenter;
import com.example.food_planner.searchArea.presenter.SearchAreaPresenterInterface;
import com.example.food_planner.searchMeal.view.search_mealFragment;

import java.util.ArrayList;
import java.util.List;

 public class searchAreaFragment extends Fragment implements SearchAreaView, SearchAreaOnClickLisener {

    View view;
    SearchAreaAdapter searchAreaAdapter;
    SearchAreaPresenterInterface searchAreaPresenterInterface;
    EditText search;
    RecyclerView recyclerView;
    LinearLayoutManager linearLayoutManager;
    List<Area> areas;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_search_area, container, false);
        recyclerView = view.findViewById(R.id.rec_areaaa);
        linearLayoutManager = new LinearLayoutManager(getActivity());
        linearLayoutManager.setOrientation(RecyclerView.VERTICAL);
        search = view.findViewById(R.id.search_area);
        recyclerView.setLayoutManager(linearLayoutManager);
        searchAreaAdapter = new SearchAreaAdapter(getActivity(), this);
        searchAreaPresenterInterface = new SearchAreaPresenter(MealsRemoteDataSourceImp.getInstance(), this);
        searchAreaPresenterInterface.getSearchArea();
        areas = searchAreaAdapter.getList();
        searchAreaAdapter.setList(areas);
        recyclerView.setAdapter(searchAreaAdapter);
        searchAreaAdapter.setOnAreaCLick(pos -> {
            Bundle bundle = new Bundle();
            bundle.putString("FLAG", "area");
            Helper.showSelectedArea(
                    areas.get(pos),
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
                List<Area> filtereddArea = filterAreas(s.toString());
                searchAreaAdapter.setList(filtereddArea);
                searchAreaAdapter.notifyDataSetChanged();
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
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
        return view;
    }



     private List<Area> filterAreas(String searchTerm) {
         List<Area> filteredArea = new ArrayList<>();
         if (areas != null) {
             for (Area area : areas) {
                 if (area.getStrArea() != null && area.getStrArea().toLowerCase().contains(searchTerm.toLowerCase())) {
                     filteredArea.add(area);
                 }
             }
         }
         return filteredArea;
     }


     @Override
    public void showSearchArea(List<Area> areas) {
        searchAreaAdapter.setList(areas);
        searchAreaAdapter.notifyDataSetChanged();
        this.areas = areas;
    }
}

