package com.shopelago.repositories;

import android.app.Application;
import android.arch.lifecycle.LiveData;
import android.os.AsyncTask;

import com.shopelago.dao.ProductFeaturedDao;
import com.shopelago.databases.ProductFeaturedDatabase;
import com.shopelago.models.ProductFeatured;

import java.util.List;

public class ProductFeaturedRepository {
    private ProductFeaturedDao Dao;
    private LiveData<List<ProductFeatured>> All;

    public ProductFeaturedRepository(Application application){
        ProductFeaturedDatabase database = ProductFeaturedDatabase.getInstance(application);
        Dao = database.dao();
        All = Dao.GetAll();
    }

    public void Insert(ProductFeatured model){
        new InsertAsyncTask(Dao).execute(model);
    }

    public void Update(ProductFeatured model){
        new UpdateAsyncTask(Dao).execute(model);
    }

    public void Delete(ProductFeatured model){
        new DeleteAsyncTask(Dao).execute(model);
    }

    public void DeleteAll(){
        new DeleteAsyncTask(Dao).execute();
    }

    public LiveData<List<ProductFeatured>> GetAll(){
        return All;
    }

    private static class InsertAsyncTask extends AsyncTask<ProductFeatured, Void, Void> {
        private ProductFeaturedDao dao;

        private InsertAsyncTask(ProductFeaturedDao dao){
            this.dao = dao;
        }

        @Override
        protected Void doInBackground(ProductFeatured... datas) {
            dao.Insert(datas[0]);
            return null;
        }
    }

    private static class UpdateAsyncTask extends AsyncTask<ProductFeatured, Void, Void> {
        private ProductFeaturedDao dao;

        private UpdateAsyncTask(ProductFeaturedDao dao){
            this.dao = dao;
        }

        @Override
        protected Void doInBackground(ProductFeatured... datas) {
            dao.Update(datas[0]);
            return null;
        }
    }

    private static class DeleteAsyncTask extends AsyncTask<ProductFeatured, Void, Void> {
        private ProductFeaturedDao dao;

        private DeleteAsyncTask(ProductFeaturedDao dao){
            this.dao = dao;
        }

        @Override
        protected Void doInBackground(ProductFeatured... datas) {
            dao.Delete(datas[0]);
            return null;
        }
    }

    private static class DeleteAllAsyncTask extends AsyncTask<Void, Void, Void> {
        private ProductFeaturedDao dao;

        private DeleteAllAsyncTask(ProductFeaturedDao dao){
            this.dao = dao;
        }

        @Override
        protected Void doInBackground(Void... datas) {
            dao.DeleteAll();
            return null;
        }
    }
}
