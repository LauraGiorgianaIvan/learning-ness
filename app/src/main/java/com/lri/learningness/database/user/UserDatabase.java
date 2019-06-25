package com.lri.learningness.database.user;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.lri.learningness.model.user.User;
import com.lri.learningness.model.user.UserType;

import static android.provider.BaseColumns._ID;
import static com.lri.learningness.database.user.UserContract.UserEntry.*;

public class UserDatabase implements UserContract.UserQuery {

    private UserDbHelper dbHelper;

    public UserDatabase(Context context) {
        dbHelper = new UserDbHelper(context);
    }

    @Override
    public void insertUser(User user) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        db.insert(TABLE_NAME, null, bindUser(user));
    }

    @Override
    public void updateUser(User user) {
        String selection = COLUMN_NAME_USERNAME + " LIKE ?";
        String[] selectionArgs = {user.getUsername()};

        SQLiteDatabase db = dbHelper.getWritableDatabase();
        db.update(TABLE_NAME, bindUser(user), selection, selectionArgs);
    }

    @Override
    public void deleteUser(User user) {
        String selection = _ID + " = ?";
        String[] selectionArgs = {user.getId()};
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        db.delete(TABLE_NAME, selection, selectionArgs);
    }

    @Override
    public User getUserByUsername(String username) {
        SQLiteDatabase db = dbHelper.getReadableDatabase();

        String[] projection = {
                _ID,
                COLUMN_NAME_USERNAME,
                COLUMN_NAME_USER_TYPE,
                COLUMN_NAME_DATE
        };

        String selection = COLUMN_NAME_USERNAME + " = ?";
        String[] selectionArgs = {username};

        Cursor cursor = db.query(
                TABLE_NAME, projection, selection, selectionArgs, null, null, null
        );
        boolean isUserAvailable = cursor.moveToFirst();
        if (isUserAvailable) {
            User user = new User();
            user.setId(cursor.getString(cursor.getColumnIndex(_ID)));
            user.setUsername(cursor.getString(cursor.getColumnIndex(COLUMN_NAME_USERNAME)));
            user.setUserType(getUserType(cursor.getString(cursor.getColumnIndex(COLUMN_NAME_USER_TYPE))));
            user.setDateRegistered(cursor.getString(cursor.getColumnIndex(COLUMN_NAME_DATE)));

            cursor.close();
            return user;
        }
        return null;
    }

    @Override
    public User getUser() {
        SQLiteDatabase db = dbHelper.getReadableDatabase();

        String[] projection = {
                _ID,
                COLUMN_NAME_USERNAME,
                COLUMN_NAME_USER_TYPE,
                COLUMN_NAME_DATE

        };

        Cursor cursor = db.query(
                TABLE_NAME, projection, null, null, null, null, null
        );
        boolean isUserAvailable = cursor.moveToFirst();
        if (isUserAvailable) {
            User user = new User();
            user.setUsername(cursor.getString(cursor.getColumnIndex(COLUMN_NAME_USERNAME)));
            user.setUserType(getUserType(cursor.getString(cursor.getColumnIndex(COLUMN_NAME_USER_TYPE))));
            user.setDateRegistered(cursor.getString(cursor.getColumnIndex(COLUMN_NAME_DATE)));

            cursor.close();
            return user;
        }
        return null;
    }

    @Override
    public User getUserByUsernameAndPassword(String username, String password) {
        SQLiteDatabase db = dbHelper.getReadableDatabase();

        String[] projection = {
                _ID,
                COLUMN_NAME_USERNAME,
                COLUMN_NAME_USER_TYPE,
                COLUMN_NAME_DATE
        };

        String selection = COLUMN_NAME_USERNAME + " = ? AND " + COLUMN_NAME_PASSWORD + " =? ";
        String[] selectionArgs = {username};

        Cursor cursor = db.query(
                TABLE_NAME, projection, selection, selectionArgs, null, null, null
        );
        boolean isUserAvailable = cursor.moveToFirst();
        if (isUserAvailable) {
            User user = new User();
            user.setId(cursor.getString(cursor.getColumnIndex(_ID)));
            user.setUsername(cursor.getString(cursor.getColumnIndex(COLUMN_NAME_USERNAME)));
            user.setUserType(getUserType(cursor.getString(cursor.getColumnIndex(COLUMN_NAME_USER_TYPE))));
            user.setDateRegistered(cursor.getString(cursor.getColumnIndex(COLUMN_NAME_DATE)));

            cursor.close();
            return user;
        }
        return null;
    }

    @Override
    public void clear() {
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        db.execSQL("delete from " + TABLE_NAME);
    }

    @Override
    public void close() {
        if (dbHelper != null) {
            dbHelper.close();
        }
    }

    private UserType getUserType(String type) {
        return type.equalsIgnoreCase("ADMIN") ? UserType.ADMIN : UserType.DEFAULT;
    }

    private ContentValues bindUser(User user) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(COLUMN_NAME_USERNAME, user.getUsername());
        contentValues.put(COLUMN_NAME_PASSWORD, user.getPassword());
        contentValues.put(COLUMN_NAME_USER_TYPE, user.getUserType().name());
        contentValues.put(COLUMN_NAME_DATE, user.getDateRegistered());


        return contentValues;
    }
}
