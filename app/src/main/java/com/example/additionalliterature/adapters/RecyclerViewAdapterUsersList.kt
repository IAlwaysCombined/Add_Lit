package com.example.additionalliterature.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.additionalliterature.R
import com.example.additionalliterature.models.Users
import com.example.additionalliterature.utilits.*
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference

class RecyclerViewAdapterUsersList(private var mUserList: MutableList<Users> = mutableListOf()) :
    RecyclerView.Adapter<RecyclerViewAdapterUsersList.UserViewHolder>() {

    private lateinit var mRefRole: String

    class UserViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var role: TextView? = itemView.findViewById(R.id.list_user_role_text_view)
        var uid: TextView? = itemView.findViewById(R.id.list_uid_text_view)
        var assignUser: Button? = itemView.findViewById(R.id.list_assign_btn)
    }

    override fun getItemCount() = mUserList.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.recyclerview_item_users_list, parent, false)
        return UserViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        holder.role?.text = mUserList[position].role
        holder.uid?.text = mUserList[position].uid

        holder.assignUser?.setOnClickListener {
            mRefRole = mUserList[position].role
            if (mRefRole == USER_ROLE) {
                val dateMap = mutableMapOf<String, Any>()
                dateMap[CHILD_ROLE] = ADMIN_ROLE
                REF_DATABASE_ROOT.child(NODE_USERS).child(mUserList[position].uid).updateChildren(dateMap)
                showToast("Администратор успешно назначен")
            }
            else showToast("Пользователь уже является администратором")
        }


    }

    fun setList(list: List<Users>) {
        mUserList = list as MutableList<Users>
        notifyDataSetChanged()
    }
}