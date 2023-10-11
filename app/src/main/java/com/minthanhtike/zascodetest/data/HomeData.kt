package com.minthanhtike.zascodetest.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "items_table")
data class HomeData(
    @PrimaryKey (autoGenerate = false) var id:Int=0,
    @ColumnInfo (name = "name") val name:String,
    @ColumnInfo (name = "price")val price:Double,
    @ColumnInfo (name = "photosUrl")val photosUrl:String){


}