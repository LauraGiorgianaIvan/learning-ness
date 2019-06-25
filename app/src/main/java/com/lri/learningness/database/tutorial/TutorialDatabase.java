package com.lri.learningness.database.tutorial;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.lri.learningness.model.tutorials.Tutorial;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import static android.provider.BaseColumns._ID;
import static com.lri.learningness.database.tutorial.TutorialContract.TutorialEntry.*;


public class TutorialDatabase implements TutorialContract.TutorialQuery {

    private TutorialDbHelper dbHelper;

    public TutorialDatabase(Context context) {
        dbHelper = new TutorialDbHelper(context);
    }

    private String[] userProjection = {
            _ID,
            COLUMN_NAME_TUTORIAL_NAME,
            COLUMN_NAME_LINK,
            COLUMN_NAME_IMG,
            COLUMN_NAME_DURATION,
            COLUMN_NAME_FAVORITE,
            COLUMN_NAME_CERTIFICATE,
            COLUMN_NAME_USERNAME
    };

    @Override
    public List<Tutorial> getAllTutorials() {
        List<Tutorial> tutorials = new ArrayList<>();
        SQLiteDatabase db = dbHelper.getReadableDatabase();

        Cursor cursor = db.query(
                TABLE_NAME, userProjection, null, null, null, null, null
        );
        while (cursor.moveToNext()) {
            Tutorial tutorial = buildTutorial(cursor);
            tutorials.add(tutorial);
        }
        cursor.close();
        return tutorials;
    }

    @Override
    public List<Tutorial> getUserTutorials(String username) {
        List<Tutorial> tutorials = new ArrayList<>();
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        String selection = COLUMN_NAME_USERNAME + " =?";
        String[] selectionArgs = {username};

        Cursor cursor = db.query(
                TABLE_NAME, userProjection, selection, selectionArgs, null, null, null
        );
        while (cursor.moveToNext()) {
            Tutorial tutorial = buildTutorial(cursor);
            tutorials.add(tutorial);
        }
        cursor.close();
        return tutorials;
    }

    @Override
    public void insertTutorial(Tutorial tutorial) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        db.insert(TABLE_NAME, null, bindTutorial(tutorial));
    }

    @Override
    public void deleteTutorial(Tutorial tutorial) {
        String selection = _ID + " = ?";
        String[] selectionArgs = {tutorial.getId()};
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        db.delete(TABLE_NAME, selection, selectionArgs);
    }

    @Override
    public void updateTutorial(Tutorial tutorial) {
        String selection = _ID + " = ?";
        String[] selectionArgs = {tutorial.getId()};

        SQLiteDatabase db = dbHelper.getWritableDatabase();
        db.update(TABLE_NAME, bindTutorial(tutorial), selection, selectionArgs);
    }

    @Override
    public Tutorial getTutorialById(String id) {
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        String selection = _ID + " = ?";
        String[] selectionArgs = {id};

        Cursor cursor = db.query(
                TABLE_NAME, userProjection, selection, selectionArgs, null, null, null
        );
        boolean isTutorialAvailable = cursor.moveToFirst();
        if (isTutorialAvailable) {
            Tutorial tutorial = buildTutorial(cursor);
            cursor.close();
            return tutorial;
        }
        return null;
    }

    @Override
    public Tutorial getTutorial() {
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        Cursor cursor = db.query(
                TABLE_NAME, userProjection, null, null, null, null, null
        );
        boolean isTutorialAvailable = cursor.moveToFirst();
        if (isTutorialAvailable) {
            Tutorial tutorial = buildTutorial(cursor);
            cursor.close();
            return tutorial;
        }
        return null;
    }

    @Override
    public Tutorial getTutorialByLink(String link) {
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        String selection = COLUMN_NAME_LINK + " = ?";
        String[] selectionArgs = {link};

        Cursor cursor = db.query(
                TABLE_NAME, userProjection, selection, selectionArgs, null, null, null
        );
        boolean isTutorialAvailable = cursor.moveToFirst();
        if (isTutorialAvailable) {
            Tutorial tutorial = buildTutorial(cursor);
            cursor.close();
            return tutorial;
        }
        return null;
    }

    @Override
    public Tutorial getUserTutorialByLink(String username, String link) {
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        String selection = COLUMN_NAME_LINK + " = ? AND " + COLUMN_NAME_USERNAME + " =? ";
        String[] selectionArgs = {link};

        Cursor cursor = db.query(
                TABLE_NAME, userProjection, selection, selectionArgs, null, null, null
        );
        boolean isTutorialAvailable = cursor.moveToFirst();
        if (isTutorialAvailable) {
            Tutorial tutorial = buildTutorial(cursor);
            cursor.close();
            return tutorial;
        }
        return null;
    }

    @Override
    public void close() {
        if (dbHelper != null) {
            dbHelper.close();
        }
    }

    private boolean getBoolFromString(String val) {
        return val.equalsIgnoreCase("true");
    }

    private Tutorial buildTutorial(Cursor cursor) {
        Tutorial tutorial = new Tutorial();
        tutorial.setId(cursor.getString(cursor.getColumnIndex(_ID)));
        tutorial.setName(cursor.getString(cursor.getColumnIndex(COLUMN_NAME_TUTORIAL_NAME)));
        tutorial.setLink(cursor.getString(cursor.getColumnIndex(COLUMN_NAME_LINK)));
        tutorial.setImgRes(cursor.getString(cursor.getColumnIndex(COLUMN_NAME_IMG)));
        tutorial.setDuration(cursor.getString(cursor.getColumnIndex(COLUMN_NAME_DURATION)));
        tutorial.setFavorite(getBoolFromString(cursor.getString(cursor.getColumnIndex(COLUMN_NAME_FAVORITE))));
        tutorial.setCertificated(getBoolFromString(cursor.getString(cursor.getColumnIndex(COLUMN_NAME_CERTIFICATE))));
        tutorial.setUserName(cursor.getString(cursor.getColumnIndex(COLUMN_NAME_USERNAME)));
        return tutorial;
    }

    private ContentValues bindTutorial(Tutorial tutorial) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(COLUMN_NAME_TUTORIAL_NAME, tutorial.getName());
        contentValues.put(COLUMN_NAME_LINK, tutorial.getLink());
        contentValues.put(COLUMN_NAME_IMG, tutorial.getImgRes());
        contentValues.put(COLUMN_NAME_DURATION, tutorial.getDuration());
        contentValues.put(COLUMN_NAME_FAVORITE, String.valueOf(tutorial.isFavorite()));
        contentValues.put(COLUMN_NAME_CERTIFICATE, String.valueOf(tutorial.isCertificated()));
        contentValues.put(COLUMN_NAME_USERNAME, tutorial.getUserName());

        return contentValues;
    }
}
