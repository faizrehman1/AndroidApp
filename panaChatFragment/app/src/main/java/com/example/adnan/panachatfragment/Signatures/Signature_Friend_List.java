package com.example.adnan.panachatfragment.Signatures;

/**
 * Created by Adnan on 1/6/2016.
 */
public class Signature_Friend_List {

    String name;
    String senderId;
    String picUrl;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSenderId() {
        return senderId;
    }

    public void setSenderId(String senderId) {
        this.senderId = senderId;
    }

    public String getPicUrl() {
        return picUrl;
    }

    public void setPicUrl(String picUrl) {
        this.picUrl = picUrl;
    }

    public Signature_Friend_List() {

    }

    public Signature_Friend_List(String name, String senderId, String picUrl) {
        this.name = name;
        this.senderId = senderId;
        this.picUrl = picUrl;

    }
}
