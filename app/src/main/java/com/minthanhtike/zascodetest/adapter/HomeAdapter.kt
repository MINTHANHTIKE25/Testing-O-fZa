package com.minthanhtike.zascodetest.adapter

import android.app.Activity
import android.content.Context
import android.util.DisplayMetrics
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.button.MaterialButton
import com.minthanhtike.zascodetest.R
import com.minthanhtike.zascodetest.data.HomeData
import com.minthanhtike.zascodetest.room.ItemsViewModel
import com.squareup.picasso.Picasso

class HomeAdapter(private val itemViewModel: ItemsViewModel, private val context :Context, private val mList: ArrayList<HomeData>) :
    RecyclerView.Adapter<HomeAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomeAdapter.ViewHolder {
        return ViewHolder(LayoutInflater.from(context).inflate(R.layout.home_recycle_items,parent,false))
    }

    override fun onBindViewHolder(holder: HomeAdapter.ViewHolder, position: Int) {
        val displayMetrics=DisplayMetrics()

        (context as Activity).windowManager.defaultDisplay.getMetrics(displayMetrics)
        val itemsWidth=displayMetrics.widthPixels/2-80
        val itemsHeight: Int=displayMetrics.heightPixels/3-30
        holder.itemView.layoutParams.width=itemsWidth
//        holder.itemView.layoutParams.height=itemsHeight
        holder.image.layoutParams.height=itemsHeight/2-20
        Picasso.get().load(mList[position].photosUrl)
            .into(holder.image)
        holder.name.text= mList[position].name
        holder.price.text= mList[position].price.toString()
        holder.addBtn.setOnClickListener {
            val selectItem = HomeData(
                id = mList[position].id,
                name = mList[position].name,
                price = mList[position].price,
                photosUrl = mList[position].photosUrl )
            itemViewModel.addToBag(selectItem)
            Toast.makeText(context,"Added to bag successfully",Toast.LENGTH_SHORT).show()
        }
    }

    override fun getItemCount(): Int {
        return mList.size
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val image=itemView.findViewById<ImageView>(R.id.home_item_img)
        val name=itemView.findViewById<TextView>(R.id.vgt_name_tv)
        val price=itemView.findViewById<TextView>(R.id.price_tv)
        val addBtn=itemView.findViewById<ImageButton>(R.id.add_img_btn)
    }
}