package com.minthanhtike.zascodetest.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.minthanhtike.zascodetest.data.HomeData

@Database(entities = [HomeData::class], version = 1, exportSchema = true)
abstract class ItemsDb : RoomDatabase(){
    abstract fun itemsDao():ItemsDao

    companion object{
        @Volatile
        private var INSTANCE:ItemsDb? = null

        fun getDataBase(context: Context) : ItemsDb{
            //this ?: means that if INSTANCE is null then left hand side will work and not null the right will work
            return INSTANCE ?: synchronized(this){
                val instance = Room.databaseBuilder(context.applicationContext,
                    ItemsDb::class.java,
                    "items_database")
                    .build()
                INSTANCE = instance
                return instance
            }
        }
    }
}