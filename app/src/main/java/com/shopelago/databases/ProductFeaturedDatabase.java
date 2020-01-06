package com.shopelago.databases;

import android.arch.persistence.db.SupportSQLiteDatabase;
import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;
import android.os.AsyncTask;
import android.support.annotation.NonNull;

import com.shopelago.dao.ProductFeaturedDao;
import com.shopelago.models.ProductFeatured;

import static com.shopelago.config.Config.DBVERSION;

@Database(entities = {ProductFeatured.class}, version = DBVERSION)
public abstract class ProductFeaturedDatabase extends RoomDatabase {

    private static ProductFeaturedDatabase instance;

    public abstract ProductFeaturedDao dao();

    public static synchronized ProductFeaturedDatabase getInstance(Context context){
        if(instance == null){
            instance = Room.databaseBuilder(context.getApplicationContext(),
                    ProductFeaturedDatabase.class, ProductFeatured.TableName)
                    .fallbackToDestructiveMigration()
                    .addCallback(roomCallback)
                    .build();
        }
        return instance;
    }

    private static  RoomDatabase.Callback roomCallback = new RoomDatabase.Callback(){
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);
            new PopulateDbAsyncTask(instance).execute();
        }
    };

    private static class PopulateDbAsyncTask extends AsyncTask<Void, Void, Void>{
        private ProductFeaturedDao dao;

        public PopulateDbAsyncTask(ProductFeaturedDatabase db) {
            this.dao = db.dao();
        }

        @Override
        protected Void doInBackground(Void... voids) {
            dao.Insert(new ProductFeatured(1, "Lorem Ipsum", "11.500", "http://bhovdair.com/projects/shopelago/Products(8).jpg"));
            dao.Insert(new ProductFeatured(2, "Lorem Ipsum", "11.500", "http://bhovdair.com/projects/shopelago/Products(2).jpg"));
            dao.Insert(new ProductFeatured(3, "Lorem Ipsum", "11.500", "http://bhovdair.com/projects/shopelago/Products(3).jpg"));
            dao.Insert(new ProductFeatured(4, "Lorem Ipsum", "11.500", "http://bhovdair.com/projects/shopelago/Products(4).jpg"));
            dao.Insert(new ProductFeatured(5, "Lorem Ipsum", "11.500", "http://bhovdair.com/projects/shopelago/Products(5).jpg"));
            dao.Insert(new ProductFeatured(6, "Lorem Ipsum", "11.500", "http://bhovdair.com/projects/shopelago/Products(6).jpg"));
            dao.Insert(new ProductFeatured(7, "Lorem Ipsum", "11.500", "http://bhovdair.com/projects/shopelago/Products(7).jpg"));
            dao.Insert(new ProductFeatured(8, "Lorem Ipsum", "11.500", "http://bhovdair.com/projects/shopelago/Products(1).jpg"));
            return null;
        }
    }
}
