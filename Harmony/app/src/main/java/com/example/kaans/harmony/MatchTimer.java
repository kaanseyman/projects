package com.example.kaans.harmony;

import android.content.Context;
import android.content.SharedPreferences;

public class MatchTimer {

    SharedPreferences pref;

    private long time;

    public MatchTimer(Context context){
        pref=context.getSharedPreferences("timer", Context.MODE_PRIVATE);
        loadData();
    }

    public void loadData(){
        time = pref.getLong("time",0);
    }

    public void updateData(){
        SharedPreferences.Editor ed = pref.edit();
        ed.putLong("time",time);
        ed.apply();
    }

    public void setTime(long time){this.time = time; updateData();}

    public long getTime(){return time;}
}
