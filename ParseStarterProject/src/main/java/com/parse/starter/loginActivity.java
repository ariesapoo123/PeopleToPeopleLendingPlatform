package com.parse.starter;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.parse.GetCallback;
import com.parse.LogInCallback;
import com.parse.ParseAnalytics;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseUser;
import com.parse.SaveCallback;
import com.parse.SignUpCallback;

public class loginActivity extends AppCompatActivity {


    public TextView textView;
    public EditText emailId, password;
    public Button loginBtn;
    public RadioGroup RadioGroup;
    public RadioButton lenderRadioBtn, borrowerRadioBtn;
    SharedPreferences preferences;
    SharedPreferences.Editor editor;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        preferences= getSharedPreferences("login",MODE_PRIVATE);
        editor=preferences.edit();

        emailId = (EditText) findViewById(R.id.emailId);
        password = (EditText) findViewById(R.id.bpassword);


        loginBtn = (Button) findViewById(R.id.loginBtn);
        textView = (TextView) findViewById(R.id.borrowReq);
        RadioGroup = (RadioGroup) findViewById(R.id.Radiogroup);

        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                lenderRadioBtn=findViewById(R.id.lenderRadioBtn);
                borrowerRadioBtn=findViewById(R.id.borrowerRadioBtn);

              if(lenderRadioBtn.isChecked())
              {
                  String email = emailId.getText().toString();
                  String pass = password.getText().toString();
                  Toast.makeText(getApplicationContext(), " Lender selected", Toast.LENGTH_SHORT).show();
                  validate();
                  ParseQuery<ParseObject> query1 = new ParseQuery<ParseObject>("Lender");
                  query1.whereEqualTo("email_id", email);
                  query1.whereEqualTo("password", pass);
                  query1.getFirstInBackground(new GetCallback<ParseObject>()
                  {
                      @Override
                      public void done(ParseObject object, ParseException e)
                       {
                          if (e == null)
                          {
                              object.getString("email_id");
                              object.getString("password");

                              advSign();
                          }
                          else {
                              if(e.getCode()==ParseException.OBJECT_NOT_FOUND) {
                                  Toast.makeText(getApplicationContext(), "please signup", Toast.LENGTH_SHORT).show();
                              }
                              else {
                                  Toast.makeText(loginActivity.this,e.getMessage(), Toast.LENGTH_SHORT).show();
                              }
                          }
                       }





                 });
              }
               else if (borrowerRadioBtn.isChecked())
                {
                    String email = emailId.getText().toString();
                    String pass = password.getText().toString();
                    Toast.makeText(getApplicationContext(), " Borrower  selected", Toast.LENGTH_SHORT).show();
                    validate();
                    ParseQuery<ParseObject> query1 = new ParseQuery<ParseObject>("Borrower");
                    query1.whereEqualTo("email_id", email);
                    query1.whereEqualTo("password", pass);
                    query1.getFirstInBackground(new GetCallback<ParseObject>()
                    {
                        @Override
                        public void done(ParseObject object, ParseException e)
                        {
                            if (e == null)
                            {
                                object.getString("email_id");
                                object.getString("password");
                                reqSign();
                            }
                            else{
                                if(e.getCode()==ParseException.OBJECT_NOT_FOUND) {
                                    Toast.makeText(getApplicationContext(), "please signup", Toast.LENGTH_SHORT).show();
                                }
                                else {
                                    Toast.makeText(loginActivity.this,e.getMessage(), Toast.LENGTH_SHORT).show();
                                }

                            }

                        }





                    });


                }
        }

    });
    }

    private void validate() {

        String getPassword = password.getText().toString();
        String getEmailId = emailId.getText().toString();


        if (getPassword.equals("") || getPassword.length() == 0
                || getEmailId.equals("") || getEmailId.length() == 0

                )

            Toast.makeText(getApplicationContext(), "All fields are required", Toast.LENGTH_SHORT).show();

        else if (!Patterns.EMAIL_ADDRESS.matcher(getEmailId).matches()) {
            emailId.setError("Enter a valid email");
            emailId.requestFocus();
            return;
        }



    }
    private void advSign() {
        editor.putString("username" ,emailId.getText().toString().trim());
        editor.putString("type" ,"lender");
        editor.apply();
        Intent lend = new Intent(this,lenderActivity.class);
        startActivity(lend);
    }
    private void reqSign() {
        editor.putString("username" ,emailId.getText().toString().trim());
        editor.putString("type" ,"borrower");
        editor.apply();
        Intent bor = new Intent(this, p2borrower.class);
        startActivity(bor);
    }
}


