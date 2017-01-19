package com.example.espino.scaneat.repositories;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

/**
 * Created by espino on 2/12/16.
 */

public class MyPreferences {
    private static final String PREFERENCES_FILES = "UserAccount";
    public static final String USERNAME = "username";
    public static final String EMAIL = "email";
    public static final String PASSWD = "password";
    public static final String REMEMBER = "remember_password";

    private static MyPreferences instance;
    private SharedPreferences preferences;
    private Context context;

    private MyPreferences(Context context){
        this.context = context;
        preferences = PreferenceManager.getDefaultSharedPreferences(context);
    }

    public static MyPreferences getInstance(Context context){
        if(instance == null)
            instance = new MyPreferences(context);

        return instance;
    }

    private SharedPreferences.Editor editor(){
        return preferences.edit();
    }

    public boolean putString(String key, String value){

        if(editor().putString(key, value).commit())
            return true;

        return false;
    }

    public String getString(String key){
        return preferences.getString(key, null);
    }

    public boolean putBoolean(String key, boolean value){

        if(editor().putBoolean(key, value).commit())
            return true;

        return false;
    }

    public Boolean getBoolean(String key){
        return preferences.getBoolean(key, false);
    }


}
