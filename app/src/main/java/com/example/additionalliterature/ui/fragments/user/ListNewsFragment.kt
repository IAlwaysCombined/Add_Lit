package com.example.additionalliterature.ui.fragments.user

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.example.additionalliterature.R
import com.example.additionalliterature.adapters.RecyclerViewAdapterNewsList
import com.example.additionalliterature.adapters.RecyclerViewAdapterUsersList
import com.example.additionalliterature.databinding.FragmentListNewsBinding
import com.example.additionalliterature.models.CommonModel
import com.example.additionalliterature.models.Users
import com.example.additionalliterature.utilits.*
import com.google.firebase.database.DatabaseReference

class ListNewsFragment : Fragment(R.layout.fragment_list_news) {

    private lateinit var binding: FragmentListNewsBinding
    private lateinit var mAdapter: RecyclerViewAdapterNewsList
    private lateinit var mRecyclerView: RecyclerView
    private lateinit var mRefUser: DatabaseReference
    private lateinit var mNewsListener: AppValueEventListener
    private var mNewsList = mutableListOf<CommonModel>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentListNewsBinding.bind(view)
    }

    override fun onResume() {
        super.onResume()
        getAllNews()
    }

    private fun getAllNews() {
        mRecyclerView = binding.listNewsRecyclerView
        mAdapter = RecyclerViewAdapterNewsList(mutableListOf())
        mRefUser = REF_DATABASE_ROOT.child(NODE_NEWS)
        mRecyclerView.adapter = mAdapter
        mNewsListener = AppValueEventListener { dataSnapshot ->
            mNewsList = dataSnapshot.children.map { it.getCommonModel() } as MutableList<CommonModel>
            mAdapter.setList(mNewsList)
            mRecyclerView.smoothScrollToPosition(mAdapter.itemCount)
        }
        mRefUser.addValueEventListener(mNewsListener)
    }
}