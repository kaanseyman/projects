package com.example.kaans.harmony;

import com.google.firebase.database.IgnoreExtraProperties;

import java.util.Date;
@IgnoreExtraProperties
public class ChatMessage {
    private String mText;
    private String mUser;
    private long mTime;

    public ChatMessage(String messageText, String messageUser) {
        this.mText = messageText;
        this.mUser = messageUser;

        mTime = new Date().getTime();
    }

    public String getmText() {
        return mText;
    }
    public ChatMessage(){}

    public void setmText(String mText) {
        this.mText = mText;
    }

    public String getmUser() {
        return mUser;
    }

    public void setmUser(String mUser) {
        this.mUser = mUser;
    }

    public long getmTime() {
        return mTime;
    }

    public void setmTime(long mTime) {
        this.mTime = mTime;
    }
}
