package com.example.faiz.sql_todolist;

public class ToDoObjects {
    private String title;
    private String discription;
    private Boolean check;
    private int id;

    public Boolean getCheck() {
        return check;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setCheck(Boolean check) {
        this.check = check;
    }

    public ToDoObjects(String title, String discription, Boolean check, int id) {
        this.title = title;
        this.discription = discription;
        this.check = check;
        this.id = id;
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
