package com.lri.learningness.model.tutorials;

public class TutorialSection {

    private String title;
    private boolean certificated = false;

    public String getTitle() {
        return title;
    }

    public TutorialSection setTitle(String title) {
        this.title = title;
        return this;
    }

    public boolean isCertificated() {
        return certificated;
    }

    public TutorialSection setCertificated(boolean certificated) {
        this.certificated = certificated;
        return this;
    }
}
