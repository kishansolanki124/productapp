package com.app.productapp.utils

import androidx.annotation.Nullable
import androidx.recyclerview.widget.DiffUtil
import com.app.productapp.db.entity.Product

class ProductDiffCallback(private val oldList: List<Product>, private val newList: List<Product>) :
    DiffUtil.Callback() {

    override fun getOldListSize(): Int = oldList.size

    override fun getNewListSize(): Int = newList.size

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldList[oldItemPosition].id === newList[newItemPosition].id
    }

    override fun areContentsTheSame(oldPosition: Int, newPosition: Int): Boolean {
        val (_, value, name1) = oldList[oldPosition]
        val (_, value1, name2) = newList[newPosition]

        return name1 == name2 && value == value1
    }

    @Nullable
    override fun getChangePayload(oldPosition: Int, newPosition: Int): Any? {
        return super.getChangePayload(oldPosition, newPosition)
    }
}