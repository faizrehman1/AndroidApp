package com.example.faiz.tablayout.models;

public class News_Objects {


    private String heading;
    private String paragraph;

    public News_Objects(String heading, String paragraph) {
        this.heading = heading;
        this.paragraph = paragraph;
    }

    public News_Objects() {
    }


    public String getHeading() {
        return heading;
    }

    public void setHeading(String heading) {
        this.heading = heading;
    }

    public String getParagraph() {
        return paragraph;
    }

    public void setParagraph(String paragraph) {
        this.paragraph = paragraph;
    }


}
