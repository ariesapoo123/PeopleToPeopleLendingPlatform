package com.parse.starter;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Main2Activity extends AppCompatActivity {
    public Button lbutton;
    public Button bbutton1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        lbutton = findViewById(R.id.lenderBtn);
        lbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openSignup();

            }
        });
        bbutton1= findViewById(R.id.borrowerBtn);
        bbutton1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openSignup();
            }
        });
    }
    public void openSignup(){
        Intent intent = new Intent(this,signupActivity.class);
        startActivity(intent);
    }


}

