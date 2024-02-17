package com.example.food_planner.model;


import java.io.Serializable;

public class Ingredient implements Serializable {
    public String strIngredient;

    public Ingredient() {
    }

    public String getStrIngredient() {
        return strIngredient;
    }

    public void setStrIngredient(String strIngredient) {
        this.strIngredient = strIngredient;
    }

    public Ingredient(String strIngredient) {
        this.strIngredient = strIngredient;
    }
}
