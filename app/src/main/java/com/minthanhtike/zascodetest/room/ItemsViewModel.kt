package com.minthanhtike.zascodetest.room

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.minthanhtike.zascodetest.data.HomeData
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ItemsViewModel(application: Application):AndroidViewModel(application) {
    val allItems :LiveData<List<HomeData>>
    private val repo : ItemsRepo
    init {
        val itemsDao =ItemsDb.getDataBase(application).itemsDao()
        repo= ItemsRepo(itemsDao)
        allItems=repo.allItems
    }

    fun addToBag(homeData: HomeData){
        viewModelScope.launch (Dispatchers.IO){
            repo.insert(homeData)
        }
    }

    fun deleteItems(homeData: HomeData){
        viewModelScope.launch (Dispatchers.IO){
            repo.delete(homeData)
        }
    }
}