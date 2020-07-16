package com.example.swapnil.ekycapplication;

import android.content.Intent;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Success extends AppCompatActivity {
    Button NextBtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_success);

        NextBtn=(Button)findViewById(R.id.donebtn);

        NextBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent GotoSelectionActivity=new Intent(Success.this,Selectionactivity.class);
                startActivity(GotoSelectionActivity);
                finish();
            }
        });
    }
}
