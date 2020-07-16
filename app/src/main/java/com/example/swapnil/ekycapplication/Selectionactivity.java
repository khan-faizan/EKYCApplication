package com.example.swapnil.ekycapplication;

import android.content.Context;
import android.content.Intent;
import androidx.appcompat.app.AppCompatActivity;

import android.net.ConnectivityManager;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.LinearLayout;

public class Selectionactivity extends AppCompatActivity {
LinearLayout OldCustomerbtn,NewcustomerBtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_selectionactivity);
        OldCustomerbtn=(LinearLayout) findViewById(R.id.oldcustomerll);
        NewcustomerBtn=(LinearLayout)findViewById(R.id.newcustomerll);


        ConnectivityManager connectivityManager
                = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);

        if(CheckInternet.isNetworkAvailable(connectivityManager)){
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    OldCustomerbtn.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Intent GotoSelectionActivity=new Intent(Selectionactivity.this,ExistingCustomerList.class);
                            startActivity(GotoSelectionActivity);
                        }
                    });

                    NewcustomerBtn.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Intent GotoSelectionActivity=new Intent(Selectionactivity.this,Emiratesidverification.class);
                            startActivity(GotoSelectionActivity);
                            finish();
                        }
                    });
                }
            },1000);
        }else{
            Intent gotologinscreen=new Intent(Selectionactivity.this,NoInternet.class);
            startActivity(gotologinscreen);
        }


    }
}
