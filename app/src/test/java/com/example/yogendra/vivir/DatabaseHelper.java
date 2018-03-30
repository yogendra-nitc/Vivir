package com.example.yogendra.vivir;

import android.content.Context;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by yogendra on 30/3/18.
 */

public class DatabaseHelper extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "check";
    public static final String TABLE1 = "user";
    public static final String COL1 = "email";
    public static final String COL2 = "password";
    public static final String COL3 = "rdate";


    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table " + TABLE1 + "(email VARCHAR PRIMARY KEY , password VARCHAR, rdate DATE )");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+TABLE1);
        onCreate(db);
    }
}

