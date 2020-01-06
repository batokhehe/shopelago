package com.shopelago.factory;

import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider;
import android.support.annotation.NonNull;


import com.shopelago.repositories.ForgotPasswordRepository;
import com.shopelago.viewmodels.ForgotPasswordViewModel;

import javax.inject.Inject;

public class ForgotPasswordViewModelFactory implements ViewModelProvider.Factory {

    private ForgotPasswordRepository repository;

    @Inject
    public ForgotPasswordViewModelFactory(ForgotPasswordRepository repository) {
        this.repository = repository;
    }


    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        if (modelClass.isAssignableFrom(ForgotPasswordViewModel.class)) {
            return (T) new ForgotPasswordViewModel(repository);
        }
        throw new IllegalArgumentException("Unknown class name");
    }
}