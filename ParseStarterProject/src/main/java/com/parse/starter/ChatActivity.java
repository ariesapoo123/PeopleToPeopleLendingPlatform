package com.parse.starter;

import android.content.SharedPreferences;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseUser;
import com.parse.SaveCallback;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ChatActivity extends AppCompatActivity {

    ListView chatList;
    SwipeRefreshLayout swipeRefreshLayout;
    ArrayAdapter<String> adapter;
    ArrayList<String> messages = new ArrayList<>();

    EditText msg;

    @Override
    protected void onResume() {
        setTitle(getIntent().getStringExtra("email_id"));
        super.onResume();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);

        chatList = (ListView)findViewById(R.id.chatListView);
        swipeRefreshLayout = findViewById(R.id.swipe_refresh);
        adapter = new ArrayAdapter<>(this,android.R.layout.simple_list_item_1,messages);
        msg = findViewById(R.id.chatEditText);

        chatList.setAdapter(adapter);
        receiveMessage();

        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                receiveMessage();
            }
        });

    }

    public void onSendChat(View view){
        sendMessage();
    }

    private void sendMessage(){
        SharedPreferences preferences = getSharedPreferences("login", MODE_PRIVATE);
        
        String sender = preferences.getString("username","");
        String receiver = getIntent().getStringExtra("email_id");

        if(!msg.getText().toString().isEmpty()){

            messages.add("=> "+msg.getText().toString().trim());
            adapter.notifyDataSetChanged();

            ParseObject object = new ParseObject("Messages");
            object.put("message",msg.getText().toString().trim());
            object.put("sender",sender);
            object.put("receiver",receiver);

            object.saveInBackground(new SaveCallback() {
                @Override
                public void done(ParseException e) {
                    if(e == null){
                        //Change Image View (Tick)
                        Toast.makeText(ChatActivity.this, "Sent", Toast.LENGTH_SHORT).show();
                    }else {
                        Toast.makeText(ChatActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }
    }

    private void receiveMessage(){

        messages.clear();
        final SharedPreferences preferences = getSharedPreferences("login", MODE_PRIVATE);

        ParseQuery<ParseObject> query1 = new ParseQuery<ParseObject>("Messages");
        query1.whereEqualTo("sender",getIntent().getStringExtra("email_id"));
        query1.whereEqualTo("receiver",preferences.getString("username",""));

        ParseQuery<ParseObject> query2 = new ParseQuery<ParseObject>("Messages");
        query2.whereEqualTo("sender",preferences.getString("username",""));
        query2.whereEqualTo("receiver",getIntent().getStringExtra("email_id"));

        ParseQuery<ParseObject> newQuery = ParseQuery.or(Arrays.asList(query1,query2));

        newQuery.findInBackground(new FindCallback<ParseObject>() {
            @Override
            public void done(List<ParseObject> objects, ParseException e) {
                if(e == null){
                    if(objects.size() > 0){
                        for (ParseObject obj : objects){
                            String sender = obj.getString("sender");
                            String receiver = obj.getString("receiver");
                            String message = obj.getString("message");

                            if(sender.equalsIgnoreCase(preferences.getString("username",""))){
                                messages.add("=> "+message);
                            }else {
                                messages.add(message);
                            }
                        }

                        adapter.notifyDataSetChanged();
                    }
                }else {
                    Toast.makeText(ChatActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                }
            }
        });

        if(swipeRefreshLayout.isRefreshing())
            swipeRefreshLayout.setRefreshing(false);

    }


}
