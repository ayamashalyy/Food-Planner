package com.example.food_planner.Common;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;

import com.example.food_planner.R;

import java.util.List;

public class onboardingViewPagerAdapter extends PagerAdapter {
    Context context;

    public onboardingViewPagerAdapter(Context context, List<ScreenItems_onboarding> mylistscreen) {
        this.context = context;
        this.mylistscreen = mylistscreen;
    }

    List<ScreenItems_onboarding>mylistscreen;

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((View)object);
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View layoutScreen = inflater.inflate(R.layout.layout_screen,null);
        ImageView imgSlide = layoutScreen.findViewById(R.id.img);
        TextView title = layoutScreen.findViewById(R.id.tv_title);
        TextView description = layoutScreen.findViewById(R.id.tv_description);
        title.setText(mylistscreen.get(position).getTitle());
        description.setText(mylistscreen.get(position).getDescription());
        imgSlide.setImageResource(mylistscreen.get(position).getScreenImg());
        container.addView(layoutScreen);
        return layoutScreen;

    }

    @Override
    public int getCount() {
        return mylistscreen.size();
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == object;
    }
}
