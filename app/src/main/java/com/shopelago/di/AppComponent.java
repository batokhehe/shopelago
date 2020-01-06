package com.shopelago.di;

import com.shopelago.view.activities.ForgotPasswordActivity;
import com.shopelago.view.activities.SignInActivity;
import com.shopelago.view.activities.SignUpActivity;
import com.shopelago.view.fragments.AccountFragment;

import javax.inject.Singleton;

import dagger.Component;

@Component(modules = {AppModule.class, UtilsModule.class})
@Singleton
public interface AppComponent {
    void doSignUpInjection(SignUpActivity activity);
    void doSignInInjection(SignInActivity activity);
    void doForgotPasswordInjection(ForgotPasswordActivity activity);
    void doAccountInjection(AccountFragment fragment);
}
