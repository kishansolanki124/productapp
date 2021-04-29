package com.app.productapp.network

import com.app.productapp.pojo.ProductListModel
import com.app.productapp.utils.AppConstant
import retrofit2.http.*

interface APIEndPointsInterface {

    @GET(AppConstant.APIEndPoints.products)
    suspend fun getProducts(): ProductListModel

    @POST(AppConstant.APIEndPoints.products)
    suspend fun addProduct(
        @Body productListItem: ProductListModel.ProductListItem
    ): ProductListModel.ProductListItem

    @PUT(AppConstant.APIEndPoints.products + "/{id}")
    suspend fun editProduct(
        @Body productListItem: ProductListModel.ProductListItem,
        @Path("id") id: String
    ): ProductListModel.ProductListItem
}