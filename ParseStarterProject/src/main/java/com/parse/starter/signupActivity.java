package com.parse.starter;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import android.app.ProgressDialog;

public class signupActivity extends AppCompatActivity {

    public TextView textView;
    public EditText name, mobNum, email, adhar, password, confirmPassword;
    public Button signUpBtn;
    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        name = (EditText) findViewById(R.id.name);
        mobNum = (EditText)findViewById(R.id.mobNum);
        email = (EditText) findViewById(R.id.email);
        adhar = (EditText) findViewById(R.id.adhar);
        password = (EditText) findViewById(R.id.password);
        confirmPassword= (EditText) findViewById(R.id.confirmPassword);

        signUpBtn = (Button) findViewById(R.id.BtnSignUp);
        textView = (TextView)findViewById(R.id.borrowReq);
        progressDialog = new ProgressDialog(this);

        signUpBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                validate();
            }
        });
    }
    private void validate() {

        String getFullName = name.getText().toString();
        String getEmailId = email.getText().toString();
        String getMobileNumber = mobNum.getText().toString();
        String getLocation = adhar.getText().toString();
        String getPassword = password.getText().toString();
        String getConfirmPassword = confirmPassword.getText().toString();



        if (getFullName.equals("") || getFullName.length() == 0
                || getEmailId.equals("") || getEmailId.length() == 0
                || getMobileNumber.equals("") || getMobileNumber.length() == 0
                || getLocation.equals("") || getLocation.length() == 0
                || getPassword.equals("") || getPassword.length() == 0
                || getConfirmPassword.equals("") || getConfirmPassword.length() == 0)

            Toast.makeText(getApplicationContext(),"All fields are required",Toast.LENGTH_SHORT).show();
        if(!Patterns.EMAIL_ADDRESS.matcher(getEmailId).matches()){
            email.setError("Enter a valid email");
            email.requestFocus();
            return;
        }
        if (!getConfirmPassword.equals(getPassword)){
            confirmPassword.setError("Both passwords do not match");
            confirmPassword.requestFocus();
            return;

        }
        if (mobNum.length()<10){
            mobNum.setError("Please enter a valid Mobile Number");
            mobNum.requestFocus();
            return;
        }
        if (adhar.length()<12){
            adhar.setError("Please enter a valid Aadhar number");
            adhar.requestFocus();
            return;
        }
        if (password.length()<6){
            password.setError("Password should be of at least 6 characters");
            password.requestFocus();
            return;


        }


            Toast.makeText(getApplicationContext(),"Do Sign Up",Toast.LENGTH_SHORT).show();

    }


}



