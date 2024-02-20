package com.example.food_planner.Favorite.view;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.food_planner.DB.MealsLocalDataSourceImp;
import com.example.food_planner.Favorite.presenter.favoritePresenter;
import com.example.food_planner.R;
import com.example.food_planner.model.Meal;
import com.example.food_planner.network.FireStore;

import java.util.ArrayList;

public class favoriteAdapter  extends  RecyclerView.Adapter<favoriteAdapter.FavoriteViewHolder>{
    Context context;
    ArrayList<Meal> meals;
    favoriteOnClickListener listener ;
    favoritePresenter presenter;


    public favoriteAdapter(Context context, favoriteOnClickListener listener) {
        this.context = context;
        meals = new ArrayList<>();
        this.listener = listener;
        presenter = new favoritePresenter(new MealsLocalDataSourceImp(context));
    }
    public void setList(ArrayList<Meal> updateMeals) {
        this.meals = updateMeals;
        notifyDataSetChanged();

    }

    @NonNull
    @Override
    public favoriteAdapter.FavoriteViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.fragment_favorite_view, parent ,false);
        FavoriteViewHolder viewHolder = new FavoriteViewHolder(view);
        return viewHolder;    }

    @Override
    public void onBindViewHolder(@NonNull favoriteAdapter.FavoriteViewHolder holder, int position) {
        Meal meal = meals.get(position);
        holder.mealName.setText(meal.getStrMeal());
        Glide.with(context).load(meal.getStrMealThumb()).into(holder.mealImage);
        holder.removeFromFavourite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    presenter.remove(meal);
                FireStore.removeFavouriteFromFirebase(context,meal);

                Toast.makeText(context, "Removed from your favorite list", Toast.LENGTH_SHORT).show();

                }

                });

            }

    @Override
    public int getItemCount() {
        return meals.size();
    }

    class FavoriteViewHolder extends RecyclerView.ViewHolder {
        private TextView mealName ;
        private ImageView mealImage;
        private ImageButton removeFromFavourite ;

        public FavoriteViewHolder(@NonNull View itemView) {
            super(itemView);
            mealName = itemView.findViewById(R.id.favText);
            mealImage = itemView.findViewById(R.id.favImag);
            removeFromFavourite = itemView.findViewById(R.id.btn_removeToFavorite);


        }
    }
}
