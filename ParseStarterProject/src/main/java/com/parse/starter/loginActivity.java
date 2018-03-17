package com.parse.starter;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class loginActivity extends AppCompatActivity {

    public TextView textView;
    public EditText emailId,password;
    public Button loginBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);


        emailId = (EditText) findViewById(R.id.emailId);
        password = (EditText)findViewById(R.id.password);


        loginBtn = (Button) findViewById(R.id.loginBtn);
        textView = (TextView)findViewById(R.id.borrowReq);

        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                validate();
            }
        });
    }

    private void validate() {

        String getPassword = password.getText().toString();
        String getEmailId = emailId.getText().toString();




        if (getPassword.equals("") || getPassword.length() == 0
                || getEmailId.equals("") || getEmailId.length() == 0

                )

            Toast.makeText(getApplicationContext(),"All fields are required",Toast.LENGTH_SHORT).show();

        else if(!Patterns.EMAIL_ADDRESS.matcher(getEmailId).matches()){
            emailId.setError("Enter a valid email");
            emailId.requestFocus();
            return;
        }

        else
            Toast.makeText(getApplicationContext(),"Do Login",Toast.LENGTH_SHORT).show();

    }


}


