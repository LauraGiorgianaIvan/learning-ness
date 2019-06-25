package com.lri.learningness.tools.prefs;

import android.content.Context;
import android.content.SharedPreferences;

import com.google.gson.Gson;
import com.lri.learningness.base.GlobalApp;
import com.lri.learningness.model.user.User;

public class SharedPrefs {
    private static final String TUTORIAL_NESS_PREFERENCES = "TUTORIAL_NESS_PREFERENCES";
    private static final String CURRENT_LOGGED_USER = "CURRENT_LOGGED_USER";

    private SharedPreferences sharedPref;

    public SharedPrefs() {
        sharedPref = GlobalApp.appContext().getSharedPreferences(TUTORIAL_NESS_PREFERENCES, Context.MODE_PRIVATE);

    }

    public void saveLoggedIn(User user) {
        sharedPref.edit().putString(CURRENT_LOGGED_USER, new Gson().toJson(user)).apply();
    }

    public User getCurrentUser() {
        if (sharedPref.contains(CURRENT_LOGGED_USER)) {
            String userString = sharedPref.getString(CURRENT_LOGGED_USER, null);
            return new Gson().fromJson(userString, User.class);
        }
        return null;
    }

    public void clear() {
        sharedPref.edit().remove(CURRENT_LOGGED_USER).apply();
    }
}
