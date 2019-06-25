package com.lri.learningness.model.tutorials;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

public class TutorialList implements Serializable {

    @SerializedName("tutorial")
    private List<TutorialCategory> tutorials;

    public List<TutorialCategory> getTutorials() {
        return tutorials;
    }

    public void setTutorials(List<TutorialCategory> tutorials) {
        this.tutorials = tutorials;
    }
}
