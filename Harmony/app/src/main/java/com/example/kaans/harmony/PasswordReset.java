package com.example.kaans.harmony;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;

public class PasswordReset extends AppCompatActivity {
    EditText email;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_password_reset);
        email=findViewById(R.id.resetEmail);
    }
    public void forgotPass(View v)
    {
        FirebaseAuth.getInstance().sendPasswordResetEmail(email.getText().toString())
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()) {
                            Toast.makeText(PasswordReset.this, "E-mail sent, please check", Toast.LENGTH_SHORT).show();
                            Intent intent=new Intent(PasswordReset.this,LogIn.class);
                            startActivity(intent);
                        }
                        else{
                            Toast.makeText(PasswordReset.this,"E-mail Not Found",Toast.LENGTH_LONG).show();
                        }

                    }
                });
    }
}
