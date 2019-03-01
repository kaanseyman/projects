package com.example.kaans.harmony;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import org.w3c.dom.Text;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;



public class ChatListAdapter extends RecyclerView.Adapter<ChatListAdapter.viewHolder> {
    ArrayList<HashMap<String,String>> currentchat;
    String username;
    chatOnClickAdapter onclick;



    @NonNull
    @Override
    public viewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup,final int viewType) {
        View view=LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.home_item,viewGroup,false);
        return new viewHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull viewHolder viewHolder, final int position) {

        HashMap <String, String> temp;
        temp=currentchat.get(position);
        viewHolder.chatUsername.setText(temp.get("Username"));
        Picasso.get().load(temp.get("downloadurl")).into(viewHolder.PP);
        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onclick.onClick(position);

            }
        });


    }



    @Override
    public int getItemCount() {
        if (currentchat == null) {
            return 0;
        }
        return currentchat.size();
    }
    public static class viewHolder extends RecyclerView.ViewHolder {
        TextView chatUsername;
        ImageView PP;

        public viewHolder(@NonNull View itemView) {
            super(itemView);

            chatUsername=itemView.findViewById(R.id.chatUsername);
            PP=itemView.findViewById(R.id.chatPP);

        }
    }

   public ChatListAdapter(){}

    public void setCurrentchat(ArrayList<HashMap <String, String>> currentchat)
    {
        this.currentchat = currentchat;
    }
    public void setChatListAdapter(chatOnClickAdapter temp)
    {
        this.onclick=temp;
    }
    public interface chatOnClickAdapter {
        void onClick(int position);
    }


}

