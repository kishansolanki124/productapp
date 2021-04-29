package com.app.productapp.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.app.productapp.db.dao.ProductDao
import com.app.productapp.db.entity.Product

@Database(
    entities = [Product::class],
    version = 1
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun productDao(): ProductDao
}