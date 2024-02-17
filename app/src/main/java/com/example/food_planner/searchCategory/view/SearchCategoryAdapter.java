package com.example.food_planner.searchCategory.view;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.bumptech.glide.Glide;
import com.example.food_planner.R;
import com.example.food_planner.model.Category;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class SearchCategoryAdapter extends RecyclerView.Adapter<SearchCategoryAdapter.ViewHolder> {
    Context context;
    List<Category> categoryMeal;
    List<Category> newCategoryMeal;

    SearchCategoryView searchCategoryView;

    public SearchCategoryAdapter(Context context, SearchCategoryView searchCategoryView) {
        this.context = context;
        this.searchCategoryView = searchCategoryView;
    }

    public void setList(List<Category> updateMeals){
        this.categoryMeal = this.categoryMeal = updateMeals;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public SearchCategoryAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.fragment_categorylistmeal, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SearchCategoryAdapter.ViewHolder holder, int position) {
        Category category = categoryMeal.get(position);
        holder.CategoryMealName.setText(category.getStrCategory());
        Glide.with(context).load(category.getStrCategoryThumb()).into(holder.CategoryMealImage);
        if(onCategoryCLick!=null){
            holder.itemView.setOnClickListener(v -> onCategoryCLick.onCategoryClick(position));
        }
    }

    onCategoryCLick onCategoryCLick;

    public void setOnCategoryCLick(SearchCategoryAdapter.onCategoryCLick onCategoryCLick) {
        this.onCategoryCLick = onCategoryCLick;
    }

    public interface onCategoryCLick{
        void onCategoryClick(int pos);
    }

    @Override
    public int getItemCount() {
        if (categoryMeal == null) return 0;
        return categoryMeal.size();
    }
    public List<Category>getList(){

        return categoryMeal;
    }


    class ViewHolder extends RecyclerView.ViewHolder {
        private TextView CategoryMealName;
        private ImageView CategoryMealImage;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            CategoryMealName = itemView.findViewById(R.id.category);
            CategoryMealImage = itemView.findViewById(R.id.categoryImag);
        }
    }
}
