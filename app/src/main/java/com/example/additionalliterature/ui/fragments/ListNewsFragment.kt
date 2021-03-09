package com.example.additionalliterature.ui.fragments

import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.additionalliterature.R
import com.example.additionalliterature.adapters.RecyclerViewAdapterNewsList
import kotlinx.android.synthetic.main.fragment_list_news.*

class ListNewsFragment : Fragment(R.layout.fragment_list_news) {

    override fun onResume() {
        super.onResume()
        list_news_recycler_view.layoutManager = LinearLayoutManager(this.context)
        list_news_recycler_view.adapter = RecyclerViewAdapterNewsList(fillList())
    }

    private fun fillList(): List<String> {
        val data = mutableListOf<String>()
        (0..30).forEach { i -> data.add("$i новость") }
        return data
    }
}