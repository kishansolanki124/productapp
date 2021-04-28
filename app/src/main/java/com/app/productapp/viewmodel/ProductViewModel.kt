package com.app.productapp.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.app.productapp.network.APIEndPointsInterface
import com.app.productapp.network.RetrofitFactory
import com.app.productapp.pojo.ProductListModel
import com.app.pweapp.apputil.ResultOf
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import okio.IOException
import retrofit2.HttpException

class ProductViewModel : ViewModel() {

    // for internal usage
    private val _gameListResponseModel = MutableLiveData<ResultOf<ProductListModel>>()
    private val _productItem = MutableLiveData<ResultOf<ProductListModel.ProductListItem>>()

    // Expose to the outside world
    val gameListResponseResponseModel: LiveData<ResultOf<ProductListModel>> =
        _gameListResponseModel
val productItem: LiveData<ResultOf<ProductListModel.ProductListItem>> = _productItem

    private var apiEndPointsInterface =
        RetrofitFactory.createService(APIEndPointsInterface::class.java)

    /**
     * Dispatchers.IO for network or disk operations that takes longer time and runs in background thread
     */
    fun getProducts() {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val apiResponse = apiEndPointsInterface.getProducts()
                _gameListResponseModel.postValue(ResultOf.Success(apiResponse))
            } catch (ioe: IOException) {
                _gameListResponseModel.postValue(ResultOf.Failure("[IO] error please retry", ioe))
            } catch (he: HttpException) {
                _gameListResponseModel.postValue(ResultOf.Failure("[HTTP] error please retry", he))
            }
        }
    }
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
}