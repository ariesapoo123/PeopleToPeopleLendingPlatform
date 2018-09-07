package com.parse.starter;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;

import java.util.ArrayList;
import java.util.List;

public class LendersListActivity extends AppCompatActivity {


    CustomAdapter arrayAdapter;
    ArrayList<Lender> lenders = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lenders_list);
        setTitle(" Lenders List");
        final ListView LenderslistView;
        LenderslistView = (ListView) findViewById(R.id.LenderslistView);

        ParseQuery<ParseObject> query = new ParseQuery<ParseObject>("Lender_requests");
        query.findInBackground(new FindCallback<ParseObject>() {
            @Override
            public void done(List<ParseObject> objects, ParseException e) {

                if (e == null)
                {
                    if(objects.size()>0){
                        for (ParseObject obj : objects){
                            String loan_amt = obj.getString("loan_amt");
                            String interest_rate = obj.getString("interest_rate");
                            String email_id = obj.getString("email");
                            String type_of_loan = obj.getString("type_of_loan");

                            //Toast.makeText(LendersListActivity.this, email_id, Toast.LENGTH_SHORT).show();
                            //Toast.makeText(LendersListActivity.this, interest_rate, Toast.LENGTH_SHORT).show();

                            Lender lender = new Lender(loan_amt,interest_rate,email_id,type_of_loan);
                            lenders.add(lender);
                        }

                        arrayAdapter = new CustomAdapter(LendersListActivity.this, lenders);
                        LenderslistView.setAdapter(arrayAdapter);
                    }else {
                        Toast.makeText(LendersListActivity.this, "Empty list", Toast.LENGTH_SHORT).show();
                    }
                }else {
                    Toast.makeText(LendersListActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                }
            }});

        LenderslistView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(LendersListActivity.this,ChatActivity.class);
                intent.putExtra("email_id",lenders.get(position).getEmail_id());
                startActivity(intent);
            }
        });

    }
}
