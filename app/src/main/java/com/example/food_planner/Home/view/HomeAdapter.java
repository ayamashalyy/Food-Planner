package com.example.food_planner.Home.view;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.bumptech.glide.Glide;
import com.example.food_planner.R;
import com.example.food_planner.model.Category;
import com.example.food_planner.model.Meal;

import java.util.ArrayList;
import java.util.List;

public class HomeAdapter extends RecyclerView.Adapter<HomeAdapter.ViewHolder> {
    Context context;
    List<Category> categoryMeal;
    List<Meal> meals;
    HomeView homeView;

    public HomeAdapter(Context context, List<Meal> meals, List<Category> categoryMeal, HomeView homeView) {
        this.context = context;
        this.categoryMeal = categoryMeal;
        this.meals = meals;
        this.homeView = homeView;
        categoryMeal = new ArrayList<Category>();

    }


    public void setList(List<Category> updateMeals) {
        this.categoryMeal = updateMeals;
        notifyDataSetChanged();

    }

    @NonNull
    @Override
    public HomeAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.fragment_categorylistmeal, parent, false);
        HomeAdapter.ViewHolder viewHolder = new HomeAdapter.ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull HomeAdapter.ViewHolder holder, int position) {
        Category category = categoryMeal.get(position);
        holder.CategoryMealName.setText(category.getStrCategory());
        Glide.with(context).load(category.getStrCategoryThumb()).into(holder.CategoryMealImage);
        if (onMealItemClick != null)
            holder.itemView.setOnClickListener(v -> onMealItemClick.onItemClick(position));
    }

    onMealItemClick onMealItemClick;

    public void setOnMealItemClick(onMealItemClick onclick) {
        onMealItemClick = onclick;
    }

    public interface onMealItemClick {
        void onItemClick(int pos);
    }

    @Override
    public int getItemCount() {
        if (categoryMeal == null) return 0;
        return categoryMeal.size();
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
