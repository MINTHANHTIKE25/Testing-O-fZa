package com.minthanhtike.zascodetest.frags

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.widget.SearchView
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.Recycler
import com.minthanhtike.zascodetest.R
import com.minthanhtike.zascodetest.adapter.HomeAdapter
import com.minthanhtike.zascodetest.data.HomeData
import com.minthanhtike.zascodetest.room.ItemsViewModel


class HomeFragment : Fragment() {
    val photosArray= arrayOf(
        "https://media.istockphoto.com/id/1388403435/photo/fresh-carrots-isolated-on-white-background.jpg?s=1024x1024&w=is&k=20&c=AZlR713OB95BftaWVrKHOLKcbX1VYSWBHfGs1C6_nj8=",
        "https://media.istockphoto.com/id/1290615948/photo/fresh-spinach-on-a-white-background.jpg?s=1024x1024&w=is&k=20&c=mZVfB2jagy1C_bc_N_FxG1pHiZwE3HwskaG4NXOLD1w=",
        "https://media.istockphoto.com/id/1675860973/photo/fresh-broccoli-on-a-gray-background.jpg?s=1024x1024&w=is&k=20&c=5uHcxoVXFs3M4NARiwL0pt5ChwoPQW5UmgV3Glx2pC4=",
        "https://media.istockphoto.com/id/498307806/photo/eggplants-isolated-on-white-background-with-clipping-path.jpg?s=1024x1024&w=is&k=20&c=PUmejBtUU4GibvXweN9G1dJNtpnQM1Nz7AzFQBnsI50=",
        "https://media.istockphoto.com/id/178083170/photo/tomato.jpg?s=1024x1024&w=is&k=20&c=dVC6SVDWXsM52R7sP3ijlwhzMUjvuKzZRkH5-fPDqWw=",
        "https://media.istockphoto.com/id/1076882402/photo/fresh-organic-vegetables-potatoes-on-the-white-background.jpg?s=2048x2048&w=is&k=20&c=MMTXzavkyDJhJq4ZmWUPbSHPktgYGZShiI1Xz0wJA3M=",
        "https://media.istockphoto.com/id/486114781/photo/several-mandarins.jpg?s=1024x1024&w=is&k=20&c=lsZA_6KBYwVm77ZljnLMLitoZvOt9zg0FPzqT-_O44c=",
        "https://media.istockphoto.com/id/1481381964/photo/fresh-apricots-isolated-on-white-background-ripe-apricots-with-copy-space-for-text-fresh.jpg?s=1024x1024&w=is&k=20&c=gvRLDUC4TVYZBXPiLgM8G1PdiY26GsdsWVtpKdaRYWw=",
        "https://images.unsplash.com/photo-1632992468737-54880593aada?ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D&auto=format&fit=crop&w=2070&q=80",
        "https://images.unsplash.com/photo-1603052875302-d376b7c0638a?ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D&auto=format&fit=crop&w=1974&q=80"
    )

    val vegName= arrayOf(
        "Carrot","Spinach","Broccoli","Brinjal","Tomato","Potato","Orange","Apricot","Capsicum","Banana"
    )
    val price= arrayOf(
        15.25,8.78,7.40,12.50,7.40,12.50,10.4,5.45,6.90,12.42
    )

    private lateinit var itemsViewModel: ItemsViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val searchView=view.findViewById<SearchView>(R.id.searchView)
        val searchResultTv=view.findViewById<TextView>(R.id.search_rst_tv)
        val recyclerView=view.findViewById<RecyclerView>(R.id.items_recycler_home)
        //viewmodels
        itemsViewModel=ViewModelProvider(this).get(ItemsViewModel::class.java)
        searchView.isActivated=false
        searchView.setOnQueryTextListener(object :SearchView.OnQueryTextListener{
            override fun onQueryTextSubmit(query: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                searchResultTv.text = "\' $newText \'"
                searchResultTv.setTextColor(resources.getColor(R.color.pretty_red,null))
                return true
            }
        })

        setUpRecycler(itemsViewModel,recyclerView,view)
    }

    fun setUpRecycler(itemsViewModel: ItemsViewModel,recycler: RecyclerView,view: View):Unit{
        recycler.layoutManager=GridLayoutManager(view.context,2)
        val data=ArrayList<HomeData>()
        for (i in 0..9){
            data.add(HomeData(i,vegName[i],price[i],photosArray[i]))
        }
        val homeAdapter=HomeAdapter(itemsViewModel,view.context,data)
        recycler.adapter=homeAdapter
    }

}