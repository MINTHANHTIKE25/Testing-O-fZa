package com.minthanhtike.zascodetest.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.core.util.toHalf
import androidx.recyclerview.widget.RecyclerView
import com.minthanhtike.zascodetest.R
import com.minthanhtike.zascodetest.data.HomeData
import com.minthanhtike.zascodetest.room.ItemsViewModel
import com.squareup.picasso.Picasso
import java.text.DecimalFormat

class BagAdapter(private val itemsViewModel: ItemsViewModel,private val context: Context, private val itemsList :List<HomeData>) :
    RecyclerView.Adapter<BagAdapter.ViewHolder>() {
    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        val imageView=itemView.findViewById<ImageView>(R.id.photo_img)
        val nameTv=itemView.findViewById<TextView>(R.id.name_tv_bag)
        val priceTv=itemView.findViewById<TextView>(R.id.price_tv_bag)
        val plusBtn=itemView.findViewById<ImageButton>(R.id.plus_btn_bag)
        val minusBtn=itemView.findViewById<ImageButton>(R.id.minus_bag_btn)
        val amountTv=itemView.findViewById<TextView>(R.id.amount_tv_bag)
        val kiloTv=itemView.findViewById<TextView>(R.id.kilogram_tv)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BagAdapter.ViewHolder {
        return ViewHolder(LayoutInflater.from(context).inflate(R.layout.bag_recycle_items,parent,false))
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: BagAdapter.ViewHolder, position: Int) {
        Picasso.get().load(itemsList[position].photosUrl)
            .into(holder.imageView)
        holder.nameTv.text=itemsList[position].name
        holder.priceTv.text=itemsList[position].price.toString()
        holder.plusBtn.setOnClickListener {
            var amount=holder.amountTv.text.toString().toInt()
            amount++;
            holder.kiloTv.text=amount.toString()+"kg"
            holder.amountTv.text=amount.toString()
            holder.priceTv.text=(itemsList[position].price*amount.toDouble()).toHalf().toString()

            val currentPrice=DecimalFormat(".00").format(itemsList[position].price*amount)
            holder.priceTv.text=currentPrice.toString()

        }
        holder.minusBtn.setOnClickListener {
            var amount=holder.amountTv.text.toString().toInt()
            amount--;
            if (amount == 0){
                itemsViewModel.deleteItems(itemsList[position])
            }else{
                holder.kiloTv.text=amount.toString()+"kg"
                holder.amountTv.text=amount.toString()
                holder.priceTv.text=(itemsList[position].price*amount.toDouble()).toHalf().toString()
                val currentPrice=DecimalFormat(".00").format(itemsList[position].price*amount)
                holder.priceTv.text=(currentPrice).toString()

            }
        }
    }

    override fun getItemCount(): Int {
        return itemsList.size
    }
}