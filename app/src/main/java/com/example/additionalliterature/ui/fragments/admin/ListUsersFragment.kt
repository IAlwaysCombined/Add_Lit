package com.example.additionalliterature.ui.fragments.admin

import androidx.recyclerview.widget.RecyclerView
import com.example.additionalliterature.R
import com.example.additionalliterature.adapters.RecyclerViewAdapterUsersList
import com.example.additionalliterature.models.Users
import com.example.additionalliterature.utilits.*
import com.google.firebase.database.DatabaseReference
import kotlinx.android.synthetic.main.fragment_list_users.*

class ListUsersFragment : BaseAdminFragment(R.layout.fragment_list_users) {

    private lateinit var mAdapter: RecyclerViewAdapterUsersList
    private lateinit var mRecyclerView: RecyclerView
    private lateinit var mRefUser: DatabaseReference
    private lateinit var mUsersListener: AppValueEventListener
    private var mUserList = mutableListOf<Users>()

    override fun onResume() {
        super.onResume()
        getAllUsers()
    }

    private fun getAllUsers() {
        mRecyclerView = list_user_recycler_view
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