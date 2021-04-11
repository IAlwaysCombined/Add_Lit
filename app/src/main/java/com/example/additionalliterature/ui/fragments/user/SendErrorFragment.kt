package com.example.additionalliterature.ui.fragments.user

import com.example.additionalliterature.R
import com.example.additionalliterature.utilits.*
import kotlinx.android.synthetic.main.fragment_send_error.*

class SendErrorFragment : BaseUserFragment(R.layout.fragment_send_error) {

    override fun onResume() {
        super.onResume()
        send_error_btn.setOnClickListener { sendError() }
    }

    //Send error
    private fun sendError() {
        val uid = AUTH.currentUser?.uid.toString()
        val dateMap = mutableMapOf<String, Any>()
        dateMap[CHILD_ERROR] = send_error_edit_text.text.toString()
        dateMap[CHILD_UID] = uid
        REF_DATABASE_ROOT.child(NODE_ERROR).child(uid).updateChildren(dateMap)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    showToast(getString(R.string.error_send_toast))
                } else showToast(getString(R.string.something_went_wrong))
            }
    }
}