/*
 * Copyright (c) 2015-present, Parse, LLC.
 * All rights reserved.
 *
 * This source code is licensed under the BSD-style license found in the
 * LICENSE file in the root directory of this source tree. An additional grant
 * of patent rights can be found in the PATENTS file in the same directory.
 */
package com.parse.starter;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import android.view.View;
import android.widget.Button;
import android.widget.Switch;

import com.parse.LogInCallback;
import com.parse.Parse;
import com.parse.ParseAnalytics;
import com.parse.ParseAnonymousUtils;
import com.parse.ParseException;
import com.parse.ParseUser;
import com.parse.SaveCallback;


public class MainActivity extends AppCompatActivity {
  public Button button;
    public Button button1;


  @Override
  protected void onCreate(Bundle savedInstanceState) {
      super.onCreate(savedInstanceState);
      setContentView(R.layout.activity_main);
      button = findViewById(R.id.creBtn);
      button.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View view) {
              openMain2Activity();

          }
      });
      button1 = findViewById(R.id.loginBtn);
      button1.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View view) {
              openloginActivity();
          }
      });
  }
public void openMain2Activity(){
    Intent intent = new Intent(this,Main2Activity.class);
    startActivity(intent);
    }
    public void openloginActivity(){
    Intent login = new Intent(this,loginActivity.class);
    startActivity(login);
    }


  }

