package com.example.food_planner.searchArea.view;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.food_planner.R;
import com.example.food_planner.model.Area;
import com.example.food_planner.searchCategory.view.SearchCategoryAdapter;

import java.util.ArrayList;
import java.util.List;

public class SearchAreaAdapter extends RecyclerView.Adapter<SearchAreaAdapter.ViewHolder> {
    Context context;
    List<Area> AreaMeal;

    SearchAreaView searchAreaView;

    public SearchAreaAdapter(Context context, SearchAreaView searchAreaView) {
        this.context = context;
        this.searchAreaView = searchAreaView;
    }

    public void setList(List<Area> updateMeals){
        this.AreaMeal = updateMeals;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public SearchAreaAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.meal, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SearchAreaAdapter.ViewHolder holder, int position) {
        Area area = AreaMeal.get(position);
        String countryCode = countryCodes[position];

        holder.AreaMealName.setText(area.getStrArea());
        Glide.with(context).load("https://flagsapi.com/" + countryCode + "/flat/64.png")
                .apply(new RequestOptions().override(120, 64))
                .into(holder.AreaMealImage);
        if(onAreaCLick!=null){
            holder.itemView.setOnClickListener(v -> onAreaCLick.onAreaCLick(position));
        }

    }
    onAreaCLick onAreaCLick;

    public void setOnAreaCLick(SearchAreaAdapter.onAreaCLick onAreaCLick) {
        this.onAreaCLick = onAreaCLick;
    }

    public interface onAreaCLick{
        void onAreaCLick(int pos);
    }

    @Override
    public int getItemCount() {
        if (AreaMeal == null) return 0;
        return AreaMeal.size();
    }
    public List<Area>getList(){

        return AreaMeal;
    }




    String[] countryCodes = {
            "US", "GB", "CA", "CN", "HR", "NL", "EG", "PH", "FR", "GR",
            "IN", "IE", "IT", "JM", "JP", "KE", "MY", "MX", "MA", "PL",
            "PT", "RU", "ES", "TH", "TN", "TR", "FM", "VN"
    };

    class ViewHolder extends RecyclerView.ViewHolder {
        private TextView AreaMealName;
        private ImageView AreaMealImage;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            AreaMealName = itemView.findViewById(R.id.mealText);
            AreaMealImage = itemView.findViewById(R.id.mealImag);
        }
    }
}
