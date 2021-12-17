package com.adesfe.practice05_shopping_list.database.entities

import androidx.room.*
import com.adesfe.practice05_shopping_list.database.MyDao

@Dao
interface ProductDao:MyDao {
    @Query("SELECT * FROM producto")
    override fun getAllProducts(): MutableList<ProductEntity>

    @Insert
    override fun addProducts(productEntity: ProductEntity):Long

    @Query("SELECT * FROM producto WHERE id LIKE :id")
    override fun getProductById(id: Long): ProductEntity

    @Update
    override fun updateProduct(productEntity: ProductEntity): Int

    @Delete
    override fun deleteProduct(productEntity: ProductEntity): Int

}