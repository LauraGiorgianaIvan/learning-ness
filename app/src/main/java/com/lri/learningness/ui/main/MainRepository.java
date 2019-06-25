package com.lri.learningness.ui.main;

import android.content.Context;

import com.lri.learningness.database.tutorial.TutorialDatabase;
import com.lri.learningness.database.user.UserDatabase;
import com.lri.learningness.tools.prefs.SharedPrefs;

public class MainRepository {
    private UserDatabase userDatabase;
    private TutorialDatabase tutorialDatabase;
    private SharedPrefs prefs;

    public MainRepository(Context context) {
        userDatabase = new UserDatabase(context);
        tutorialDatabase = new TutorialDatabase(context);
        prefs = new SharedPrefs();
    }

    public UserDatabase userDb() {
        return userDatabase;
    }

    public TutorialDatabase tutorialDb() {
        return tutorialDatabase;
    }

    public SharedPrefs prefs() {
        return prefs;
    }

    public void closeDb() {
        if (userDatabase != null) {
            userDatabase.close();
        }

        if (tutorialDatabase != null) {
            tutorialDatabase.close();
        }
    }

}
