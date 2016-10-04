package com.example.faiz.sql_todolist;

public class Habits {
    private String title;
    private String discription;

   // private String check;
    private int id;

    public Habits(String title, String discription, int id) {
        this.title = title;
        this.discription = discription;
       // this.check = check;
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

//    public String getCheck() {
//        return check;
//    }
//
//    public void setCheck(String check) {
//        this.check = check;
//    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}