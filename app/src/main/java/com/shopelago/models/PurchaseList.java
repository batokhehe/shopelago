package com.shopelago.models;

public class PurchaseList {
    
    private String Status;
    private String Date;
    private String InvNumber;
    private String Image;
    private String Name;
    private String Count;
    private String Price;

    public PurchaseList(String status, String date, String invNumber, String image, String name, String count, String price) {
        Status = status;
        Date = date;
        InvNumber = invNumber;
        Image = image;
        Name = name;
        Count = count;
        Price = price;
    }

    public String getStatus(){
        return Status;
    }

    public void setStatus(String Status){
        this.Status = Status;
    }

    public String getInvNumber(){
        return InvNumber;
    }

    public void setInvNumber(String InvNumber){
        this.InvNumber = InvNumber;
    }

    public String getDate(){
        return Date;
    }

    public void setDate(String Date){
        this.Date = Date;
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

    public String getCount(){
        return Count;
    }

    public void setCount(String Count){
        this.Count = Count;
    }

    public String getPrice(){
        return "Rp. " + Price;
    }

    public void setPrice(String Price){
        this.Price = Price;
    }
    
}

