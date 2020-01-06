package com.shopelago.models;

public class Shop {
    private String Name;
    private boolean IsTrustedSeller;
    private String Location;
    private String Address;

    public Shop(String name, boolean isTrustedSeller){
        Name = name;
        IsTrustedSeller = isTrustedSeller;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public boolean isTrustedSeller() {
        return IsTrustedSeller;
    }

    public void setTrustedSeller(boolean trustedSeller) {
        IsTrustedSeller = trustedSeller;
    }

    public String getLocation() {
        return Location;
    }

    public void setLocation(String location) {
        Location = location;
    }

    public String getAddress() {
        return Address;
    }

    public void setAddress(String address) {
        Address = address;
    }
}
