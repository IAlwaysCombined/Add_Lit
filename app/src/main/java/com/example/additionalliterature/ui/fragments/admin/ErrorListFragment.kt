package com.example.additionalliterature.ui.fragments.admin

import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.example.additionalliterature.R
import com.example.additionalliterature.adapters.RecyclerViewAdapterErrorList
import com.example.additionalliterature.adapters.RecyclerViewAdapterUsersList
import com.example.additionalliterature.models.CommonModel
import com.example.additionalliterature.models.Users
import com.example.additionalliterature.utilits.*
import com.example.additionalliterature.utilits.getUsers
import com.google.firebase.database.DatabaseReference
import kotlinx.android.synthetic.main.fragment_error_list.*
import kotlinx.android.synthetic.main.fragment_list_users.*


class ErrorListFragment : Fragment(R.layout.fragment_error_list) {

    private lateinit var mAdapter: RecyclerViewAdapterErrorList
    private lateinit var mRecyclerView: RecyclerView
    private lateinit var mRefError: DatabaseReference
    private lateinit var mErrorListener: AppValueEventListener
    private var mErrorList = mutableListOf<CommonModel>()

    override fun onResume() {
        super.onResume()
        getAllErrors()
    }

    private fun getAllErrors() {
        mRecyclerView = list_error_recycler_view
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