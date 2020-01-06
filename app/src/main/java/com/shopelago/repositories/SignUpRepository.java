package com.shopelago.repositories;

import com.google.gson.JsonElement;
import com.shopelago.config.ApiInterface;
import com.shopelago.models.requests.registration.CreateNewUser;

import io.reactivex.Observable;

public class SignUpRepository {

    private ApiInterface apiInterface;

    public SignUpRepository(ApiInterface apiCallInterface) {
        this.apiInterface = apiCallInterface;
    }

    /*
     * method to call signup api
     * */
    public Observable<JsonElement> executeCreateNewUser(CreateNewUser createNewUser) {
        return apiInterface.CreateNewUser(createNewUser);
    }

}
