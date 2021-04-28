package com.app.productapp.network

import com.app.productapp.pojo.ProductListModel
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface APIEndPointsInterface {

//    @POST(AppConstants.APIEndPoints.LOGIN_USER)
//    suspend fun loginUser(
//        @Body requestLoginModel: LoginRequestModel
//    ): LoginResponseModel
//
//    @POST(AppConstants.APIEndPoints.ADD_ADDRESS)
//    suspend fun addAddress(
//        @Body addressRequestModel: AddressRequestModel
//    ): AddressResponseModel
//
//    @POST(AppConstants.APIEndPoints.UPDATE_ADDRESS)
//    suspend fun updateAddress(
//        @Body updatearessRequestModel: UpdatearessRequestModel
//    ): AddressResponseModel
//
//    //multipart/form-data
//    @POST(AppConstants.APIEndPoints.ADD_USER)
//    suspend fun addUser(
//        @Body hashMap: MultipartBody
//    ): SignupResponseModel
//
//    //multipart/form-data
//    @POST(AppConstants.APIEndPoints.ADD_GUEST_USER)
//    suspend fun addGuestUser(
//        @Body hashMap: MultipartBody
//    ): GuestLoginResponseModel
//
//    //multipart/form-data
//    @POST(AppConstants.APIEndPoints.VERIFY_USER)
//    suspend fun verifyUser(
//        @Body hashMap: MultipartBody
//    ): SignupResponseModel
//
//    //multipart/form-data
//    @POST(AppConstants.APIEndPoints.IS_USER_EXIST)
//    suspend fun isUserExist(
//        @Body hashMap: MultipartBody
//    ): SignupResponseModel
//
//    //multipart/form-data
//    @POST(AppConstants.APIEndPoints.RESET_PASSWORD)
//    suspend fun resetPassword(
//        @Body hashMap: MultipartBody
//    ): CommonResponseModel

    @GET("products")
    suspend fun getProducts(): ProductListModel

    //
//    @GET(AppConstants.APIEndPoints.GAMES)
//    suspend fun getGames(
//        @Query("field[]") fields: List<String>
//    ): GameListResponseModel
//
//    @GET(AppConstants.APIEndPoints.GAME_DETAILS)
//    suspend fun gameDetails(
//        @Path("id") id: String,
//        @Query("field[]") fields: List<String>
//    ): Response<GameDetailsModel>
//
//    @GET(AppConstants.APIEndPoints.GAME_NEWS)
//    suspend fun gameNews(
//        @Path("id") id: String,
//        @Query("limit") limit: String,
//        @Query("offset") offset: String,
//    ): Response<NewsListResponseModel>
//
//    @GET(AppConstants.APIEndPoints.VIRTUAL_CURRENCY)
//    suspend fun virtualCurrency(
//        @Path("id") id: String,
//        @Query("limit") limit: String,
//    ): Response<VirtualCurrencyModel>
//
//    @GET(AppConstants.APIEndPoints.GAME_SALE)
//    suspend fun gameSale(
//        @Path("id") id: String): Response<SaleListModel>
//
//    @GET(AppConstants.APIEndPoints.ADDITIONAL_CONTENT)
//    suspend fun additionalContent(
//        @Path("id") id: String,
//        @Query("limit") fields: Int
//    ): Response<AdditionalContentModel>
//
//    @GET(AppConstants.APIEndPoints.USER_ME)
//    suspend fun getUserDetails(
//        @Query("field[]") fields: List<String>
//    ): Response<UserDetailModel>
//
//    @POST(AppConstants.APIEndPoints.ADYEN_PAYMENT_METHOD)
//    suspend fun paymentMethods(
//        @Body paymentMethodsRequest: PaymentMethodsRequest
//    ): Response<AdyenPaymentMethodResponse>
//
//    @GET(AppConstants.APIEndPoints.ZENDESK_TICKET_LIST)
//    suspend fun zendeskTicketList(
//        @Query ("query") query: String,
//        @Query ("sort_order") sortOrder: String,
//        @Query ("sort_by") sortBy: String
//    ): Response<ZendeskTicketListResponse>
//
//    @POST(AppConstants.APIEndPoints.ADYEN_PAYMENTS)
//    suspend fun payments(
//        @Body paymentRequest: AdyenPaymentRequest
//    ): Response<AdyenPaymentTransactionResponse>
//
////    @GET(AppConstants.APIEndPoints.LOGOUT_USER)
////    suspend fun logoutUser(): CommonResponseModel
////
////    @POST(AppConstants.APIEndPoints.CAT_WISE_PRODUCTS)
////    suspend fun catWiseProducts(
////        @Body hashMap: MultipartBody
////    ): CatWiseProductListModel
////
////    @POST(AppConstants.APIEndPoints.CART_PRODUCT_LIST)
////    suspend fun cartProductList(
////        @Body cartProductListRequest: CartProductListRequest
////    ): CatWiseProductListModel
////
    @POST("products")
    suspend fun addProduct(
        @Body productListItem: ProductListModel.ProductListItem
    ): ProductListModel.ProductListItem
////
////    @POST(AppConstants.APIEndPoints.SEARCH_PRODUCT_LIST)
////    suspend fun searchProductList(
////        @Body hashMap: MultipartBody
////    ): CatWiseProductListModel
////
////    @POST(AppConstants.APIEndPoints.ORDER_LIST)
////    suspend fun orderList(
////        @Body hashMap: MultipartBody
////    ): OrderListModel
////
////    @POST(AppConstants.APIEndPoints.SEARCH_PRODUCT)
////    suspend fun searchProduct(
////        @Body searchProductRequestModel: SearchProductRequestModel
////    ): CatWiseProductListModel
}