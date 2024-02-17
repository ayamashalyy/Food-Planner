package com.example.food_planner.searchMeal.view;

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
import com.example.food_planner.model.Ingredient;
import com.example.food_planner.model.Meal;
import com.example.food_planner.searchCategory.view.SearchCategoryAdapter;
import com.example.food_planner.searchIngredient.view.SearchIngredientView;

import java.util.List;

public class SearchMealAdapter extends RecyclerView.Adapter<SearchMealAdapter.ViewHolder> {
    Context context;

    List<Meal> meal;
    SearchMealView searchMealView;

    public SearchMealAdapter(Context context, SearchMealView searchMealView) {
        this.context = context;
        this.searchMealView = searchMealView;
    }

    public void setList(List<Meal> updateMeals){
        this.meal = updateMeals;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public SearchMealAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.meal, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SearchMealAdapter.ViewHolder holder, int position) {
        Meal meal1 = meal.get(position);

        holder.MealName.setText(meal1.getStrMeal());
        Glide.with(context).load(meal1.getStrMealThumb()).into(holder.MealImage);
        if(onMealCLick != null){
            holder.itemView.setOnClickListener(v -> onMealCLick.onMealCLick(meal1));
        }
    }
    onMealCLick onMealCLick;

    public void setOnMealCLick(onMealCLick onMealCLick) {
        this.onMealCLick = onMealCLick;
    }

    public interface onMealCLick{
        void onMealCLick(Meal meal);
    }
    @Override
    public int getItemCount() {
        if (meal == null) return 0;
        return meal.size();
    }


    class ViewHolder extends RecyclerView.ViewHolder {
        private TextView MealName;
        private ImageView MealImage;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            MealName = itemView.findViewById(R.id.mealText);
            MealImage = itemView.findViewById(R.id.mealImag);
        }
    }
}
