package com.lri.learningness.database.user;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import static com.lri.learningness.database.user.UserContract.CREATE_TABLE_USERS;
import static com.lri.learningness.database.user.UserContract.DELETE_TABLE_USERS;


public class UserDbHelper extends SQLiteOpenHelper {

    public static final int DATABASE_VERSION = 1; // increment int whenever change the db schema
    public static final String DATABASE_NAME = "users.db";

    public UserDbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE_USERS);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(DELETE_TABLE_USERS);
        onCreate(db);
    }

    @Override
    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        onUpgrade(db, oldVersion, newVersion);
    }
}
