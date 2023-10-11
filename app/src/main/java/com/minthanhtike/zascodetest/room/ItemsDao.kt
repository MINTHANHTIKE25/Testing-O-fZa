package com.minthanhtike.zascodetest.room

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.minthanhtike.zascodetest.data.HomeData
import kotlinx.coroutines.flow.Flow

@Dao
interface ItemsDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(homeData: HomeData)

    @Query("Select * from items_table")
    fun allItems() : LiveData<List<HomeData>>

    @Delete
    suspend fun deleteItems(homeData: HomeData)

    @Query("Delete From items_table")
    suspend fun deleteAllItems();
}