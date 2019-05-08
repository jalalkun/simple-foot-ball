package com.simplefootballstatistic.Utils;

import android.util.Log;

public class LogTest {
    private static boolean debug = true;
    public static void look(String Content){
        if(debug){
            Log.d("Test : ", "Content : "+Content);
        }else{
            //Debug Dead
        }
    }
}
