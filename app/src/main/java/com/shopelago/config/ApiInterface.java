package com.shopelago.config;

import com.google.gson.JsonElement;
import com.shopelago.models.requests.login.VerifyLogin;
import com.shopelago.models.requests.registration.CompleteRegistration;
import com.shopelago.models.requests.registration.CreateNewUser;
import com.shopelago.models.requests.forgot_password.ResetPassword;

import io.reactivex.Observable;
import retrofit2.http.Body;
import retrofit2.http.Header;
import retrofit2.http.POST;

public interface ApiInterface {
    //REGISTRATION
    @POST("user/create-new-user")
    Observable<JsonElement> CreateNewUser(@Body CreateNewUser body);

    @POST("user/complete-registration")
    Observable<JsonElement> CompleteRegistration(@Body CompleteRegistration body);

    //LOGIN
    @POST("user/verify-login")
    Observable<JsonElement> VerifyLogin(@Body VerifyLogin body);


    //FORGOT PASSWORD
    @POST("user/send-email-otp")
    Observable<JsonElement> SendEmailConfirmation(@Body ResetPassword body);

    @POST("user/save-new-password")
    Observable<JsonElement> NewPassword(@Body ResetPassword body);

    //APPLY PREMIUM
    @POST("financial/apply-premium")
    Observable<JsonElement> ApplyPremium(@Header("token") String token);
}
