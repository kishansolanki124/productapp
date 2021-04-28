package com.app.productapp.utils

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.app.productapp.db.DatabaseHelper
import com.app.productapp.viewmodel.ProductViewModel

class ViewModelFactory(private val dbHelper: DatabaseHelper) :
    ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(ProductViewModel::class.java)) {
            return ProductViewModel(dbHelper) as T
        }
        throw IllegalArgumentException("Unknown class name")
    }
}