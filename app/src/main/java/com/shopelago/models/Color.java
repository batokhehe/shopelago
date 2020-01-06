package com.shopelago.models;

public class Color {

    private String Name;
    private String Image;

    public Color(String name, String image) {
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

