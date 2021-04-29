package com.app.productapp.db

import com.app.productapp.db.entity.Product

interface DatabaseHelper {

    suspend fun getProducts(): List<Product>

    suspend fun insertAll(products: List<Product>)

    suspend fun deleteAll()
}