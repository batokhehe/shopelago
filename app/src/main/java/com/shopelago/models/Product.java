package com.shopelago.models;

public class Product {

    private String Name;
    private String Image;
    private String Price;
    private Shop shop;
    private double Rating;

    public Product(String name, String price, String image) {
        Name = name;
        Image = image;
        Price = price;
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

    public String getPrice(){
        return "Rp. " + Price;
    }

    public void setPrice(String Price){
        this.Price = Price;
    }

    public Shop getShop() {
        return shop;
    }

    public void setShop(Shop shop) {
        this.shop = shop;
    }

    public double getRating() {
        return Rating;
    }

    public void setRating(double rating) {
        Rating = rating;
    }
}

