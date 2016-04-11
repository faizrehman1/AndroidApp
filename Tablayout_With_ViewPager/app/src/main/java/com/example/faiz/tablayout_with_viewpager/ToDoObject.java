package com.example.faiz.tablayout_with_viewpager;

public class ToDoObject {
   private String title;
   private  String discription;
   private  int id;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public ToDoObject(String title, String discription,int id) {
        this.title = title;
        this.discription = discription;
        this.id=id;
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
