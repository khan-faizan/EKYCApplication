package com.example.swapnil.ekycapplication;

import android.content.Context;
import android.content.Intent;
import androidx.appcompat.app.AppCompatActivity;

import android.net.ConnectivityManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Capturefingerprint extends AppCompatActivity {
    Button NextBtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_capturefingerprint);
        NextBtn=(Button)findViewById(R.id.nextfingerprint);

        ConnectivityManager connectivityManager
                = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);

        if(CheckInternet.isNetworkAvailable(connectivityManager)) {
            NextBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent GotoSelectionActivity = new Intent(Capturefingerprint.this, KYC_Form.class);
                    startActivity(GotoSelectionActivity);
                    finish();
                }
            });
        }else{
            Intent gotologinscreen=new Intent(Capturefingerprint.this,NoInternet.class);
            startActivity(gotologinscreen);
        }
    }
}
