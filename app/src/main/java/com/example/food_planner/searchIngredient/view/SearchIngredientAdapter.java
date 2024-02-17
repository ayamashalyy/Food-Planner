package com.example.food_planner.searchIngredient.view;

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
import com.example.food_planner.model.Ingredient;
import com.example.food_planner.searchCategory.view.SearchCategoryAdapter;

import java.util.List;

public class SearchIngredientAdapter extends RecyclerView.Adapter<SearchIngredientAdapter.ViewHolder> {
    Context context;
    List<Ingredient> ingredientMeal;
    private String url_part1="https://www.themealdb.com/images/ingredients/";

    private  String url_part2=".png";

    SearchIngredientView searchIngredientView;

    public SearchIngredientAdapter(Context context, SearchIngredientView searchIngredientView) {
        this.context = context;
        this.searchIngredientView = searchIngredientView;
    }

    public void setList(List<Ingredient> updateMeals){
        this.ingredientMeal = updateMeals;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public SearchIngredientAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.fragment_categorylistmeal, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SearchIngredientAdapter.ViewHolder holder, int position) {
        Ingredient ingredient = ingredientMeal.get(position);

        holder.IngredientMealName.setText(ingredient.getStrIngredient());
        Glide.with(context).load(url_part1+ingredient.getStrIngredient()+url_part2).into(holder.IngredientMealImage);
        if(onIngredientCLick!=null){
            holder.itemView.setOnClickListener(v -> onIngredientCLick.onIngredientCLick(position));
        }


    }

    SearchIngredientAdapter.onIngredientCLick onIngredientCLick;

    public void setOnIngredientCLick(SearchIngredientAdapter.onIngredientCLick onIngredientCLick) {
        this.onIngredientCLick = onIngredientCLick;
    }

    public interface onIngredientCLick{
        void onIngredientCLick(int pos);
    }

    @Override
    public int getItemCount() {
        if (ingredientMeal == null) return 0;
        return ingredientMeal.size();
    }
    public List<Ingredient>getList(){

        return ingredientMeal;
    }


    class ViewHolder extends RecyclerView.ViewHolder {
        private TextView IngredientMealName;
        private ImageView IngredientMealImage;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            IngredientMealName = itemView.findViewById(R.id.category);
            IngredientMealImage = itemView.findViewById(R.id.categoryImag);
        }
    }
}
