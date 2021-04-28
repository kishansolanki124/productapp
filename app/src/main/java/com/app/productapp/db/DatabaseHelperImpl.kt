package com.app.productapp.db

import com.app.productapp.db.entity.Product

class DatabaseHelperImpl(private val appDatabase: AppDatabase) : DatabaseHelper {

    override suspend fun getUsers(): List<Product> = appDatabase.productDao().getAll()

    override suspend fun insertAll(users: List<Product>) = appDatabase.productDao().insertAll(users)
}