package com.shopelago.models;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

@Entity(tableName = "product_featured")
public class ProductFeatured {

    @PrimaryKey(autoGenerate = true)
    private int id;
    private int ItemId;
    private String Name;
    private String Image;
    private String Price;
    public static final String TableName = "product_featured";

    public ProductFeatured(int itemId, String name, String price, String image) {
        ItemId = itemId;
        Name = name;
        Image = image;
        Price = price;
    }

    public ProductFeatured() {}

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getItemId() {
        return ItemId;
    }

    public void setItemId(int itemId) {
        ItemId = itemId;
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
}

