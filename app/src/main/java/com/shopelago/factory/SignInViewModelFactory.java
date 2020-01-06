package com.shopelago.factory;

import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider;
import android.support.annotation.NonNull;

import com.shopelago.repositories.SignInRepository;
import com.shopelago.viewmodels.SignInViewModel;

import javax.inject.Inject;

public class SignInViewModelFactory implements ViewModelProvider.Factory {

    private SignInRepository repository;

    @Inject
    public SignInViewModelFactory(SignInRepository repository) {
        this.repository = repository;
    }


    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        if (modelClass.isAssignableFrom(SignInViewModel.class)) {
            return (T) new SignInViewModel(repository);
        }
        throw new IllegalArgumentException("Unknown class name");
    }
}