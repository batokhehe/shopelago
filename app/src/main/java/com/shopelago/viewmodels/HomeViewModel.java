package com.shopelago.viewmodels;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.support.annotation.NonNull;

import com.shopelago.models.ProductFeatured;
import com.shopelago.repositories.ProductFeaturedRepository;

import java.util.List;

public class HomeViewModel extends AndroidViewModel {
    private ProductFeaturedRepository Repository;
    private LiveData<List<ProductFeatured>> All;

    // TODO: Implement the ViewModel
    public HomeViewModel(@NonNull Application application) {
        super(application);
        Repository = new ProductFeaturedRepository(application);
        All = Repository.GetAll();
    }

    public void Insert(ProductFeatured model){
        Repository.Insert(model);
    }

    public void Update(ProductFeatured model){
        Repository.Update(model);
    }

    public void Delete(ProductFeatured model){
        Repository.Delete(model);
    }

    public void DeleteAll(){
        Repository.DeleteAll();
    }

    public LiveData<List<ProductFeatured>> GetAll(){
        return All;
    }
}
