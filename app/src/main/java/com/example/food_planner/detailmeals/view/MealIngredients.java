package com.example.food_planner.detailmeals.view;

public class MealIngredients {
    private String strIngredient;
    private String strMeasure;

    public String getStrIngredient() {
        return strIngredient;
    }

    public void setStrIngredient(String strIngredient) {
        this.strIngredient = strIngredient;
    }

    public String getStrMeasure() {
        return strMeasure;
    }

    public void setStrMeasure(String strMeasure) {
        this.strMeasure = strMeasure;
    }

    public MealIngredients(String strIngredient, String strMeasure) {
        this.strIngredient = strIngredient;
        this.strMeasure = strMeasure;
    }
}
