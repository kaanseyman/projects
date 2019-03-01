package com.example.kaans.harmony;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Survey extends AppCompatActivity {
    RadioButton a1y,a1u,a1n,a2y,a2u,a2n,a3y,a3u,a3n,a4y,a4u,a4n,a5y,a5u,a5n,a6y,a6u,a6n,a7y,a7u,a7n,a8y,a8n,a8u,a9y,a9u,a9n,a10y,a10u,a10n,a11y,a11u,a11n,a12y,a12u,a12n,a13y,a13u,a13n,a14y,a14u,a14n,a15y,a15u,a15n,a16y,a16u,a16n;
    RadioButton a17y,a17u,a17n,a18y,a18u,a18n,a19y,a19u,a19n,a20y,a20u,a20n,a21y,a21u,a21n,a22y,a22u,a22n,a23y,a23u,a23n,a24y,a24u,a24n,a25y,a25u,a25n,a26y,a26u,a26n,a27y,a27u,a27n,a28y,a28u,a28n;
    Button take;
    String Extraversion,Introversion,Sensing,Intuition,Thinking,Feeling,Judging,Perceiving,Type;

    FirebaseDatabase firebaseDatabase;
    FirebaseAuth mAuth;
    FirebaseUser user;





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_survey);
        firebaseDatabase = FirebaseDatabase.getInstance();
        getSupportActionBar().setBackgroundDrawable( new ColorDrawable(Color.parseColor("#6E5D5D")));
        getSupportActionBar().setTitle(Html.fromHtml("<font color=\"#8F8484\">" + getString(R.string.app_name) + "</font>"));
        mAuth=FirebaseAuth.getInstance();
        user = mAuth.getCurrentUser();
        Extraversion="E";
        Introversion="I";
        Sensing="S";
        Intuition="N";
        Thinking="T";
        Feeling="F";
        Judging="J";
        Perceiving="P";
        Type="";
        a1y=findViewById(R.id.r1y);
        a1u=findViewById(R.id.r1u);
        a1n=findViewById(R.id.r1n);
        a2y=findViewById(R.id.r2y);
        a2u=findViewById(R.id.r2u);
        a2n=findViewById(R.id.r2n);
        a3y=findViewById(R.id.r3y);
        a3u=findViewById(R.id.r3u);
        a3n=findViewById(R.id.r3n);
        a4y=findViewById(R.id.r4y);
        a4u=findViewById(R.id.r4u);
        a4n=findViewById(R.id.r4n);
        a5y=findViewById(R.id.r5y);
        a5u=findViewById(R.id.r5u);
        a5n=findViewById(R.id.r5n);
        a6y=findViewById(R.id.r6y);
        a6u=findViewById(R.id.r6u);
        a6n=findViewById(R.id.r6n);
        a7y=findViewById(R.id.r7y);
        a7u=findViewById(R.id.r7u);
        a7n=findViewById(R.id.r7n);
        a8y=findViewById(R.id.r8y);
        a8u=findViewById(R.id.r8u);
        a8n=findViewById(R.id.r8n);
        a9y=findViewById(R.id.r9y);
        a9u=findViewById(R.id.r9u);
        a9n=findViewById(R.id.r9n);
        a10y=findViewById(R.id.r10y);
        a10u=findViewById(R.id.r10u);
        a10n=findViewById(R.id.r10n);
        a11y=findViewById(R.id.r11y);
        a11u=findViewById(R.id.r11u);
        a11n=findViewById(R.id.r11n);
        a12y=findViewById(R.id.r12y);
        a12u=findViewById(R.id.r12u);
        a12n=findViewById(R.id.r12n);
        a13y=findViewById(R.id.r13y);
        a13u=findViewById(R.id.r13u);
        a13n=findViewById(R.id.r13n);
        a14y=findViewById(R.id.r14y);
        a14u=findViewById(R.id.r14u);
        a14n=findViewById(R.id.r14n);
        a15y=findViewById(R.id.r14y);
        a15u=findViewById(R.id.r15u);
        a15n=findViewById(R.id.r15n);
        a16y=findViewById(R.id.r15y);
        a16u=findViewById(R.id.r16u);
        a16n=findViewById(R.id.r16n);
        a17y=findViewById(R.id.r16y);
        a17u=findViewById(R.id.r17u);
        a17n=findViewById(R.id.r1n);
        a18y=findViewById(R.id.r18y);
        a18u=findViewById(R.id.r18u);
        a18n=findViewById(R.id.r18n);
        a19y=findViewById(R.id.r19y);
        a19u=findViewById(R.id.r19u);
        a19n=findViewById(R.id.r20n);
        a20y=findViewById(R.id.r20y);
        a20u=findViewById(R.id.r20u);
        a20n=findViewById(R.id.r20n);
        a21y=findViewById(R.id.r21y);
        a21u=findViewById(R.id.r21u);
        a21n=findViewById(R.id.r21n);
        a22y=findViewById(R.id.r22y);
        a22u=findViewById(R.id.r22u);
        a22n=findViewById(R.id.r22n);
        a23y=findViewById(R.id.r23y);
        a23u=findViewById(R.id.r23u);
        a23n=findViewById(R.id.r23n);
        a24y=findViewById(R.id.r24y);
        a24u=findViewById(R.id.r24u);
        a24n=findViewById(R.id.r24n);
        a25y=findViewById(R.id.r25y);
        a25u=findViewById(R.id.r25u);
        a25n=findViewById(R.id.r25n);
        a26y=findViewById(R.id.r26y);
        a26u=findViewById(R.id.r26u);
        a26n=findViewById(R.id.r26n);
        a27y=findViewById(R.id.r27y);
        a27u=findViewById(R.id.r27u);
        a27n=findViewById(R.id.r27n);
        a28y=findViewById(R.id.r28y);
        a28u=findViewById(R.id.r28u);
        a28n=findViewById(R.id.r28n);

    }
    public void onSave (View v)
    {
        int eScore=0;
        int sScore=0;
        int tScore=0;
        int jScore=0;
        ///ExI
       if(a2y.isChecked())
       {
           eScore+=2;
       }
       else if(a2u.isChecked())
        {
            eScore++;
        }
        if(a9y.isChecked())
        {
            eScore+=2;
        }
        else if(a9u.isChecked())
        {
            eScore++;
        }
        if(a12n.isChecked())
        {
            eScore+=2;
        }
        else if(a12u.isChecked())
        {
            eScore++;
        }
        if(a15y.isChecked())
        {
            eScore+=2;
        }
        else if(a15u.isChecked())
        {
            eScore++;
        }
        if(a17y.isChecked())
        {
            eScore+=2;
        }
        else if(a17u.isChecked())
        {
            eScore++;
        }
        if(a21y.isChecked())
        {
            eScore+=2;
        }
        else if(a21u.isChecked())
        {
            eScore++;
        }
        if(a26y.isChecked())
        {
            eScore+=2;
        }
        else if(a26u.isChecked())
        {
            eScore++;
        }
        // SxN
        if(a3y.isChecked())
        {
            sScore+=2;
        }
        else if(a3u.isChecked())
        {
            sScore++;
        }
        if(a13n.isChecked())
        {
            sScore+=2;
        }
        else if(a13u.isChecked())
        {
            sScore++;
        }
        if(a14n.isChecked())
        {
            sScore+=2;
        }
        else if(a14u.isChecked())
        {
            sScore++;
        }
        if(a18n.isChecked())
        {
            sScore+=2;
        }
        else if(a18u.isChecked())
        {
            sScore++;
        }
        if(a19y.isChecked())
        {
            sScore+=2;
        }
        else if(a19u.isChecked())
        {
            sScore++;
        }
        if(a25y.isChecked())
        {
            sScore+=2;
        }
        else if(a25u.isChecked())
        {
            sScore++;
        }
        if(a27y.isChecked())
        {
            sScore+=2;
        }
        else if(a27u.isChecked())
        {
            sScore++;
        }
        // TxF
        if(a4y.isChecked())
        {
            tScore+=2;
        }
        else if(a4u.isChecked())
        {
            tScore++;
        }
        if(a5n.isChecked())
        {
            tScore+=2;
        }
        else if(a5u.isChecked())
        {
            tScore++;
        }
        if(a6n.isChecked())
        {
            tScore+=2;
        }
        else if(a6u.isChecked())
        {
            tScore++;
        }
        if(a7y.isChecked())
        {
            tScore+=2;
        }
        else if(a7u.isChecked())
        {
            tScore++;
        }
        if(a16y.isChecked())
        {
            tScore+=2;
        }
        else if(a16u.isChecked())
        {
            tScore++;
        }
        if(a22y.isChecked())
        {
            tScore+=2;
        }
        else if(a22u.isChecked())
        {
            tScore++;
        }
        if(a24n.isChecked())
        {
            tScore+=2;
        }
        else if(a24u.isChecked())
        {
            tScore++;
        }
        // JxP
        if(a1y.isChecked())
        {
            jScore+=2;
        }
        else if(a1u.isChecked())
        {
            jScore++;
        }
        if(a8y.isChecked())
        {
            jScore+=2;
        }
        else if(a8u.isChecked())
        {
            jScore++;
        }
        if(a10y.isChecked())
        {
            jScore+=2;
        }
        else if(a10u.isChecked())
        {
            jScore++;
        }
        if(a11n.isChecked())
        {
            jScore+=2;
        }
        else if(a11u.isChecked())
        {
            jScore++;
        }
        if(a20y.isChecked())
        {
            jScore+=2;
        }
        else if(a20u.isChecked())
        {
            jScore++;
        }
        if(a23n.isChecked())
        {
            jScore+=2;
        }
        else if(a23u.isChecked())
        {
            jScore++;
        }
        if(a28n.isChecked())
        {
            jScore+=2;
        }
        else if(a28u.isChecked())
        {
            jScore++;
        }
        //clustering

        if (eScore>7){
           Type+=Extraversion;
        }
        else {
           Type+=Introversion;
        }
        if (sScore>7){
            Type+=Sensing;
        }
        else {
            Type+=Intuition;
        }
        if (tScore>7){
            Type+=Thinking;
        }
        else {
            Type+=Feeling;
        }
        if (jScore>7){
            Type+=Judging;
        }
        else {
            Type+=Perceiving;
        }
        Toast.makeText(getApplicationContext(),"Successfully",Toast.LENGTH_LONG).show();

        addGroup(Type);

        if (Type!=null) {
            Intent intent = new Intent(this, HomePage.class);
            startActivity(intent);
        }


    }
    public void addGroup(String type)
    {   String email = user.getEmail();
        DatabaseReference myRef=firebaseDatabase.getReference();
        myRef.child("Types").child(type).child(FirebaseAuth.getInstance().getCurrentUser().getUid()).setValue(email);
        myRef.child("Users").child(FirebaseAuth.getInstance().getCurrentUser().getUid()).child("Type").setValue(type);
    }
}
