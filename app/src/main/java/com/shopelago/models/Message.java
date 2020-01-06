package com.shopelago.models;

public class Message {

    private String Name;
    private String Image;
    private String Message;
    private String Date;

    public Message(String name, String image, String message, String date){
        Name = name;
        Image = image;
        Message = message;
        Date = date;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getImage() {
        return Image;
    }

    public void setImage(String image) {
        Image = image;
    }

    public String getMessage() {
        return Message;
    }

    public void setMessage(String message) {
        Message = message;
    }

    public String getDate() {
        return Date;
    }

    public void setDate(String date) {
        Date = date;
    }
}
