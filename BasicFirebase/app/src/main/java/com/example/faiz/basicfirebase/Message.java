package com.example.faiz.basicfirebase;


public class Message {
    public Message(String name1, String message1) {
        this.name1 = name1;
        this.message1 = message1;
    }

    public Message() {
    }

    private String name1;
    private String message1;

    public String getName1() {
        return name1;
    }

    public void setName1(String name1) {
        this.name1 = name1;
    }

    public String getMessage1() {
        return message1;
    }

    public void setMessage1(String message1) {
        this.message1 = message1;
    }
}
