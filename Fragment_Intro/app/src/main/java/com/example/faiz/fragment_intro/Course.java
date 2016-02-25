package com.example.faiz.fragment_intro;

public class Course {
    private String title;
    private String discription;
    private int image;

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public Course(String title, String discription, int image) {
        this.title = title;
        this.discription = discription;
        this.image = image;
    }


    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDiscription() {
        return discription;
    }

    public void setDiscription(String discription) {
        this.discription = discription;
    }



    @Override
    public String toString() {
        return title;
    }
}
