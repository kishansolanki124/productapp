package com.app.productapp.db

import com.app.productapp.db.entity.Product

interface DatabaseHelper {

    suspend fun getUsers(): List<Product>

    suspend fun insertAll(users: List<Product>)
}