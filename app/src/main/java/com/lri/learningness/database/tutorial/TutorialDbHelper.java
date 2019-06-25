package com.lri.learningness.database.tutorial;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import static com.lri.learningness.database.tutorial.TutorialContract.CREATE_TABLE_TUTORIAL;
import static com.lri.learningness.database.tutorial.TutorialContract.DELETE_TABLE_TUTORIAL;


public class TutorialDbHelper extends SQLiteOpenHelper {

    public static final int DATABASE_VERSION = 1; // increment int whenever change the db schema
    public static final String DATABASE_NAME = "tutorial.db";

    public TutorialDbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE_TUTORIAL);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(DELETE_TABLE_TUTORIAL);
        onCreate(db);
    }

    @Override
    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        onUpgrade(db, oldVersion, newVersion);
    }
}
