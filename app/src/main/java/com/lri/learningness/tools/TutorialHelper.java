package com.lri.learningness.tools;

import com.lri.learningness.R;
import com.lri.learningness.model.tutorials.Tutorial;
import com.lri.learningness.model.tutorials.TutorialCategory;
import com.lri.learningness.model.tutorials.TutorialList;

import java.util.ArrayList;
import java.util.List;

public class TutorialHelper {
    private static TutorialHelper INSTANCE;

    private TutorialHelper() {
    }

    public static TutorialHelper get() {
        if (INSTANCE == null) {
            INSTANCE = new TutorialHelper();
        }
        return INSTANCE;
    }

    public List<TutorialCategory> getTutorialCategory() {
        JsonFileParser<TutorialList> parser = new JsonFileParser<>();
        String tutorialFile = parser.getJsonFromLocalFile(R.raw.tutorials);
        TutorialList tutorialList = parser.constructUsingGson(TutorialList.class, tutorialFile);
        return tutorialList.getTutorials();
    }

    public List<Tutorial> getAllTutorials() {
        List<Tutorial> tutorials = new ArrayList<>();
        List<TutorialCategory> tutorialCategory = getTutorialCategory();
        for (TutorialCategory tc : tutorialCategory) {
            tutorials.addAll(tc.getTutorials());
        }

        return tutorials;
    }
}
