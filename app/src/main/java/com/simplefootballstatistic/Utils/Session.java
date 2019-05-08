package com.simplefootballstatistic.Utils;

import android.content.Context;
import android.content.SharedPreferences;

public class Session {

    private SharedPreferences sharedPreferences;
    public Session(Context context){
        sharedPreferences = context.getSharedPreferences("Session",0);
    }

    /*************************** Basic *********************************/
    /*String*/
    public void setSessionString(String name, String value){
        sharedPreferences.edit().putString(name,value).apply();
    }
    public String getSessionString(String name,String value){
        return sharedPreferences.getString(name,value);
    }

    /*Boolean*/
    public void setSessionBoolean(String name, Boolean value){
        sharedPreferences.edit().putBoolean(name,value).apply();
    }
    public Boolean getSessionBoolean(String name,Boolean value){
        return sharedPreferences.getBoolean(name,value);
    }

    /*Integer*/
    public void setSessionInteger(String name, Integer value){
        sharedPreferences.edit().putInt(name,value).apply();
    }
    public Integer getSessionInteger(String name,Integer value){
        return sharedPreferences.getInt(name,value);
    }

    /*Long*/
    public void setSessionLong(String name, Long value){
        sharedPreferences.edit().putLong(name,value).apply();
    }
    public Long getSessionLong(String name, Long value){
        return sharedPreferences.getLong(name,value);
    }

    /************************ End Basic *******************************/

    /************************ Command *********************************/
    /* Clear Session*/
    public void clearSession(){
        sharedPreferences.edit().clear().apply();
    }

}
