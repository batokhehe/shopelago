package com.shopelago.repositories;

import com.google.gson.JsonElement;
import com.shopelago.config.ApiInterface;
import com.shopelago.models.requests.apply_premium.ApplyPremium;
import com.shopelago.models.requests.login.VerifyLogin;

import io.reactivex.Observable;

public class AccountRepository {

    private ApiInterface apiInterface;

    public AccountRepository(ApiInterface apiCallInterface) {
        this.apiInterface = apiCallInterface;
    }

    /*
     * method to call signup api
     * */
    public Observable<JsonElement> executeApplyPremium(String token) {
        return apiInterface.ApplyPremium(token);
    }

}
