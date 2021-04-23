package com.example.additionalliterature.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.additionalliterature.R
import com.example.additionalliterature.models.CommonModel

class RecyclerViewAdapterNewsList(private var mNewsList: MutableList<CommonModel> = mutableListOf()) :
    RecyclerView.Adapter<RecyclerViewAdapterNewsList.UserViewHolder>() {

    private lateinit var mRefRole: String

    class UserViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var titleNews: TextView? = itemView.findViewById(R.id.text_view_spelling)
        var imageUrl: ImageView? = itemView.findViewById(R.id.news_image_view)
        var readNews: Button? = itemView.findViewById(R.id.btn_read_news)
    }

    override fun getItemCount() = mNewsList.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.recyclerview_item_news_list, parent, false)
        return UserViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        holder.titleNews?.text = mNewsList[position].newsTitle
        holder.readNews?.setOnClickListener {

        }
    }

    fun setList(list: List<CommonModel>) {
        mNewsList = list as MutableList<CommonModel>
        notifyDataSetChanged()
    }
}