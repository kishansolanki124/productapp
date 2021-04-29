package com.app.productapp.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.app.productapp.db.entity.Product

@Dao
interface ProductDao {
    @Query("SELECT * FROM product ORDER BY cast(id as unsigned) DESC")
    suspend fun getAll(): List<Product>

    @Insert(onConflict = OnConflictStrategy.REPLACE)//if value exist then will be replace, restricting duplicate entries
    suspend fun insertAll(products: List<Product>)

    @Query("DELETE FROM product")
    suspend fun deleteAll()
}