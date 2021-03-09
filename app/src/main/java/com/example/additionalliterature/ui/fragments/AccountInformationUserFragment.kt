package com.example.additionalliterature.ui.fragments

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Intent
import com.example.additionalliterature.R
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*
import kotlinx.android.synthetic.main.fragment_account_information_user.*

@Suppress("DEPRECATION")
class AccountInformationUserFragment : BaseUserFragment(R.layout.fragment_account_information_user) {

    private lateinit var mAuth: FirebaseAuth
    private lateinit var databaseReference: DatabaseReference
    private lateinit var database: FirebaseDatabase

    override fun onResume() {
        super.onResume()
        mAuth = FirebaseAuth.getInstance()
        database = FirebaseDatabase.getInstance()
        databaseReference = database.reference.child("Users")

        loadProfile()

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


    @SuppressLint("SetTextI18n")
    private fun loadProfile() {
        val user = mAuth.currentUser
        val unreferenced = databaseReference.child(user?.uid!!)

        bio_email_text_view_user.text = user.email


        unreferenced.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                bio_name_text_view_user.text =
                    getString(R.string.name_text) + ": " + snapshot.child("name").value.toString()
                bio_course_text_view_user.text =
                    getString(R.string.course_text) + ": " + snapshot.child("course").value.toString()
            }

            override fun onCancelled(error: DatabaseError) {

            }
        })
    }
}