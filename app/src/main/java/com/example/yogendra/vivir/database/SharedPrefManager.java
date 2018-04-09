package com.example.yogendra.vivir.database;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by yogendra on 9/4/18.
 */
public class SharedPrefManager
{
    private static SharedPrefManager mInstance;
    private static Context mCtx;

    public static final String SHARED_PREF_NAME = "userLogin_SP";
    public static final String KEY_EMAIL = "email";
    public static final String KEY_NAME = "name";
    public static final String KEY_UTYPE = "userType";

    private SharedPrefManager(Context context)
    {
        mCtx = context;
    }

    public static synchronized SharedPrefManager getInstance(Context context)
    {
        if (mInstance == null)
        {
            mInstance = new SharedPrefManager(context);
        }
        return mInstance;
    }

    public boolean userLogin(String email, String name, String userType)
    {
        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME , Context.MODE_PRIVATE);
        SharedPreferences.Editor editor= sharedPreferences.edit();
        editor.putString(KEY_EMAIL,email);
        editor.putString(KEY_NAME,name);
        editor.putString(KEY_UTYPE,userType);
        editor.apply();
        return true;
    }

    public boolean isLoggedin()
    {
        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);

        if(sharedPreferences.getString(KEY_EMAIL,null)!= null)
        {
                return true;
        }
        return false;
    }

    public boolean logout()
    {
        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.clear();
        editor.apply();
        return true;
    }


}
