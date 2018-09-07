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

public class advLoan extends AppCompatActivity {

    public TextView textView;
    public EditText loanAmount, interestRate, TypeOfLoan;
    public Button signUpBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adv_loan);

        loanAmount = (EditText) findViewById(R.id.loanAmount);
        interestRate = (EditText) findViewById(R.id.interestRate);
        TypeOfLoan = (EditText) findViewById(R.id.TypeOfLoan);

        signUpBtn = (Button) findViewById(R.id.signUpBtn);
        textView = (TextView) findViewById(R.id.textView);

        signUpBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                validate();
            }
        });
    }

    private void validate() {
        SharedPreferences preferences = getSharedPreferences("login",MODE_PRIVATE);

        final String getFullName = loanAmount.getText().toString();
        final String getLocation = TypeOfLoan.getText().toString();
        final String getMobileNumber = interestRate.getText().toString();
        final String getEmail = preferences.getString("username","");


        if (getFullName.equals("") || getFullName.length() == 0
                || getMobileNumber.equals("") || getMobileNumber.length() == 0
                || getLocation.equals("") || getLocation.length() == 0
                ) {

            Toast.makeText(getApplicationContext(), "All fields are required", Toast.LENGTH_SHORT).show();

        }
        else{
            ParseQuery<ParseObject> query = new ParseQuery<ParseObject>("Lender_requests");
            query.whereEqualTo("email", getEmail);
            query.getFirstInBackground(new GetCallback<ParseObject>() {
                @Override
                public void done(ParseObject object, ParseException e) {
                    if (e == null) {
                        //object.getString("name");
                        Toast.makeText(advLoan.this, "User already exist", Toast.LENGTH_SHORT).show();
                    } else {
                        if (e.getCode() == ParseException.OBJECT_NOT_FOUND) {

                            ParseObject parseObject = new ParseObject("Lender_requests");
                            parseObject.put("loan_amt", getFullName);
                            parseObject.put("email", getEmail);
                            parseObject.put("interest_rate", getMobileNumber);
                            parseObject.put("type_of_loan", getLocation);


                            parseObject.saveInBackground(new SaveCallback() {
                                @Override
                                public void done(ParseException e) {
                                    if (e == null) {
                                        Toast.makeText(advLoan.this, "Saved Successfully", Toast.LENGTH_SHORT).show();
                                    } else {
                                        Toast.makeText(advLoan.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                                    }
                                }
                            });

                        } else {
                            Toast.makeText(advLoan.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    }
                }
            });
        }




    }
}