package com.example.additionalliterature.ui.fragments.admin

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.example.additionalliterature.R
import com.example.additionalliterature.adapters.RecyclerViewAdapterErrorList
import com.example.additionalliterature.databinding.FragmentErrorListBinding
import com.example.additionalliterature.models.CommonModel
import com.example.additionalliterature.utilits.AppValueEventListener
import com.example.additionalliterature.utilits.NODE_ERROR
import com.example.additionalliterature.utilits.REF_DATABASE_ROOT
import com.example.additionalliterature.utilits.getCommonModel
import com.google.firebase.database.DatabaseReference


class ErrorListFragment : Fragment(R.layout.fragment_error_list) {

    private lateinit var mAdapter: RecyclerViewAdapterErrorList
    private lateinit var mRecyclerView: RecyclerView
    private lateinit var mRefError: DatabaseReference
    private lateinit var mErrorListener: AppValueEventListener
    private var mErrorList = mutableListOf<CommonModel>()
    private lateinit var binding: FragmentErrorListBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentErrorListBinding.bind(view)
    }

    override fun onResume() {
        super.onResume()
        getAllErrors()
    }

    private fun getAllErrors() {
        mRecyclerView = binding.listErrorRecyclerView
        mAdapter = RecyclerViewAdapterErrorList(mutableListOf())
        mRefError = REF_DATABASE_ROOT.child(NODE_ERROR)
        mRecyclerView.adapter = mAdapter
        mErrorListener = AppValueEventListener { dataSnapshot ->
            mErrorList = dataSnapshot.children.map { it.getCommonModel() } as MutableList<CommonModel>
            mAdapter.setList(mErrorList)
            mRecyclerView.smoothScrollToPosition(mAdapter.itemCount)
        }
        mRefError.addValueEventListener(mErrorListener)
    }
}