package com.shopelago.models.requests.registration;

import javax.inject.Inject;

public class CreateNewUser {
    private String email;
    private String password;
    private String fullname;
    private String kode_referal;

    @Inject
    public CreateNewUser(String email, String password, String fullname, String kode_referal) {
        this.email = email;
        this.password = password;
        this.fullname = fullname;
        this.kode_referal = kode_referal;
    }
}
