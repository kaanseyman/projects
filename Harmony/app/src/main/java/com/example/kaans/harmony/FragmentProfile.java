package com.example.kaans.harmony;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import java.util.HashMap;

public class FragmentProfile extends Fragment {
    private FirebaseAuth mAuth;
    Button upload;
    ImageView pp;
    TextView username,nL,email,type;
    FirebaseDatabase firebaseDatabase;
    DatabaseReference newRef;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_profile,container,false);
        upload=view.findViewById(R.id.uploadButton);
        pp=view.findViewById(R.id.showerPp);

        username=view.findViewById(R.id.showUsername);
        nL=view.findViewById(R.id.showNL);
        email=view.findViewById(R.id.showEmail);

        upload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getActivity(), PphotoChanger.class);
                startActivity(intent);
            }
        });
        mAuth = FirebaseAuth.getInstance();
        FirebaseUser user = mAuth.getCurrentUser();
        if (user != null) {


            Picasso.get().load(user.getPhotoUrl()).into(pp);

        }

        firebaseDatabase =FirebaseDatabase.getInstance();
        newRef = firebaseDatabase.getReference("Users");

        newRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                final String uid = FirebaseAuth.getInstance().getCurrentUser().getUid();
                HashMap<String, String> hashMap = (HashMap<String, String>)dataSnapshot.child(uid).getValue();
                username.setText("Users know you as :"+hashMap.get("Username"));
                nL.setText("Welcome Harmony "+hashMap.get("FirstName")+" "+hashMap.get("LastName"));
                email.setText("Registered Email: "+hashMap.get("Email"));





            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        return view;
    }



}
