package com.example.additionalliterature.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.additionalliterature.R
import com.example.additionalliterature.models.CommonModel
import com.example.additionalliterature.utilits.*

class RecyclerViewAdapterNewsRequestList(private var mRequestNewsList: MutableList<CommonModel> = mutableListOf()) :
    RecyclerView.Adapter<RecyclerViewAdapterNewsRequestList.RequestNewsViewHolder>() {

    private lateinit var mRefRequestNews: String

    class RequestNewsViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var titleNewsRequest: TextView? = itemView.findViewById(R.id.list_title_news_text_view)
        var textNewsRequest: TextView? = itemView.findViewById(R.id.list_text_news_text_view)
        var deleteNewsRequest: Button? = itemView.findViewById(R.id.list_delete_btn)
        var publishNewsRequest: Button? = itemView.findViewById(R.id.list_publish_btn)
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): RecyclerViewAdapterNewsRequestList.RequestNewsViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.recyclerview_item_news_request_list, parent, false)
        return RecyclerViewAdapterNewsRequestList.RequestNewsViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: RequestNewsViewHolder, position: Int) {
        holder.titleNewsRequest?.text = mRequestNewsList[position].newsTitle
        holder.textNewsRequest?.text = mRequestNewsList[position].newsText
        holder.deleteNewsRequest?.setOnClickListener {
            mRefRequestNews = mRequestNewsList[position].uid
            REF_DATABASE_ROOT.child(NODE_NEWS_REQUEST).child(mRequestNewsList[position].uid)
                .removeValue()
            showToast("Предложеная новость удалена!")
        }
        holder.publishNewsRequest?.setOnClickListener {
            val dateMap = mutableMapOf<String, Any>()
            dateMap[CHILD_NEWS_TITLE] = mRequestNewsList[position].newsTitle
            dateMap[CHILD_NEWS_TEXT] = mRequestNewsList[position].newsText
            dateMap[CHILD_NEWS_URL] = mRequestNewsList[position].newsUrl
            dateMap[CHILD_NEWS_IMAGE_URL] = mRequestNewsList[position].newsImageUrl
            REF_DATABASE_ROOT.child(NODE_USERS).child(mRequestNewsList[position].uid).updateChildren(dateMap)
            showToast("Новость опубликована")
        }
    }

    override fun getItemCount() = mRequestNewsList.size

    fun setList(list: List<CommonModel>) {
        mRequestNewsList = list as MutableList<CommonModel>
        notifyDataSetChanged()
    }
}