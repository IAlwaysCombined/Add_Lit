package com.example.additionalliterature.ui.objects

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.additionalliterature.R

class RecyclerViewAdapterUsersList(private val values: List<String>) :
    RecyclerView.Adapter<RecyclerViewAdapterUsersList.MyViewHolder>() {

    override fun getItemCount() = values.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.recyclerview_item_users_list, parent, false)
        return MyViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.idUser?.text = values[position]
        holder.emailUser?.text = "Email"
    }

    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var idUser: TextView? = null
        var emailUser: TextView? = null

        init {
            idUser = itemView.findViewById(R.id.list_user_id_text_view)
            emailUser = itemView.findViewById(R.id.list_email_id_text_view)
        }
    }
}