package com.adesfe.practice05_shopping_list.database

import com.adesfe.practice05_shopping_list.database.entities.ProductEntity

interface MyDao{
    fun getAllProducts(): MutableList<ProductEntity>

    fun addProducts(productEntity: ProductEntity):Long

    fun getProductById(id: Long): ProductEntity

    fun updateProduct(productEntity: ProductEntity):Int

    fun deleteProduct(productEntity: ProductEntity):Int
}