package com.app.productapp.db

import com.app.productapp.db.entity.Product

class DatabaseHelperImpl(private val appDatabase: AppDatabase) : DatabaseHelper {

    override suspend fun getProducts(): List<Product> = appDatabase.productDao().getAll()

    override suspend fun insertAll(products: List<Product>) =
        appDatabase.productDao().insertAll(products)

    override suspend fun deleteAll() = appDatabase.productDao().deleteAll()
}