package com.shopelago.viewmodels;

import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;

import com.shopelago.config.ApiResponse;
import com.shopelago.models.requests.apply_premium.ApplyPremium;
import com.shopelago.models.requests.login.VerifyLogin;
import com.shopelago.repositories.AccountRepository;
import com.shopelago.repositories.SignInRepository;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;

public class AccountViewModel extends ViewModel {
    private AccountRepository repository;
    private final CompositeDisposable disposables = new CompositeDisposable();
    private final MutableLiveData<ApiResponse> responseLiveData = new MutableLiveData<>();


    public AccountViewModel(AccountRepository repository) {
        this.repository = repository;
    }

    public MutableLiveData<ApiResponse> response() {
        return responseLiveData;
    }

    /*
     * method to call normal login api with $(mobileNumber + password)
     * */
    public void hitApplyPremium(String token) {
        disposables.add(repository.executeApplyPremium(token)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe((d) -> responseLiveData.setValue(ApiResponse.loading()))
                .subscribe(
                        result -> responseLiveData.setValue(ApiResponse.success(result)),
                        throwable -> responseLiveData.setValue(ApiResponse.error(throwable))
                ));
    }

    @Override
    protected void onCleared() {
        disposables.clear();
    }
}
