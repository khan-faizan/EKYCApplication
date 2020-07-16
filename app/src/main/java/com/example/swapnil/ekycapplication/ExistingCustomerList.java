package com.example.swapnil.ekycapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.view.View;

public class ExistingCustomerList extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_existing_customer_list);

        ConnectivityManager connectivityManager
                = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);

        if(CheckInternet.isNetworkAvailable(connectivityManager)) {

        }else{
            Intent gotologinscreen=new Intent(ExistingCustomerList.this,NoInternet.class);
            startActivity(gotologinscreen);
        }

    }
}
