package com.adesfe.practice05_shopping_list.database.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "producto")

data class ProductEntity (
    @PrimaryKey(autoGenerate = true)
    var id:Int = 0,
    var name:String = "",
    var cantidad:Int = 0,
    var precio:Double = 0.0
)