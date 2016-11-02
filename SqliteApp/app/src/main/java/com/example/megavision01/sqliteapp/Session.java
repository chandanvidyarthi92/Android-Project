package com.example.megavision01.sqliteapp;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

/**
 * Created by MegaVision01 on 10/24/2016.
 */
public class Session {
    SharedPreferences preferences;
    SharedPreferences.Editor editor;
    Context ctx;

    public Session(Context ctx) {
        this.ctx = ctx;
      preferences = PreferenceManager.getDefaultSharedPreferences(ctx);
        editor = preferences.edit();
    }

    public void setLoggedIn(boolean loggedin)
    {
        editor.putBoolean("LoggedIn Mode",loggedin);
        editor.commit();
    }
    public boolean loggedin()
    {
        return preferences.getBoolean("loogedIn",false);
    }
}
