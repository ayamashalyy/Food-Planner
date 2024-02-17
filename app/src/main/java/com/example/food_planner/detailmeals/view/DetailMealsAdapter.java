package com.example.food_planner.detailmeals.view;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.bumptech.glide.Glide;
import com.example.food_planner.R;
import com.example.food_planner.model.Meal;
import java.util.ArrayList;
import java.util.List;

public class DetailMealsAdapter extends RecyclerView.Adapter<DetailMealsAdapter.MealViewHolder> {
    private static final String TAG = "DetailMealsAdapter";
    Context context;
    ArrayList<MealIngredients> ingredients;
    private String url_part1="https://www.themealdb.com/images/ingredients/";

    private  String url_part2=".png";
    List<Meal>meals;
    detailMealView detailMealView;
    public DetailMealsAdapter(Context context,ArrayList<MealIngredients> ingredients) {
        this.context = context;
        this.ingredients = ingredients;

    }
    public  void  setList(ArrayList<MealIngredients> ingredients){
        this.ingredients=ingredients;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public DetailMealsAdapter.MealViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.fragment_quantities, parent ,false);
        MealViewHolder viewHolder = new MealViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull DetailMealsAdapter.MealViewHolder holder, int position) {
        MealIngredients ing  = ingredients.get(position);
        holder.ingredientName.setText(ing.getStrIngredient());
        Glide.with(context).load(url_part1+ing.getStrIngredient()+url_part2).into(holder.ingredientImage);
        holder.measure.setText(ing.getStrMeasure());
    }

    @Override
    public int getItemCount() {
       // Log.e("Tag", "Test");
       // Log.e("Tag", "getItemCount: "+this.ingredients.size());
        if(this.ingredients==null) {
            Log.e("Tag", "Test");
            return 0;
        }
            return this.ingredients.size();
    }
    class MealViewHolder extends RecyclerView.ViewHolder {
        private TextView ingredientName ;
        private ImageView ingredientImage;

        private  TextView measure;

        public MealViewHolder(@NonNull View itemView) {
            super(itemView);
            ingredientImage=itemView.findViewById(R.id.categoryImage);
            ingredientName=itemView.findViewById(R.id.ingrentText);
            measure=itemView.findViewById(R.id.tv_random_meals_measure);

        }
    }
}
