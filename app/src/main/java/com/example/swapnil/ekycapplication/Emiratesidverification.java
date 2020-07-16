package com.example.swapnil.ekycapplication;

import android.content.Context;
import android.content.Intent;
import androidx.appcompat.app.AppCompatActivity;

import android.net.ConnectivityManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Emiratesidverification extends AppCompatActivity {
Button NextBtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_emiratesidverification);

        NextBtn=(Button)findViewById(R.id.nextemiratesid);

        ConnectivityManager connectivityManager
                = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);

        if(CheckInternet.isNetworkAvailable(connectivityManager)) {
            NextBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent GotoSelectionActivity=new Intent(Emiratesidverification.this,Capturefingerprint.class);
                    startActivity(GotoSelectionActivity);
                    finish();
                }
            });
        }else{
            Intent gotologinscreen=new Intent(Emiratesidverification.this,NoInternet.class);
            startActivity(gotologinscreen);
        }


    }
}
