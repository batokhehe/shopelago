package com.shopelago.models;

public class Banner {
    private String Name;
    private String Image;

    public Banner(String name, String image) {
        Name = name;
        Image = image;
    }

    public String getName(){
        return Name;
    }

    public void setName(String Name){
        this.Name = Name;
    }

    public String getImage(){
        return Image;
    }

    public void setImage(String Image){
        this.Image = Image;
    }
}
