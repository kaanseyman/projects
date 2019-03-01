package com.example.kaans.harmony;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Html;
import android.text.format.DateFormat;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.firebase.ui.auth.AuthUI;
import com.firebase.ui.database.FirebaseListAdapter;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;


public class ChatSection extends AppCompatActivity {
    FirebaseListAdapter<ChatMessage> adapter;
    private static int SIGN_IN_REQUEST_CODE = 1;

    ImageView submitButton;
    EditText input;
    FloatingActionButton fab;
    RelativeLayout activity_main;
    ListView listOfMessage;
    FirebaseDatabase firebaseDatabase;
    DatabaseReference newRef;
    String username,chatid;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat_section);
        submitButton = findViewById(R.id.fab);
        fab = findViewById(R.id.fab);
        input=findViewById(R.id.input);
        activity_main = findViewById(R.id.activity_main);
        getSupportActionBar().setBackgroundDrawable( new ColorDrawable(Color.parseColor("#6E5D5D")));
        getSupportActionBar().setTitle(Html.fromHtml("<font color=\"#8F8484\">" + getString(R.string.app_name) + "</font>"));
        Intent intent=getIntent();
        chatid=intent.getStringExtra("ChatId");


        firebaseDatabase = FirebaseDatabase.getInstance();
        newRef = firebaseDatabase.getReference("Users");

        newRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    final String uid = FirebaseAuth.getInstance().getCurrentUser().getUid();
                    HashMap<String, String> hashMap = (HashMap<String, String>)dataSnapshot.child(uid).getValue();
                    username=hashMap.get("Username");



            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });


        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                FirebaseDatabase.getInstance().getReference().child("Message").child("Private").child(chatid).push().setValue(new ChatMessage(input.getText().toString(),username
                        ));
                input.setText("");

            }
        });
            displayChatMessage();

    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
        MenuInflater menuInflater=getMenuInflater();
        menuInflater.inflate(R.menu.chat_menu, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        super.onOptionsItemSelected(item);
        if (item.getItemId()==R.id.delete){
            FirebaseDatabase.getInstance().getReference().child("Message").child("Private").child(chatid).removeValue();
            Intent intent=new Intent(this,HomePage.class);
            startActivity(intent);
        }

        return true;
    }

    private void displayChatMessage() {

        listOfMessage = findViewById(R.id.list_of_message);
        adapter = new FirebaseListAdapter<ChatMessage>(this,ChatMessage.class,R.layout.list_item,FirebaseDatabase.getInstance().getReference().child("Message").child("Private").child(chatid))
        {
            @Override
            protected void populateView(View v, ChatMessage model, int position) {


                //Get references to the views of list_item.xml
                TextView messageText, messageUser, messageTime;
                messageText = v.findViewById(R.id.message_text);
                messageUser = v.findViewById(R.id.message_user);
                messageTime = v.findViewById(R.id.message_time);

                messageText.setText(model.getmText());
                messageUser.setText(model.getmUser());
                messageTime.setText(DateFormat.format("dd-MM-yyyy (HH:mm:ss)", model.getmTime()));

            }
        };
        listOfMessage.setAdapter(adapter);
    }

}
