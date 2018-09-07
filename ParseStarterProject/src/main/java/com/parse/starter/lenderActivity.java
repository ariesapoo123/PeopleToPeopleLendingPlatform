package com.parse.starter;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class lenderActivity extends AppCompatActivity {
    public Button advloan;
    public Button accloan;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lender);

        advloan = findViewById(R.id.advLoan);
        advloan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openMain2Activity();

            }
        });
        accloan = findViewById(R.id.List);
        accloan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                borrowerList();

            }
        });

    }

    public void openMain2Activity() {
        Intent intent = new Intent(this, advLoan.class);
        startActivity(intent);
    }
    private void borrowerList() {

        Intent req = new Intent(this, BorrowerListActivity.class);
        startActivity(req);
    }


}
