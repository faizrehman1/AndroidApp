package com.example.faiz.numtodo;

public class TodoItems {

    private String title;
    private String discription;
    private boolean check;

    public boolean isCheck() {
        return check;
    }

    public void setCheck(boolean check) {
        this.check = check;
    }

    public TodoItems(String title, String discription, boolean check) {
        this.title = title;
        this.discription = discription;
        this.check = check;
    }

    public TodoItems() {

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
}
