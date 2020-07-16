package com.example.swapnil.ekycapplication;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

public class Splashscreen extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splashscreen);
        ConnectivityManager connectivityManager
                = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);

        if(CheckInternet.isNetworkAvailable(connectivityManager)){
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    Intent gotologinscreen=new Intent(Splashscreen.this,Login.class);
                    startActivity(gotologinscreen);
                    finish();
                }
            },1000);
        }else{
            Intent gotologinscreen=new Intent(Splashscreen.this,NoInternet.class);
            startActivity(gotologinscreen);
        }




        /*String  tag_string_req = "string_req";

        String url = "https://172.31.1.221:9043/ords/afexremit/ekyc/v1/ping";



        StringRequest strReq = new StringRequest(Request.Method.GET,
                url, new Response.Listener<String>() {


        }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(Splashscreen.this, error.toString(), Toast.LENGTH_SHORT).show();
                Log.e("Error",error.toString());
            }
        });

// Adding request to request queue
        AppController.getInstance().addToRequestQueue(strReq, tag_string_req);
     */


    }
}
