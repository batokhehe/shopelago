package com.shopelago.models.requests.login;

import javax.inject.Inject;

public class VerifyLogin {
    private String username;
    private String password;

    public VerifyLogin(String username, String password) {
        this.username = username;
        this.password = password;
    }
}
