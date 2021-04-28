package com.app.productapp.pojo

import com.app.productapp.db.entity.Product

class ProductListModel : ArrayList<Product>() {
    data class ProductListItem(
        var adjective: String = "",
        var color: String = "",
        var material: String = "",
        var name: String = "",
        var price: String = ""
    )
}