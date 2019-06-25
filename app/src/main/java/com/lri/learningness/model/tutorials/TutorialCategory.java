package com.lri.learningness.model.tutorials;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

public class TutorialCategory implements Serializable {

    @SerializedName("category")
    private String category;

    @SerializedName("category_img")
    private String categoryImg;

    @SerializedName("tutorials")
    private List<Tutorial> tutorials;

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getCategoryImg() {
        return categoryImg;
    }

    public void setCategoryImg(String categoryImg) {
        this.categoryImg = categoryImg;
    }

    public List<Tutorial> getTutorials() {
        return tutorials;
    }

    public void setTutorials(List<Tutorial> tutorials) {
        this.tutorials = tutorials;
    }
}
