package com.lri.learningness.model.tutorials;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Tutorial implements Serializable {

    private String id;

    @SerializedName("name")
    private String name;

    @SerializedName("link")
    private String link;

    @SerializedName("img_res")
    private String imgRes;

    @SerializedName("duration")
    private String duration;

    private String userName;

    private boolean favorite = false;

    private boolean certificated = false;

    private TutorialSection section;

    public String getId() {
        return id;
    }

    public Tutorial setId(String id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public Tutorial setName(String name) {
        this.name = name;
        return this;
    }

    public String getLink() {
        return link;
    }

    public Tutorial setLink(String link) {
        this.link = link;
        return this;
    }

    public String getImgRes() {
        return imgRes;
    }

    public Tutorial setImgRes(String imgRes) {
        this.imgRes = imgRes;
        return this;
    }

    public String getDuration() {
        return duration;
    }

    public Tutorial setDuration(String duration) {
        this.duration = duration;
        return this;
    }

    public boolean isFavorite() {
        return favorite;
    }

    public Tutorial setFavorite(boolean favorite) {
        this.favorite = favorite;
        return this;
    }

    public boolean isCertificated() {
        return certificated;
    }

    public void setCertificated(boolean certificated) {
        this.certificated = certificated;
    }

    public TutorialSection getSection() {
        return section;
    }

    public Tutorial setSection(TutorialSection section) {
        this.section = section;
        return this;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
}
