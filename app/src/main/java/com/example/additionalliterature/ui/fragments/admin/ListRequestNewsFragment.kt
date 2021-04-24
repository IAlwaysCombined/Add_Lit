package com.example.additionalliterature.ui.fragments.admin

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.example.additionalliterature.R
import com.example.additionalliterature.adapters.RecyclerViewAdapterErrorList
import com.example.additionalliterature.adapters.RecyclerViewAdapterNewsRequestList
import com.example.additionalliterature.adapters.RecyclerViewAdapterUsersList
import com.example.additionalliterature.databinding.FragmentListRequestNewsBinding
import com.example.additionalliterature.models.CommonModel
import com.example.additionalliterature.models.Users
import com.example.additionalliterature.utilits.*
import com.google.firebase.database.DatabaseReference


class ListRequestNewsFragment : Fragment(R.layout.fragment_list_request_news) {

    private lateinit var binding: FragmentListRequestNewsBinding
    private lateinit var mAdapter: RecyclerViewAdapterNewsRequestList
    private lateinit var mRecyclerView: RecyclerView
    private lateinit var mRefURequestNews: DatabaseReference
    private lateinit var mRequestNewsListener: AppValueEventListener
    private var mRequestNewsList = mutableListOf<CommonModel>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentListRequestNewsBinding.bind(view)
    }

    override fun onResume() {
        super.onResume()
        getAppRequestNews()
    }

    private fun getAppRequestNews() {
        mRecyclerView = binding.listNewsRequestRecyclerView
        mAdapter = RecyclerViewAdapterNewsRequestList(mutableListOf())
        mRefURequestNews = REF_DATABASE_ROOT.child(NODE_NEWS_REQUEST)
        mRecyclerView.adapter = mAdapter
        mRequestNewsListener = AppValueEventListener { dataSnapshot ->
            mRequestNewsList = dataSnapshot.children.map { it.getCommonModel() } as MutableList<CommonModel>
            mAdapter.setList(mRequestNewsList)
            mRecyclerView.smoothScrollToPosition(mAdapter.itemCount)
        }
        mRefURequestNews.addValueEventListener(mRequestNewsListener)
    }
}