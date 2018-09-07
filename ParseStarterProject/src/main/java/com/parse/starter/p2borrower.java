package com.parse.starter;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.parse.ParseAnalytics;
import com.parse.ParseUser;

public class p2borrower extends AppCompatActivity {

    public Button ReqLoan;
    public Button LendersList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_p2borrower);
        ReqLoan = findViewById(R.id.ReqLoan);
        ReqLoan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                reqsign();
            }
        });

        LendersList = findViewById(R.id.LendersList);
        LendersList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                lendersList();
            }
        });


       }

    private void reqsign() {
        Intent intent = new Intent(getApplicationContext(), ReqLoanSign.class);
        startActivity(intent);


    }

    private void lendersList() {

        Intent req = new Intent(this, LendersListActivity.class);
        startActivity(req);

    }


}




