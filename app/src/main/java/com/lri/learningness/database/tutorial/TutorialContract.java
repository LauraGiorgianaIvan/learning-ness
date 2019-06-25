package com.lri.learningness.database.tutorial;

import android.provider.BaseColumns;

import com.lri.learningness.model.tutorials.Tutorial;

import java.util.List;

public final class TutorialContract {

    private TutorialContract() {//no-op
    }


    public static final String CREATE_TABLE_TUTORIAL =
            "CREATE TABLE " + TutorialEntry.TABLE_NAME + " (" +
                    TutorialEntry._ID + " INTEGER PRIMARY KEY," +
                    TutorialEntry.COLUMN_NAME_TUTORIAL_NAME + " TEXT," +
                    TutorialEntry.COLUMN_NAME_LINK + " TEXT," +
                    TutorialEntry.COLUMN_NAME_IMG + " TEXT," +
                    TutorialEntry.COLUMN_NAME_DURATION + " TEXT," +
                    TutorialEntry.COLUMN_NAME_FAVORITE + " TEXT," +
                    TutorialEntry.COLUMN_NAME_CERTIFICATE + " TEXT," +
                    TutorialEntry.COLUMN_NAME_USERNAME + " TEXT)";

    public static final String DELETE_TABLE_TUTORIAL =
            "DROP TABLE IF EXISTS " + TutorialEntry.TABLE_NAME;

    public static class TutorialEntry implements BaseColumns {
        public static final String TABLE_NAME = "tutorial";
        public static final String COLUMN_NAME_TUTORIAL_NAME = "tutorial_name";
        public static final String COLUMN_NAME_LINK = "link";
        public static final String COLUMN_NAME_IMG = "img";
        public static final String COLUMN_NAME_DURATION = "duration";
        public static final String COLUMN_NAME_FAVORITE = "favorite";
        public static final String COLUMN_NAME_CERTIFICATE = "certificate";
        public static final String COLUMN_NAME_USERNAME = "username";
    }

    public interface TutorialQuery {

        List<Tutorial> getAllTutorials();

        List<Tutorial> getUserTutorials(String username);

        void insertTutorial(Tutorial tutorial);

        void deleteTutorial(Tutorial tutorial);

        void updateTutorial(Tutorial tutorial);

        Tutorial getTutorialById(String id);

        Tutorial getTutorial();

        Tutorial getTutorialByLink(String link);

        Tutorial getUserTutorialByLink(String username, String link);

        void close();
    }
}
