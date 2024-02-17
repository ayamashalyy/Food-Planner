package com.example.food_planner.listofmeal.view;

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

import java.util.List;

    public class ListofmealAdapter extends RecyclerView.Adapter<ListofmealAdapter.ViewHolder> {
        Context context;
        List<Meal>meals;
        listofmealView view;
        public ListofmealAdapter(Context context, List<Meal> meals , listofmealView view) {
            this.context = context;
            this.meals = meals;
            this.view = view;
        }


        public void setList(List<Meal> updateMeals){
            this.meals=updateMeals;
            notifyDataSetChanged();

        }
        @NonNull
        @Override
        public ListofmealAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            LayoutInflater inflater=(LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            View view=inflater.inflate(R.layout.fragment_categorylistmeal,parent,false);
         ListofmealAdapter.ViewHolder  viewHolder =new ListofmealAdapter.ViewHolder(view);
            return viewHolder;
        }

        @Override
        public void onBindViewHolder(@NonNull ListofmealAdapter.ViewHolder holder, int position) {
            Meal meal = meals.get(position);
            holder.ListOfMealName.setText(meal.getStrMeal());
            Glide.with(context).load(meal.getStrMealThumb()).into(holder.ListOfMealImage);
            if(onMealItemClick != null)
                holder.itemView.setOnClickListener(v -> onMealItemClick.onItemClick(position));
        }

        ListofmealAdapter.onMealItemClick onMealItemClick;
        public  void  setOnMealItemClick(ListofmealAdapter.onMealItemClick onclick){
            onMealItemClick = onclick;
        }
        public interface onMealItemClick{
            void  onItemClick(int pos);
        }
        @Override
        public int getItemCount() {
            if(meals==null) return 0;
            return meals.size();
        }

        class ViewHolder extends RecyclerView.ViewHolder {
            private TextView ListOfMealName ;
            private ImageView ListOfMealImage;


            public ViewHolder(@NonNull View itemView) {
                super(itemView);
                ListOfMealName = itemView.findViewById(R.id.category);
                ListOfMealImage = itemView.findViewById(R.id.categoryImag);



            }
        }
    }



