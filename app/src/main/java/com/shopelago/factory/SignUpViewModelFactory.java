package com.shopelago.factory;

import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider;
import android.support.annotation.NonNull;

import com.shopelago.repositories.SignUpRepository;
import com.shopelago.viewmodels.SignUpViewModel;

import javax.inject.Inject;

public class SignUpViewModelFactory implements ViewModelProvider.Factory {

    private SignUpRepository repository;

    @Inject
    public SignUpViewModelFactory(SignUpRepository repository) {
        this.repository = repository;
    }


    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        if (modelClass.isAssignableFrom(SignUpViewModel.class)) {
            return (T) new SignUpViewModel(repository);
        }
        throw new IllegalArgumentException("Unknown class name");
    }
}