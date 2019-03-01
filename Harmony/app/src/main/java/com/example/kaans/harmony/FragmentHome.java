package com.example.kaans.harmony;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.database.connection.ListenHashProvider;

import java.util.ArrayList;
import java.util.HashMap;

public class FragmentHome extends Fragment {
    Button survey;
    LinearLayout surveysection;
    FirebaseDatabase firebaseDatabase;
    DatabaseReference newRef,newRef2,newRef3;
    String personality;
    String opponentid;
    ArrayList<HashMap <String,String>> imageList;
    ArrayList<String> usernamelist;
    ChatListAdapter adapter,adapter2;
    ArrayList<String> currentchat;
    RecyclerView recyclerView;




    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view=inflater.inflate(R.layout.fragment_home,container,false);
        survey=view.findViewById(R.id.surveyButton);
        surveysection=view.findViewById(R.id.qsection);
        usernamelist=new ArrayList<>();
        imageList=new ArrayList<>();
        firebaseDatabase = FirebaseDatabase.getInstance();
        adapter = new ChatListAdapter();
        surveysection.setVisibility(View.GONE);
        recyclerView=view.findViewById(R.id.chats);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setHasFixedSize(true);

        newRef = firebaseDatabase.getReference("Users");
        newRef2 = firebaseDatabase.getReference("Message");

        newRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                final String uid = FirebaseAuth.getInstance().getCurrentUser().getUid();
                HashMap<String, String> hashMap = (HashMap<String, String>)dataSnapshot.child(uid).getValue();
                personality=hashMap.get("Type");
                if (personality==null)
        {surveysection.setVisibility(View.VISIBLE);}
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
            }
        });
        if (personality!=null)
        {surveysection.setVisibility(view.INVISIBLE);}
        newRef2.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                final String uid= FirebaseAuth.getInstance().getCurrentUser().getUid();
                HashMap<String, String> chatlist = (HashMap<String, String>) dataSnapshot.child("Private").getValue();
                ArrayList<String> chatarray = new ArrayList<String>(chatlist.keySet());
                currentchat= new ArrayList<String>();
                ArrayList<String> opponents=new ArrayList<>();
                for (String current:chatarray )
                {
                    if (current.contains(uid)) {
                        currentchat.add(current);
                    }
                }
                for (String tempchat:currentchat)
                {
                    int currentindex;
                    currentindex=tempchat.indexOf(uid);
                    if (currentindex == 28)
                    {
                        opponentid=tempchat.substring(0,28);

                        opponents.add(opponentid);
                    }
                    else if (currentindex ==0)
                    {
                        opponentid=tempchat.substring(28,56);
                        opponents.add(opponentid);
                    }
                }
                if(imageList.size()>0) {
                    imageList.clear();
                }
                recursiveOppenent(opponents,currentchat,0);
                adapter.setChatListAdapter(new ChatListAdapter.chatOnClickAdapter() {
                    @Override
                    public void onClick(int position) {

                        String temp=currentchat.get(position);
                        Intent i=new Intent(getContext(),ChatSection.class);
                        i.putExtra("ChatId",temp);
                        getContext().startActivity(i);
                    }
                });



            }


            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        survey.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getActivity(), Survey.class);
                startActivity(intent);
            }
        });
        return view;
    }
 public void recursiveOppenent(final ArrayList<String> recur,final ArrayList<String> current, final int i)
  {
      if (i>=recur.size()){
          usernameLister();
          return;
      }
    newRef.child(recur.get(i)).addListenerForSingleValueEvent(new ValueEventListener() {
        @Override
        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
            int r=i+1;
            String usernametemp;
            String imagetemp;
            imagetemp=dataSnapshot.child("downloadurl").getValue(String.class);
            usernametemp=dataSnapshot.child("Username").getValue(String.class);
            HashMap<String,String> temphash=new HashMap<>();
            temphash.put("Username",usernametemp);
            temphash.put("downloadurl",imagetemp);
            temphash.put("currentchat",current.get(i));
            imageList.add(temphash);
           // usernamelist.add(usernametemp);
            recursiveOppenent(recur,current,r);
        }

        @Override
        public void onCancelled(@NonNull DatabaseError databaseError) {

        }
    });
  }
  public void usernameLister(){

      adapter.setCurrentchat(imageList);
      adapter.notifyDataSetChanged();
      recyclerView.setAdapter(adapter);

  }

}
