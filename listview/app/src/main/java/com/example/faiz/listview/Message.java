package com.example.faiz.listview;


public class Message {
    private String name;
    private String message;
   // private int imageRes;
    private boolean radio;
    public  Message(){

    }
    public Message(String message, String name,boolean radio ) {
        this.message = message;
        this.name = name;
      //  this.imageRes = imageRes;
        this.radio=radio;
    }

    public boolean isRadio() {
        return radio;
    }

    public void setRadio(boolean radio) {
        this.radio = radio;
    }

    //  public int getImageRes() {
    //    return imageRes;
  //  }

  //  public void setImageRes(int imageRes) {
    //    this.imageRes = imageRes;
   // }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
