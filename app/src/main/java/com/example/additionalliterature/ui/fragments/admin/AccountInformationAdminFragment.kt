package com.example.additionalliterature.ui.fragments.admin

import android.app.Activity
import android.content.Intent
import com.example.additionalliterature.R
import com.example.additionalliterature.utilits.EMAIL
import com.example.additionalliterature.utilits.USER
import kotlinx.android.synthetic.main.fragment_account_information_admin.*

class AccountInformationAdminFragment : BaseAdminFragment(R.layout.fragment_account_information_admin) {

    override fun onResume() {
        super.onResume()
        bio_email_text_view_admin.text = EMAIL
        bio_name_text_view_admin.text = USER.name

        circle_view_image_profile_admin.setOnClickListener {
            val intent = Intent(Intent.ACTION_PICK)
            intent.type = "image/*"
            startActivityForResult(intent, 1)
        }
    }
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (resultCode == Activity.RESULT_OK && requestCode == 1) {
            circle_view_image_profile_admin.setImageURI(data?.data)
        }
    }
}