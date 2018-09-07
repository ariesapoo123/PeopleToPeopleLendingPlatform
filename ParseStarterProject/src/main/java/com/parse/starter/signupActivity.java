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
import android.app.ProgressDialog;

import com.parse.FindCallback;
import com.parse.GetCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseUser;
import com.parse.SaveCallback;
import com.parse.SignUpCallback;

import java.util.List;

public class signupActivity extends AppCompatActivity {

    public TextView textView;
    public EditText name, mobNum, email, adhar, password, confirmPassword;
    public Button signUpBtn;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        name = (EditText) findViewById(R.id.bname);
        mobNum = (EditText) findViewById(R.id.bmobNum);
        email = (EditText) findViewById(R.id.bemail);
        adhar = (EditText) findViewById(R.id.badhar);
        password = (EditText) findViewById(R.id.bpassword);
        confirmPassword = (EditText) findViewById(R.id.bconfirmPassword);

        signUpBtn = (Button) findViewById(R.id.bBtnSignUp);
        textView = (TextView) findViewById(R.id.borrowReq);


        signUpBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                validate();
            }
        });
    }

    private void validate() {

        final String getFullName = name.getText().toString();
        final String getEmailId = email.getText().toString();
        final String getMobileNumber = mobNum.getText().toString();
        final String getLocation = adhar.getText().toString();
        final String getPassword = password.getText().toString();
        final String getConfirmPassword = confirmPassword.getText().toString();


        if (getFullName.equals("") || getFullName.length() == 0
                || getEmailId.equals("") || getEmailId.length() == 0
                || getMobileNumber.equals("") || getMobileNumber.length() == 0
                || getLocation.equals("") || getLocation.length() == 0
                || getPassword.equals("") || getPassword.length() == 0
                || getConfirmPassword.equals("") || getConfirmPassword.length() == 0)

            Toast.makeText(getApplicationContext(), "All fields are required", Toast.LENGTH_SHORT).show();
        if (!Patterns.EMAIL_ADDRESS.matcher(getEmailId).matches()) {
            email.setError("Enter a valid email");
            email.requestFocus();
            return;
        }
        if (!getConfirmPassword.equals(getPassword)) {
            confirmPassword.setError("Both passwords do not match");
            confirmPassword.requestFocus();
            return;

        }
        if (mobNum.length() < 10) {
            mobNum.setError("Please enter a valid Mobile Number");
            mobNum.requestFocus();
            return;
        }
        if (adhar.length() < 12) {
            adhar.setError("Please enter a valid Aadhar number");
            adhar.requestFocus();
            return;
        }
        if (password.length() < 6) {
            password.setError("Password should be of at least 6 characters");
            password.requestFocus();
            return;

        }

        ParseQuery<ParseObject> query = new ParseQuery<ParseObject>("Lender");
        query.whereEqualTo("adhaar", getLocation);
        query.getFirstInBackground(new GetCallback<ParseObject>() {
            @Override
            public void done(ParseObject object, ParseException e) {
                if (e == null) {
                    object.getString("name");
                    Toast.makeText(signupActivity.this, "User already exist", Toast.LENGTH_SHORT).show();
                } else {
                    if (e.getCode() == ParseException.OBJECT_NOT_FOUND) {

                        ParseUser user = new ParseUser();
                        user.put("username",getEmailId);
                        user.put("password",getPassword);

                        user.signUpInBackground(new SignUpCallback() {
                            @Override
                            public void done(ParseException e) {
                                if(e == null){
                                    ParseObject parseObject = new ParseObject("Lender");
                                    parseObject.put("name", getFullName);
                                    parseObject.put("email_id", getEmailId);
                                    parseObject.put("mob_num", getMobileNumber);
                                    parseObject.put("adhaar", getLocation);
                                    parseObject.put("password", getPassword);
                                    parseObject.saveInBackground(new SaveCallback() {
                                        @Override
                                        public void done(ParseException ex) {
                                            if (ex == null) {
                                                Toast.makeText(signupActivity.this, "Saved Successfully", Toast.LENGTH_SHORT).show();

                                            } else {
                                                Toast.makeText(signupActivity.this, ex.getMessage(), Toast.LENGTH_SHORT).show();
                                            }
                                        }
                                    });
                                }else{
                                    Toast.makeText(signupActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                                }
                            }
                        });

                    } else {
                        Toast.makeText(signupActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });


    }
}




