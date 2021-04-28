package com.app.productapp.db.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity
data class Product(
    @PrimaryKey @ColumnInfo(name = "id", defaultValue = "") val id: String,
    @ColumnInfo(name = "name", defaultValue = "") val name: String,
    @ColumnInfo(name = "adjective", defaultValue = "") val adjective: String,
    @ColumnInfo(name = "price", defaultValue = "") val price: String,
    @ColumnInfo(name = "color", defaultValue = "") val color: String,
    @ColumnInfo(name = "image", defaultValue = "") val image: String,
    @ColumnInfo(name = "material", defaultValue = "") val material: String,
    @ColumnInfo(name = "createdAt", defaultValue = "") val createdAt: String
) : Serializable