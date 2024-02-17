package com.example.food_planner.Calender.view;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.food_planner.R;
import com.example.food_planner.model.Meal;

import java.util.ArrayList;

public class CalendarAdapter extends RecyclerView.Adapter <CalendarAdapter.ViewHolder>{
    ArrayList<Meal> meals;
    Context context;
    CalenderOnClickListener listener;
    TextView day;
    RecyclerView recyclerView;
    public CalendarAdapter( Context context, CalenderOnClickListener listener, TextView day, RecyclerView recyclerView) {
        meals = new ArrayList<>();
        this.context = context;
        this.listener = listener;
        this.day = day;
        this.recyclerView = recyclerView;
    }

    public void setList(ArrayList<Meal> updateMeals) {
        this.meals = updateMeals;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public CalendarAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater  = (LayoutInflater) context .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.fragment_calender_view, parent , false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;    }

    @Override
    public void onBindViewHolder(@NonNull CalendarAdapter.ViewHolder holder, int position) {
        Meal meal = meals.get(position);
        holder.name.setText(meal.getStrMeal());
        Glide.with(context).load(meal.getStrMealThumb()).into(holder.image);
        holder.delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.OnClick(meal);
                Toast.makeText(context, "Removed from your favorite list", Toast.LENGTH_SHORT).show();

            }

        });
        if(meals.size() > 0){
            day.setVisibility(View.VISIBLE);
            recyclerView.setVisibility(View.VISIBLE);
        }

    }

    @Override
    public int getItemCount() {
        return meals.size();
    }
    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView name ;
        ImageView image;
        ToggleButton delete;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.calenderText);
            image = itemView.findViewById(R.id.calenderImag);
            delete=itemView.findViewById(R.id.btn_removeCalender);

        }


    }
}
