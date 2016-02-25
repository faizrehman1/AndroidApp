package com.example.faiz.todolistfire;


public class Message {
    private String name;
    private String discription;
    private String date;
    private boolean check;

    public boolean isCheck() {
        return check;
    }

    public void setCheck(boolean check) {
        this.check = check;
    }

    public Message(String name, String discription, String date,boolean check ) {
        this.name = name;
        this.discription = discription;
        this.date = date;
        this.check=check;
    }

    public Message() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDiscription() {
        return discription;
    }

    public void setDiscription(String discription) {
        this.discription = discription;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

}
