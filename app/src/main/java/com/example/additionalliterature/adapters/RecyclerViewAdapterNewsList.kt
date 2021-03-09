package com.example.additionalliterature.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.additionalliterature.R

class RecyclerViewAdapterNewsList(private val values: List<String>) :
    RecyclerView.Adapter<RecyclerViewAdapterNewsList.MyViewHolder>() {

    override fun getItemCount() = values.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.recyclerview_item_news_list, parent, false)
        return MyViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.idUser?.text = values[position]
    }

    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var idUser: TextView? = null

        init {
            idUser = itemView.findViewById(R.id.text_view_spelling)
        }
    }
}