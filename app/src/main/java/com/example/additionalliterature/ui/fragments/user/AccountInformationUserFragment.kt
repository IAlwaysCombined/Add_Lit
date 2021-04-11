package com.example.additionalliterature.ui.fragments.user

import android.app.Activity
import android.content.Intent
import com.example.additionalliterature.R
import com.example.additionalliterature.utilits.*
import kotlinx.android.synthetic.main.fragment_account_information_user.*

class AccountInformationUserFragment : BaseUserFragment(R.layout.fragment_account_information_user) {

    override fun onResume() {
        super.onResume()

        bio_email_text_view_user.text = EMAIL
        bio_name_text_view_user.text = USER.name

        circle_view_image_profile_user.setOnClickListener {
            val intent = Intent(Intent.ACTION_PICK)
            intent.type = "image/*"
            startActivityForResult(intent, 1)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (resultCode == Activity.RESULT_OK && requestCode == 1) {
            circle_view_image_profile_user.setImageURI(data?.data)
        }
    }
}