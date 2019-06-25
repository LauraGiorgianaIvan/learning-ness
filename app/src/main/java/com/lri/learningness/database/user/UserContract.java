package com.lri.learningness.database.user;

import android.provider.BaseColumns;

import com.lri.learningness.model.user.User;

final class UserContract {

    private UserContract() {//no-op
    }


    static final String CREATE_TABLE_USERS =
            "CREATE TABLE " + UserEntry.TABLE_NAME + " (" +
                    UserEntry._ID + " INTEGER PRIMARY KEY," +
                    UserEntry.COLUMN_NAME_USERNAME + " TEXT," +
                    UserEntry.COLUMN_NAME_PASSWORD + " TEXT," +
                    UserEntry.COLUMN_NAME_DATE + " TEXT," +
                    UserEntry.COLUMN_NAME_USER_TYPE + " TEXT)";

    static final String DELETE_TABLE_USERS =
            "DROP TABLE IF EXISTS " + UserEntry.TABLE_NAME;

    static class UserEntry implements BaseColumns {
        static final String TABLE_NAME = "users";
        static final String COLUMN_NAME_USERNAME = "username";
        static final String COLUMN_NAME_PASSWORD = "password";
        static final String COLUMN_NAME_USER_TYPE = "user_type";
        static final String COLUMN_NAME_DATE = "user_date";
    }

    interface UserQuery {
        void insertUser(User user);

        void updateUser(User user);

        void deleteUser(User user);

        User getUserByUsername(String username);

        User getUser();

        User getUserByUsernameAndPassword(String username, String password);

        void clear();

        void close();
    }
}
