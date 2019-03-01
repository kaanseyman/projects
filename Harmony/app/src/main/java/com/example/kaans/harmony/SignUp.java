package com.example.kaans.harmony;

import android.app.DatePickerDialog;
import android.content.ContentValues;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.media.Rating;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.Calendar;

public class SignUp extends AppCompatActivity {
    private FirebaseAuth mAuth;
    EditText userName,password,eMail,fName,lName;
    TextView displayDate,chooseDate;
    DatePickerDialog.OnDateSetListener dateSetListener;
    private static final String TAG = "SignUp";
    FirebaseDatabase firebaseDatabase;
    DatabaseReference myRef;
    String ID,date,today;





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        mAuth = FirebaseAuth.getInstance();
        displayDate=findViewById(R.id.tvDate);
        chooseDate=findViewById(R.id.chooseDate);
        userName= findViewById(R.id.signUser);
        password=findViewById(R.id.signPassword);
        eMail=findViewById(R.id.signEmail);
        fName=findViewById(R.id.signName);
        lName=findViewById(R.id.signLastname);
        firebaseDatabase = FirebaseDatabase.getInstance();
        myRef=firebaseDatabase.getReference();
        getSupportActionBar().hide();


        chooseDate.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Calendar cal = Calendar.getInstance();
                int year = cal.get(Calendar.YEAR);
                int month = cal.get(Calendar.MONTH);
                int day = cal.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog dialog = new DatePickerDialog(
                        SignUp.this,
                        android.R.style.Theme_Holo_Light_Dialog_MinWidth,
                        dateSetListener,
                        year,month,day);
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                dialog.show();
            }
        });
        dateSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                month = month + 1;
                Log.d(TAG, "onDateSet: dd/mm/yyy: " + day + "/" + month + "/" + year);

                date = day + "/" + month + "/" + year;
                displayDate.setText(date);

            }
        };
    }

    public void onSignup (View v){

        mAuth.createUserWithEmailAndPassword(eMail.getText().toString(),password.getText().toString())
        .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {
                Toast.makeText(SignUp.this,"Successfully",Toast.LENGTH_LONG).show();
                    firebaseDatabase = FirebaseDatabase.getInstance();
                    DatabaseReference myRef=firebaseDatabase.getReference();
                    ID=mAuth.getCurrentUser().getUid();
                    myRef.child("Users").child(ID).child("Email").setValue(eMail.getText().toString());
                    myRef.child("Users").child(ID).child("Password").setValue(password.getText().toString());
                    myRef.child("Users").child(ID).child("Username").setValue(userName.getText().toString());
                    myRef.child("Users").child(ID).child("FirstName").setValue(fName.getText().toString());
                    myRef.child("Users").child(ID).child("LastName").setValue(lName.getText().toString());
                    myRef.child("Users").child(ID).child("BirthDate").setValue(date);
                    

                Intent intent=new Intent(getApplicationContext(),LogIn.class);
                startActivity(intent);}

            }
        }).addOnFailureListener(this, new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(SignUp.this,e.getLocalizedMessage(),Toast.LENGTH_LONG).show();

            }
        });







    }
    public void onGo (View v) {
        Intent intent=new Intent(this,LogIn.class);
        startActivity(intent);
    }





}