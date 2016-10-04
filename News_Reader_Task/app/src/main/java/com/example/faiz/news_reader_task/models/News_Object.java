package com.example.faiz.news_reader_task.models;

public class News_Object {

    private String heading;
    private String paragraph;

    public News_Object(String heading, String paragraph) {
        this.heading = heading;
        this.paragraph = paragraph;
    }

    public News_Object(){

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
