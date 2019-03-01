package com.example.kaans.harmony;

import android.content.Intent;
import android.database.Cursor;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class LogIn extends AppCompatActivity {
    EditText eUser, ePass;
    Button login,forget,signup;

    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_in);
        eUser=findViewById(R.id.editUsername);
        ePass=findViewById(R.id.editPassword);
        login=findViewById(R.id.loginButton);
        signup=findViewById(R.id.signupButton);
        forget=findViewById(R.id.forgetButton);

        getSupportActionBar().hide();




        mAuth = FirebaseAuth.getInstance();
        FirebaseUser user = mAuth.getCurrentUser();
         if (user != null) {
             Intent intent = new Intent(getApplicationContext(),HomePage.class);
             startActivity(intent);

         }


    }
    public void loginButton(View v) {
        if (eUser.getText().toString().matches("") || ePass.getText().toString().matches("")) {
            Toast.makeText(this, "Fill e-mail and password", Toast.LENGTH_SHORT).show();
        } else {
            mAuth.signInWithEmailAndPassword(eUser.getText().toString(), ePass.getText().toString())
                    .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                Intent intent = new Intent(getApplicationContext(), HomePage.class);
                                startActivity(intent);
                            }

                        }
                    }).addOnFailureListener(this, new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    Toast.makeText(LogIn.this, e.getLocalizedMessage(), Toast.LENGTH_LONG).show();
                }
            });

        }
    }
    public void goSignup(View v){
        Intent intent = new Intent(getApplicationContext(),SignUp.class);
        startActivity(intent);

    }
    public void goReset(View v){
        Intent intent = new Intent(getApplicationContext(),PasswordReset.class);
        startActivity(intent);
    }

}
