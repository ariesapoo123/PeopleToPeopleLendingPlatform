package com.parse.starter;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Patterns;
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
import com.parse.SignUpCallback;

public class bsignup extends AppCompatActivity {


    public TextView borrowReq;
    public EditText bname, bmobNum, bemail, badhar, bpassword, bconfirmPassword;
    public Button bBtnSignUp;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bsignup);

        bname = (EditText) findViewById(R.id.bname);
        bmobNum = (EditText)findViewById(R.id.bmobNum);
        bemail = (EditText) findViewById(R.id.bemail);
        badhar = (EditText) findViewById(R.id.badhar);
        bpassword = (EditText) findViewById(R.id.bpassword);
        bconfirmPassword= (EditText) findViewById(R.id.bconfirmPassword);

        bBtnSignUp = (Button) findViewById(R.id.bBtnSignUp);
        borrowReq = (TextView)findViewById(R.id.borrowReq);


        bBtnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                validate1();
            }
        });
    }
    private void validate1(){

        final String getFullName = bname.getText().toString();
        final String getEmailId = bemail.getText().toString();
        final String getMobileNumber = bmobNum.getText().toString();
        final String getLocation = badhar.getText().toString();
        final String getPassword = bpassword.getText().toString();
        final String getConfirmPassword = bconfirmPassword.getText().toString();
        if (getFullName.equals("") || getFullName.length() == 0
                || getEmailId.equals("") || getEmailId.length() == 0
                || getMobileNumber.equals("") || getMobileNumber.length() == 0
                || getLocation.equals("") || getLocation.length() == 0
                || getPassword.equals("") || getPassword.length() == 0
                || getConfirmPassword.equals("") || getConfirmPassword.length() == 0)

            Toast.makeText(getApplicationContext(),"All fields are required",Toast.LENGTH_SHORT).show();
        if(!Patterns.EMAIL_ADDRESS.matcher(getEmailId).matches()){
            bemail.setError("Enter a valid email");
            bemail.requestFocus();
            return;
        }
        if (!getConfirmPassword.equals(getPassword)){
            bconfirmPassword.setError("Both passwords do not match");
            bconfirmPassword.requestFocus();
            return;

        }
        if (bmobNum.length()<10){
            bmobNum.setError("Please enter a valid Mobile Number");
            bmobNum.requestFocus();
            return;
        }
        if (badhar.length()<12){
            badhar.setError("Please enter a valid Aadhar number");
            badhar.requestFocus();
            return;
        }
        if (bpassword.length()<6){
            bpassword.setError("Password should be of at least 6 characters");
            bpassword.requestFocus();
            return;

        }
        ParseQuery<ParseObject> query = new ParseQuery<ParseObject>("Borrower");
        query.whereEqualTo("adhaar", getLocation);
        query.getFirstInBackground(new GetCallback<ParseObject>() {
            @Override
            public void done(ParseObject object, ParseException e) {
                if (e == null) {
                    object.getString("name");
                    Toast.makeText(bsignup.this, "User already exist", Toast.LENGTH_SHORT).show();
                } else {
                    if (e.getCode() == ParseException.OBJECT_NOT_FOUND) {

                        ParseUser user = new ParseUser();
                        user.put("username",getEmailId);
                        user.put("password",getPassword);

                        user.signUpInBackground(new SignUpCallback() {
                            @Override
                            public void done(ParseException e) {
                                if(e == null){
                                    ParseObject parseObject = new ParseObject("Borrower");
                                    parseObject.put("name", getFullName);
                                    parseObject.put("email_id", getEmailId);
                                    parseObject.put("mob_num", getMobileNumber);
                                    parseObject.put("adhaar", getLocation);
                                    parseObject.put("password", getPassword);

                                    parseObject.saveInBackground(new SaveCallback() {
                                        @Override
                                        public void done(ParseException ex) {
                                            if (ex == null) {
                                                Toast.makeText(bsignup.this, "Saved Successfully", Toast.LENGTH_SHORT).show();

                                            } else {
                                                Toast.makeText(bsignup.this, ex.getMessage(), Toast.LENGTH_SHORT).show();
                                            }
                                        }
                                    });
                                }else{
                                    Toast.makeText(bsignup.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                                }
                            }
                        });

                    } else {
                        Toast.makeText(bsignup.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });




        

    }

}

