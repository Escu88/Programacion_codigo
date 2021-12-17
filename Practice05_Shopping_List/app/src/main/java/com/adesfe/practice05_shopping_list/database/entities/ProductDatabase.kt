package com.adesfe.practice05_shopping_list.database.entities

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = arrayOf(ProductEntity::class), version = 1)
abstract class ProductDatabase: RoomDatabase() {
    abstract fun productDao():ProductDao

    companion object{
        private var instance:ProductDao? = null

        fun getInstance(context: Context): ProductDao{
            return instance ?: Room.databaseBuilder(context, ProductDatabase::class.java, "tasks-db").build().productDao().also { instance = it }
        }
    }

}