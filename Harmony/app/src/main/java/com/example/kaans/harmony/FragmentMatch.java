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
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Random;

public class FragmentMatch extends Fragment {
    Button go;
    TextView text;
    FirebaseDatabase firebaseDatabase;
    DatabaseReference newRef;
    String type, randomuser, chatid;
    Random random;
    MatchTimer timerRef;

    int r;

    @Nullable
    @Override
    public View onCreateView(@NonNull final LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_match, container, false);
        timerRef = new MatchTimer(getContext());
        go = view.findViewById(R.id.matchButton);
        text = view.findViewById(R.id.matchText);
        text.setText("This is 'Matching Page' to match someone like you.All matches are the same each personality group you in." +
                "You can match once every 3 hours.If you use the match button once, it will be able to 3 hours.");
        random = new Random();
        go.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Date currentTime = Calendar.getInstance().getTime();
                long mess=timerRef.getTime()-currentTime.getTime();
                mess=mess/1000;
                mess=mess/60;


                if(timerRef.getTime() > currentTime.getTime()){
                    Toast.makeText(getContext(),mess+" minutes left to match",Toast.LENGTH_SHORT).show();
                    return;
                }

                firebaseDatabase = FirebaseDatabase.getInstance();
                newRef = firebaseDatabase.getReference("Users");

                newRef.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        final String uid = FirebaseAuth.getInstance().getCurrentUser().getUid();
                        HashMap<String, String> hashMap = (HashMap<String, String>) dataSnapshot.child(uid).getValue();
                        type = hashMap.get("Type");
                        if (type == null) {
                            Toast.makeText(getContext(), "Please Let Us Know You", Toast.LENGTH_LONG).show();
                            Intent intent = new Intent(getContext(), Survey.class);
                            startActivity(intent);

                        }
                    }


                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });
                if (type != null) {
                    newRef = firebaseDatabase.getReference("Types");
                    newRef.addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                            HashMap<String, String> uidlist = (HashMap<String, String>) dataSnapshot.child(type).getValue();
                            ArrayList<String> uidAList = new ArrayList<String>(uidlist.keySet());
                            r = random.nextInt(uidAList.size());
                            randomuser = uidAList.get(r);
                            final String uid = FirebaseAuth.getInstance().getCurrentUser().getUid();
                            if (!uid.equals(randomuser)) {
                                if (randomuser.compareTo(uid) == 0) {
                                    chatid = uid + randomuser;
                                } else if (randomuser.compareTo(randomuser) == 0) {
                                    chatid = uid + randomuser;
                                }
                                firebaseDatabase.getReference().child("Message").child("Private").child(chatid).push();

                                Intent intent = new Intent(getActivity(), ChatSection.class);
                                intent.putExtra("ChatId", chatid);
                                startActivity(intent);
                            }
                        }


                        @Override
                        public void onCancelled(@NonNull DatabaseError databaseError) {

                        }

                    });
                    timerRef.setTime(currentTime.getTime()+10800000);
                }
            }
        });
        return view;
    }


}
