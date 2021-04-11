package com.example.additionalliterature.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.additionalliterature.R
import com.example.additionalliterature.models.CommonModel
import com.example.additionalliterature.utilits.NODE_ERROR
import com.example.additionalliterature.utilits.REF_DATABASE_ROOT
import com.example.additionalliterature.utilits.showToast

class RecyclerViewAdapterErrorList(private var mErrorList: MutableList<CommonModel> = mutableListOf()) :
    RecyclerView.Adapter<RecyclerViewAdapterErrorList.ErrorViewHolder>() {

    private lateinit var mRefError: String

    class ErrorViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var uid: TextView? = itemView.findViewById(R.id.id_user_send_error)
        var errorText: TextView? = itemView.findViewById(R.id.text_user_send_error)
        var deleteError: Button? = itemView.findViewById(R.id.button_delete_error)
    }

    override fun getItemCount() = mErrorList.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ErrorViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.recyclerview_item_error_list, parent, false)
        return ErrorViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ErrorViewHolder, position: Int) {
        holder.uid?.text = mErrorList[position].uid
        holder.errorText?.text = mErrorList[position].error
        holder.deleteError?.setOnClickListener {
            mRefError = mErrorList[position].uid
            REF_DATABASE_ROOT.child(NODE_ERROR).child(mErrorList[position].uid)
                .removeValue()
            showToast("Запись об ошибке удалена!")
        }
    }

    fun setList(list: MutableList<CommonModel>) {
        mErrorList = list
        notifyDataSetChanged()
    }
}