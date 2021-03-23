package com.example.additionalliterature.ui.fragments

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Intent
import com.example.additionalliterature.R
import com.example.additionalliterature.utilits.*
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*
import kotlinx.android.synthetic.main.fragment_account_information_admin.*

class AccountInformationAdminFragment : BaseAdminFragment(R.layout.fragment_account_information_admin) {


    override fun onResume() {
        super.onResume()
        loadProfile()

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


    @SuppressLint("SetTextI18n")
    private fun loadProfile() {
        bio_email_text_view_admin.text = EMAIL

        REF_DATABASE_ROOT.child(NODE_USERS).child(UID)
            .addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                bio_name_text_view_admin.text =
                    getString(R.string.name_text) + ": " + snapshot.child(CHILD_NAME).value.toString()
                bio_course_text_view_admin.text =
                    getString(R.string.course_text) + ": " + snapshot.child(CHILD_COURSE).value.toString()
            }
            override fun onCancelled(error: DatabaseError) {

            }
        })
    }
}