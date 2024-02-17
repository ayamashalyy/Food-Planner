package com.example.food_planner.network;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

public class NetworkConnection {
    public static Boolean getConnectivity(Context context){
        ConnectivityManager service = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetwork = service.getActiveNetworkInfo();
        if(activeNetwork != null){
            return true;
            } else
            {
            return false;
            }

        }
    }

