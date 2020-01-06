package com.shopelago.factory;

import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider;
import android.support.annotation.NonNull;

import com.shopelago.repositories.AccountRepository;
import com.shopelago.repositories.SignInRepository;
import com.shopelago.viewmodels.AccountViewModel;
import com.shopelago.viewmodels.SignInViewModel;

import javax.inject.Inject;

public class AccountViewModelFactory implements ViewModelProvider.Factory {

    private AccountRepository repository;

    @Inject
    public AccountViewModelFactory(AccountRepository repository) {
        this.repository = repository;
    }


    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        if (modelClass.isAssignableFrom(AccountViewModel.class)) {
            return (T) new AccountViewModel(repository);
        }
        throw new IllegalArgumentException("Unknown class name");
    }
}