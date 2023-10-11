package com.minthanhtike.zascodetest.frags

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.minthanhtike.zascodetest.R
import com.minthanhtike.zascodetest.adapter.BagAdapter
import com.minthanhtike.zascodetest.room.ItemsViewModel


class BagFragments : Fragment() {
    private lateinit var itemsViewModel: ItemsViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_bag_fragments, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val recyclerView = view.findViewById<RecyclerView>(R.id.recyclerView_bag)
        itemsViewModel=ViewModelProvider(this).get(ItemsViewModel::class.java)

        itemsViewModel.allItems.observe(viewLifecycleOwner, Observer {
            val bagAdapter=BagAdapter(itemsViewModel,view.context,it)
            recyclerView.layoutManager=LinearLayoutManager(view.context)
            recyclerView.adapter=bagAdapter
        })
    }
}