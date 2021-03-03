package com.example.additionalliterature.ui

import android.annotation.SuppressLint
import androidx.fragment.app.Fragment
import com.example.additionalliterature.R
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*
import kotlinx.android.synthetic.main.fragment_account_information.*

class AccountInformationFragment : Fragment(R.layout.fragment_account_information) {

    lateinit var auth: FirebaseAuth
    var databaseReference: DatabaseReference? = null
    var database: FirebaseDatabase? = null

    override fun onStart() {
        super.onStart()

        auth = FirebaseAuth.getInstance()
        database = FirebaseDatabase.getInstance()
        databaseReference = database?.reference!!.child("Users")

        loadProfile()

    }

    @SuppressLint("SetTextI18n")
    private fun loadProfile() {
        val user = auth.currentUser
        val unreferenced = databaseReference?.child(user?.uid!!)

        bio_email_text_view.text = getString(R.string.email_text) + ": " + user?.email

        unreferenced?.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {

                bio_name_text_view.text = getString(R.string.name_text) + ": " + snapshot.child("name").value.toString()
                bio_course_text_view.text = getString(R.string.course_text) + ": " + snapshot.child("course").value.toString()
            }

            override fun onCancelled(error: DatabaseError) {

            }
        })
    }
}