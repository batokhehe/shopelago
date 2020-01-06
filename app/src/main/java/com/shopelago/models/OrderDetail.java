package com.shopelago.models;

public class OrderDetail {

    private String Name;
    private String Image;
    private String Price;
    private String Qty;
    private String Weight;
    private String Uom;

    public OrderDetail(String name, String price, String image, String qty, String weight, String uom) {
        Name = name;
        Image = image;
        Price = price;
        Qty = qty;
        Weight = weight;
        Uom = uom;
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

    public String getQty(){
        return Qty;
    }

    public void setQty(String Qty){
        this.Qty = Qty;
    }

    public String getWeight(){
        return Weight;
    }

    public void setWeight(String Weight){
        this.Weight = Weight;
    }

    public String getUom(){
        return Uom;
    }

    public void setUom(String Uom){
        this.Uom = Uom;
    }

    public String getPrice(){
        return "Rp. " + Price;
    }

    public void setPrice(String Price){
        this.Price = Price;
    }

    public String getDesc(){
        return String.format("%s Barang (%s %s)", Qty, Weight, Uom);
    }
    
}

