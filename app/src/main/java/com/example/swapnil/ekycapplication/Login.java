package com.example.swapnil.ekycapplication;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.net.ConnectivityManager;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import static androidx.constraintlayout.widget.Constraints.TAG;

public class Login extends Activity {
    Button loginButton;
    String usernameStr,passwordStr;
    EditText usernameEt,passwordEt;
    ProgressBar progressBar;
    // [START declare_auth]
    private FirebaseAuth mAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);


        ConnectivityManager connectivityManager
                = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);

        if(CheckInternet.isNetworkAvailable(connectivityManager)){
            loginButton=(Button)findViewById(R.id.loginbtn);
            usernameEt=(EditText)findViewById(R.id.input_username);
            passwordEt=(EditText)findViewById(R.id.input_password);
            progressBar=(ProgressBar)findViewById(R.id.loginprogressbar) ;





            loginButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    progressBar.setVisibility(View.VISIBLE);
                    usernameStr=usernameEt.getText().toString();
                    passwordStr=passwordEt.getText().toString();
                    logindata(usernameStr,passwordStr);
                }
            });
        }else{
            Intent gotologinscreen=new Intent(Login.this,NoInternet.class);
            startActivity(gotologinscreen);
        }



    }


    public void logindata(String username,String password) {

        if (usernameEt.getText().toString().trim().equals("")) {
            progressBar.setVisibility(View.GONE);
            usernameEt.setError("Field can`t be left blank");
        } else if (passwordEt.getText().toString().trim().equals("")) {
            progressBar.setVisibility(View.GONE);
            passwordEt.setError("Field can`t be left blank");
        } else {

            /*
            Login code
             */

        }
    }
}
