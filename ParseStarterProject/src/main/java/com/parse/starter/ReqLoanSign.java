package com.parse.starter;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class ReqLoanSign extends AppCompatActivity {

    public Button BtnSignUp;
    public EditText loanAmt, interestRt, email, LoanType;
    public TextView borrowReq;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_req_loan_sign);
        loanAmt = (EditText) findViewById(R.id.loanAmt);
        interestRt = (EditText) findViewById(R.id.interestRt);
        email = (EditText) findViewById(R.id.email);
        LoanType = (EditText) findViewById(R.id.LoanType);

        BtnSignUp = (Button) findViewById(R.id.BtnSignUp);
        borrowReq = (TextView) findViewById(R.id.borrowReq);

        BtnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                validate1();
            }
        });
    }

    private void validate1() {

        String getFullName = loanAmt.getText().toString();
        String getEmailId = email.getText().toString();
        String getLocation = LoanType.getText().toString();
        String getMobileNumber = interestRt.getText().toString();


        if (getFullName.equals("") || getFullName.length() == 0
                || getEmailId.equals("") || getEmailId.length() == 0
                || getMobileNumber.equals("") || getMobileNumber.length() == 0
                || getLocation.equals("") || getLocation.length() == 0
                )

            Toast.makeText(getApplicationContext(), "All fields are required", Toast.LENGTH_SHORT).show();

        else
            Toast.makeText(getApplicationContext(), "Do Sign Up", Toast.LENGTH_SHORT).show();

    }
}

