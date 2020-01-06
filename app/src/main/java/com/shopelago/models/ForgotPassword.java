package com.shopelago.models;

public class ForgotPassword {

    private String OtpCode;
    private String Email;

    public String getOtpCode() {
        return OtpCode;
    }

    public void setOtpCode(String otpCode) {
        OtpCode = otpCode;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }
}
