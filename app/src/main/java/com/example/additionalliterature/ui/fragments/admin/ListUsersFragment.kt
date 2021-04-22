package com.example.additionalliterature.ui.fragments.admin

import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.example.additionalliterature.R
import com.example.additionalliterature.adapters.RecyclerViewAdapterUsersList
import com.example.additionalliterature.databinding.FragmentListUsersBinding
import com.example.additionalliterature.models.Users
import com.example.additionalliterature.utilits.AppValueEventListener
import com.example.additionalliterature.utilits.NODE_USERS
import com.example.additionalliterature.utilits.REF_DATABASE_ROOT
import com.example.additionalliterature.utilits.getUsers
import com.google.firebase.database.DatabaseReference

class ListUsersFragment : BaseAdminFragment(R.layout.fragment_list_users) {

    private lateinit var mAdapter: RecyclerViewAdapterUsersList
    private lateinit var mRecyclerView: RecyclerView
    private lateinit var mRefUser: DatabaseReference
    private lateinit var mUsersListener: AppValueEventListener
    private var mUserList = mutableListOf<Users>()
    private lateinit var binding: FragmentListUsersBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentListUsersBinding.bind(view)
    }

    override fun onResume() {
        super.onResume()
        getAllUsers()
    }

    private fun getAllUsers() {
        mRecyclerView = binding.listUserRecyclerView
        mAdapter = RecyclerViewAdapterUsersList(mutableListOf())
        mRefUser = REF_DATABASE_ROOT.child(NODE_USERS)
        mRecyclerView.adapter = mAdapter
        mUsersListener = AppValueEventListener { dataSnapshot ->
            mUserList = dataSnapshot.children.map { it.getUsers() } as MutableList<Users>
            mAdapter.setList(mUserList)
            mRecyclerView.smoothScrollToPosition(mAdapter.itemCount)
        }
        mRefUser.addValueEventListener(mUsersListener)
    }
}