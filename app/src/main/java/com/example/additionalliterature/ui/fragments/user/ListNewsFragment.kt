package com.example.additionalliterature.ui.fragments.user

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.additionalliterature.R
import com.example.additionalliterature.adapters.RecyclerViewAdapterNewsList
import com.example.additionalliterature.databinding.FragmentListNewsBinding

class ListNewsFragment : Fragment(R.layout.fragment_list_news) {

    private lateinit var binding: FragmentListNewsBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentListNewsBinding.bind(view)
    }

    override fun onResume() {
        super.onResume()
        binding.listNewsRecyclerView.layoutManager = LinearLayoutManager(this.context)
        binding.listNewsRecyclerView.adapter = RecyclerViewAdapterNewsList(fillList())
    }

    private fun fillList(): List<String> {
        val data = mutableListOf<String>()
        (0..30).forEach { i -> data.add("$i новость") }
        return data
    }
}