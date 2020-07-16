package com.example.swapnil.ekycapplication;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

public class CheckInternet {

    public static boolean isNetworkAvailable(ConnectivityManager connectivityManager) {

        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }

}
