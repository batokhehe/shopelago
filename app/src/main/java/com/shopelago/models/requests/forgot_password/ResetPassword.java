package com.shopelago.models.requests.forgot_password;

import javax.inject.Inject;

public class ResetPassword {
    private String email;
    private String otp;
    private String password;

    public String getEmail() {
        return email;
    }

    @Inject
    public void setEmail(String email) {
        this.email = email;
    }

    public String getOtp() {
        return otp;
    }

    @Inject
    public void setOtp(String otp) {
        this.otp = otp;
    }

    public String getPassword() {
        return password;
    }

    @Inject
    public void setPassword(String password) {
        this.password = password;
    }
}
