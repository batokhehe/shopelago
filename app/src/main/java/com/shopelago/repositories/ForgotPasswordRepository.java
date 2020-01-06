package com.shopelago.repositories;

import com.google.gson.JsonElement;
import com.shopelago.config.ApiInterface;
import com.shopelago.models.requests.forgot_password.ResetPassword;

import io.reactivex.Observable;

public class ForgotPasswordRepository {

    private ApiInterface apiInterface;

    public ForgotPasswordRepository(ApiInterface apiCallInterface) {
        this.apiInterface = apiCallInterface;
    }


    public Observable<JsonElement> executeSendEmailConfirmation(ResetPassword resetPassword) {
        return apiInterface.SendEmailConfirmation(resetPassword);
    }

    public Observable<JsonElement> executeNewPassword(ResetPassword resetPassword) {
        return apiInterface.NewPassword(resetPassword);
    }

}
