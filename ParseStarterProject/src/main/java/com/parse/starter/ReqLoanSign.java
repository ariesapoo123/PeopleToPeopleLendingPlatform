package com.parse.starter;

import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.parse.GetCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseUser;
import com.parse.SaveCallback;

public class ReqLoanSign extends AppCompatActivity {

    public Button BtnSignUp;
    public EditText loanAmt, interestRt, LoanType;
    public TextView borrowReq;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_req_loan_sign);
        loanAmt = (EditText) findViewById(R.id.loanAmt);
        interestRt = (EditText) findViewById(R.id.interestRt);
        LoanType = (EditText) findViewById(R.id.LoanType);

        BtnSignUp = (Button) findViewById(R.id.bBtnSignUp);
        borrowReq = (TextView) findViewById(R.id.borrowReq);

        BtnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                validate1();
            }
        });
    }

    private void validate1() {

        SharedPreferences preferences = getSharedPreferences("login", MODE_PRIVATE);

        final String getFullName = loanAmt.getText().toString();
        final String getLocation = LoanType.getText().toString();
        final String getMobileNumber = interestRt.getText().toString();
        final String getEmail = preferences.getString("username", "");



        if (getFullName.equals("") || getFullName.length() == 0
                || getMobileNumber.equals("") || getMobileNumber.length() == 0
                || getLocation.equals("") || getLocation.length() == 0
                ) {

            Toast.makeText(getApplicationContext(), "All fields are required", Toast.LENGTH_SHORT).show();
        }
        else
        {
            ParseQuery<ParseObject> query = new ParseQuery<ParseObject>("Borrow_requests");
            query.whereEqualTo("email", getEmail);
            query.getFirstInBackground(new GetCallback<ParseObject>() {
                @Override
                public void done(ParseObject object, ParseException e) {
                    if (e == null) {
                        //object.getString("name");
                        Toast.makeText(ReqLoanSign.this, "User already exist", Toast.LENGTH_SHORT).show();
                    } else {
                        if (e.getCode() == ParseException.OBJECT_NOT_FOUND) {

                            ParseObject parseObject = new ParseObject("Borrow_requests");
                            parseObject.put("loan_amt", getFullName);
                            parseObject.put("email", getEmail);
                            parseObject.put("interest_rate", getMobileNumber);
                            parseObject.put("type_of_loan", getLocation);


                            parseObject.saveInBackground(new SaveCallback() {
                                @Override
                                public void done(ParseException e) {
                                    if (e == null) {
                                        Toast.makeText(ReqLoanSign.this, "Saved Successfully", Toast.LENGTH_SHORT).show();
                                    } else {
                                        Toast.makeText(ReqLoanSign.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                                    }
                                }
                            });

                        } else {
                            Toast.makeText(ReqLoanSign.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    }
                }
            });
        }




    }
}

