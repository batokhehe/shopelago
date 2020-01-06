package com.shopelago.repositories;

import com.google.gson.JsonElement;
import com.shopelago.config.ApiInterface;
import com.shopelago.models.requests.login.VerifyLogin;

import io.reactivex.Observable;

public class SignInRepository {

    private ApiInterface apiInterface;

    public SignInRepository(ApiInterface apiCallInterface) {
        this.apiInterface = apiCallInterface;
    }

    /*
     * method to call signup api
     * */
    public Observable<JsonElement> executeVerifyLogin(VerifyLogin verifyLogin) {
        return apiInterface.VerifyLogin(verifyLogin);
    }

}
