package com.shopelago.di;

import android.arch.lifecycle.ViewModelProvider;
import android.content.Context;

import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import com.readystatesoftware.chuck.ChuckInterceptor;
import com.shopelago.config.ApiInterface;
import com.shopelago.config.Config;
import com.shopelago.factory.AccountViewModelFactory;
import com.shopelago.factory.ForgotPasswordViewModelFactory;
import com.shopelago.factory.SignInViewModelFactory;
import com.shopelago.factory.SignUpViewModelFactory;
import com.shopelago.models.Response;
import com.shopelago.models.requests.registration.CreateNewUser;
import com.shopelago.repositories.AccountRepository;
import com.shopelago.repositories.ForgotPasswordRepository;
import com.shopelago.repositories.SignInRepository;
import com.shopelago.repositories.SignUpRepository;

import java.util.concurrent.TimeUnit;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

@Module
public class UtilsModule {
    Context context;

    public UtilsModule(Context context) {
        this.context = context;
    }

    @Provides
    @Singleton
    Gson provideGson() {
        GsonBuilder builder =
                new GsonBuilder().setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES);
        return builder.setLenient().create();
    }

    @Provides
    @Singleton
    Retrofit provideRetrofit(Gson gson, OkHttpClient okHttpClient) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Config.BASE_URL)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .client(okHttpClient)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();

        return retrofit;
    }

    @Provides
    @Singleton
    ApiInterface getApiCallInterface(Retrofit retrofit) {
        return retrofit.create(ApiInterface.class);
    }

    @Provides
    @Singleton
    OkHttpClient getRequestHeader() {

        OkHttpClient.Builder httpClient = new OkHttpClient.Builder();

        httpClient.addInterceptor(chain -> {
            Request original = chain.request();
            Request request = original.newBuilder().build();
            return chain.proceed(request);
        })
                .addInterceptor(new ChuckInterceptor(context))
                .connectTimeout(100, TimeUnit.SECONDS)
                .writeTimeout(100, TimeUnit.SECONDS)
                .readTimeout(300, TimeUnit.SECONDS);

        OkHttpClient client = httpClient.build();
        return client;
    }

    @Provides
    @Singleton
    SignUpRepository getSignUpRepository(ApiInterface apiInterface) {
        return new SignUpRepository(apiInterface);
    }

    @Provides
    @Singleton
    ViewModelProvider.Factory getSignUpViewModelFactory(SignUpRepository repository) {
        return new SignUpViewModelFactory(repository);
    }

    @Provides
    @Singleton
    SignInRepository getSignInRepository(ApiInterface apiInterface) {
        return new SignInRepository(apiInterface);
    }

    @Provides
    @Singleton
    ViewModelProvider.Factory getSignInViewModelFactory(SignInRepository repository) {
        return new SignInViewModelFactory(repository);
    }

    @Provides
    @Singleton
    AccountRepository getAccountRepository(ApiInterface apiInterface) {
        return new AccountRepository(apiInterface);
    }

    @Provides
    @Singleton
    ViewModelProvider.Factory getAccountViewModelFactory(AccountRepository repository) {
        return new AccountViewModelFactory(repository);
    }

    @Provides
    @Singleton
    ForgotPasswordRepository getForgotPasswordRepository(ApiInterface apiInterface) {
        return new ForgotPasswordRepository(apiInterface);
    }

    @Provides
    @Singleton
    ViewModelProvider.Factory getForgotPasswordViewModelFactory(ForgotPasswordRepository repository) {
        return new ForgotPasswordViewModelFactory(repository);
    }
}