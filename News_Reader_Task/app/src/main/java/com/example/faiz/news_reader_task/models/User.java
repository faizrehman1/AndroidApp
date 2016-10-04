package com.example.faiz.news_reader_task.models;

public class User {

    private String fname;
    private String lname;
    private String id;
    private String password;

    public User(String fname, String lname, String id, String password) {
        this.fname = fname;
        this.lname = lname;
        this.id = id;
        this.password = password;
    }


    public User() {
    }

    public String getFname() {
        return fname;
    }

    public void setFname(String fname) {
        this.fname = fname;
    }

    public String getLname() {
        return lname;
    }

    public void setLname(String lname) {
        this.lname = lname;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
