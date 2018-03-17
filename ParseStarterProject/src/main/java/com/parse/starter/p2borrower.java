package com.parse.starter;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class p2borrower extends AppCompatActivity {

    public Button ReqLoan;

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
        });}

    private void reqsign() {

        Intent req = new Intent(this, ReqLoanSign.class);
        startActivity(req);
    }

}




