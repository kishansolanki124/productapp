package com.app.productapp.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.app.productapp.db.DatabaseHelper
import com.app.productapp.network.APIEndPointsInterface
import com.app.productapp.network.RetrofitFactory
import com.app.productapp.pojo.ProductListModel
import com.app.productapp.utils.ResultOf
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import okio.IOException
import retrofit2.HttpException

class ProductViewModel(private val dbHelper: DatabaseHelper) : ViewModel() {

    // for internal usage
    private val _productListModel = MutableLiveData<ResultOf<ProductListModel>>()
    private val _networkProductList = MutableLiveData<ResultOf<ProductListModel>>()
    private val _productItem = MutableLiveData<ResultOf<ProductListModel.ProductListItem>>()

    // Expose to the outside world
    val productListModel: LiveData<ResultOf<ProductListModel>> = _productListModel
    val networkProductList: LiveData<ResultOf<ProductListModel>> = _networkProductList
    val productItem: LiveData<ResultOf<ProductListModel.ProductListItem>> = _productItem

    private var apiEndPointsInterface =
        RetrofitFactory.createService(APIEndPointsInterface::class.java)

    /**
     * Dispatchers.IO for network or disk operations that takes longer time and runs in background thread
     */
    fun addProduct(productListItem: ProductListModel.ProductListItem) {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val apiResponse = apiEndPointsInterface.addProduct(productListItem)
                _productItem.postValue(ResultOf.Success(apiResponse))
            } catch (ioe: IOException) {
                _productItem.postValue(ResultOf.Failure("[IO] error please retry", ioe))
            } catch (he: HttpException) {
                _productItem.postValue(ResultOf.Failure("[HTTP] error please retry", he))
            }
        }
    }

    /**
     * Dispatchers.IO for network or disk operations that takes longer time and runs in background thread
     */
    fun editProduct(productListItem: ProductListModel.ProductListItem, id: String) {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val apiResponse = apiEndPointsInterface.editProduct(productListItem, id)
                _productItem.postValue(ResultOf.Success(apiResponse))
            } catch (ioe: IOException) {
                _productItem.postValue(ResultOf.Failure("[IO] error please retry", ioe))
            } catch (he: HttpException) {
                _productItem.postValue(ResultOf.Failure("[HTTP] error please retry", he))
            }
        }
    }

    fun fetchFromServer() {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val apiResponse = apiEndPointsInterface.getProducts()
                _networkProductList.postValue(ResultOf.Success(apiResponse))
                if (apiResponse.isNotEmpty()) {
                    dbHelper.deleteAll()//if someone have deleted from server side then updating in our database
                    dbHelper.insertAll(apiResponse)
                }
            } catch (ioe: IOException) {
                _networkProductList.postValue(ResultOf.Failure("[IO] error please retry", ioe))
            } catch (he: HttpException) {
                _networkProductList.postValue(ResultOf.Failure("[HTTP] error please retry", he))
            }
        }
    }

    fun fetchFromDB() {
        viewModelScope.launch(Dispatchers.IO) {
            val productsFromDB = dbHelper.getProducts()
            val productListModel = ProductListModel()
            productListModel.addAll(productsFromDB)
            _productListModel.postValue(ResultOf.Success(productListModel))
        }

        fetchFromServer()
    }
}