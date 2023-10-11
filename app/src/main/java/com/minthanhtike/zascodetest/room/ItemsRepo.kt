package com.minthanhtike.zascodetest.room

import androidx.lifecycle.LiveData
import com.minthanhtike.zascodetest.data.HomeData
import kotlinx.coroutines.flow.Flow

class ItemsRepo(private val itemsDao: ItemsDao) {
    val allItems:LiveData<List<HomeData>> = itemsDao.allItems();

    suspend fun insert(homeData: HomeData){
        itemsDao.insert(homeData)
    }

    suspend fun delete(homeData: HomeData){
        itemsDao.deleteItems(homeData)
    }

    suspend fun deleteAllItems(){
        itemsDao.deleteAllItems()
    }
}