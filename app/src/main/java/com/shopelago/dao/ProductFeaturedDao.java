package com.shopelago.dao;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import com.shopelago.models.ProductFeatured;

import java.util.List;

import static com.shopelago.models.ProductFeatured.TableName;

@Dao
public interface ProductFeaturedDao {
    @Insert
    void Insert(ProductFeatured productFeatured);

    @Update
    void Update(ProductFeatured productFeatured);

    @Delete
    void Delete(ProductFeatured productFeatured);

    @Query("DELETE FROM " + TableName)
    void DeleteAll();

    @Query("SELECT * FROM " + TableName + " ORDER BY id ASC")
    LiveData<List<ProductFeatured>> GetAll();
}
