package com.example.food_planner.model;

import java.io.Serializable;

public class Area implements Serializable {
    String strArea;

    public Area() {
    }

    public String getStrArea() {
        return strArea;
    }

    public void setStrArea(String strArea) {
        this.strArea = strArea;
    }

    public Area(String strArea) {
        this.strArea = strArea;
    }
}
