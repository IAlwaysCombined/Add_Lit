package com.example.additionalliterature.ui.fragments.user

import android.os.Bundle
import android.view.View
import com.example.additionalliterature.R
import com.example.additionalliterature.databinding.FragmentSendErrorBinding
import com.example.additionalliterature.utilits.*

class SendErrorFragment : BaseUserFragment(R.layout.fragment_send_error) {

    private lateinit var binding: FragmentSendErrorBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentSendErrorBinding.bind(view)
    }

    override fun onResume() {
        super.onResume()
        binding.sendErrorBtn.setOnClickListener { sendError() }
    }

    //Send error
    private fun sendError() {
        val uid = AUTH.currentUser?.uid.toString()
        val dateMap = mutableMapOf<String, Any>()
        dateMap[CHILD_ERROR] = binding.sendErrorBtn.text.toString()
        dateMap[CHILD_UID] = uid
        REF_DATABASE_ROOT.child(NODE_ERROR).child(uid).updateChildren(dateMap)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    showToast(getString(R.string.error_send_toast))
                } else showToast(getString(R.string.something_went_wrong))
            }
    }
}