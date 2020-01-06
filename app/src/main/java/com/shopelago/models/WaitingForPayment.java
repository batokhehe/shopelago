package com.shopelago.models;

public class WaitingForPayment {

    private String Date;
    private String TotalPayment;
    private String PaymentMethod;
    private String PaymentAccount;


    public WaitingForPayment(String date, String totalPayment, String paymentMethod,
                             String paymentAccount) {
        Date = date;
        TotalPayment = totalPayment;
        PaymentMethod = paymentMethod;
        PaymentAccount = paymentAccount;
    }

    public String getDate(){
        return Date;
    }

    public void setDate(String Date){
        this.Date = Date;
    }

    public String getTotalPayment(){
        return "Rp. " + TotalPayment;
    }

    public void setTotalPayment(String TotalPayment){
        this.TotalPayment = TotalPayment;
    }

    public String getPaymentMethod(){
        return PaymentMethod;
    }

    public void setPaymentMethod(String PaymentMethod){
        this.PaymentMethod = PaymentMethod;
    }

    public String getPaymentAccount(){
        return PaymentAccount;
    }

    public void setPaymentAccount(String PaymentAccount){
        this.PaymentAccount = PaymentAccount;
    }
    
}

