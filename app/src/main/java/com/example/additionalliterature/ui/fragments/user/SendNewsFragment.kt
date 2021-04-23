package com.example.additionalliterature.ui.fragments.user

import android.os.Bundle
import android.view.View
import com.example.additionalliterature.R
import com.example.additionalliterature.databinding.FragmentSendNewsBinding
import com.example.additionalliterature.utilits.*


class SendNewsFragment : BaseUserFragment(R.layout.fragment_send_news) {

    private lateinit var binding: FragmentSendNewsBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentSendNewsBinding.bind(view)
    }

    override fun onResume() {
        super.onResume()
        binding.sendNewsBtn.setOnClickListener { sendNews() }
    }


    //Send news
    private fun sendNews() {
        when {
            binding.sendUrlNewsEdtText.text.toString().isEmpty() -> {
                showToast(getString(R.string.url_news_edt_text_not_field))
                return
            }
            binding.sendTitleEdtText.text.toString().isEmpty() -> {
                showToast(getString(R.string.title_news_edt_text_not_field))
                return
            }
            binding.sendTextNewsEdtText.text.toString().isEmpty() -> {
                showToast(getString(R.string.text_news_edt_text_not_field))
                return
            }
            else -> {
                val uid = AUTH.currentUser?.uid.toString()
                val dateMap = mutableMapOf<String, Any>()
                dateMap[CHILD_NEWS_URL] = binding.sendUrlNewsEdtText.text.toString()
                dateMap[CHILD_NEWS_TITLE] = binding.sendTitleEdtText.text.toString()
                dateMap[CHILD_NEWS_TEXT] = binding.sendTextNewsEdtText.text.toString()
                val newsKey = REF_DATABASE_ROOT.child(NODE_NEWS_REQUEST).child(uid).push().key
                if (newsKey != null) {
                    REF_DATABASE_ROOT.child(NODE_NEWS_REQUEST).child(uid).child(newsKey).updateChildren(dateMap)
                        .addOnCompleteListener { task ->
                            if (task.isSuccessful) {
                                showToast(getString(R.string.news_send_toast))
                            } else showToast(getString(R.string.something_went_wrong))
                        }
                }
            }
        }
    }
}