package com.shopelago.models.requests.registration;

public class CompleteRegistration {
    private String full_name;
    private String password;
    private String hashed_username;

    public CompleteRegistration(String full_name, String password, String hashed_username) {
        this.full_name = full_name;
        this.password = password;
        this.hashed_username = hashed_username;
    }
}
