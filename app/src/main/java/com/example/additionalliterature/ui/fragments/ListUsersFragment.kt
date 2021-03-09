package com.example.additionalliterature.ui.fragments

import androidx.recyclerview.widget.LinearLayoutManager
import com.example.additionalliterature.R
import com.example.additionalliterature.adapters.RecyclerViewAdapterUsersList
import kotlinx.android.synthetic.main.fragment_list_users.*

class ListUsersFragment : BaseAdminFragment(R.layout.fragment_list_users) {

    override fun onResume() {
        super.onResume()
        list_user_recycler_view.layoutManager = LinearLayoutManager(this.context)
        list_user_recycler_view.adapter = RecyclerViewAdapterUsersList(fillList())
    }

    private fun fillList(): List<String> {

        val data = mutableListOf<String>()
        (0..30).forEach { i -> data.add("$i пользователь") }
        return data
    }
}