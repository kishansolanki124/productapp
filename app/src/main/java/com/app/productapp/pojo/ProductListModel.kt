package com.app.productapp.pojo

class ProductListModel : ArrayList<ProductListModel.ProductListItem>(){
    data class ProductListItem(
        var adjective: String = "",
        var color: String = "",
        var createdAt: String = "",
        var id: String = "",
        var image: String = "",
        var material: String = "",
        var name: String = "",
        var price: String = ""
    )
}