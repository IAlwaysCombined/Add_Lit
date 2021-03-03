package com.example.additionalliterature.ui

import android.text.TextUtils
import androidx.fragment.app.Fragment
import com.example.additionalliterature.MainActivity
import com.example.additionalliterature.R
import com.example.additionalliterature.utilits.replaceActivity
import com.example.additionalliterature.utilits.replaceFragment
import com.example.additionalliterature.utilits.showToast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import kotlinx.android.synthetic.main.fragment_registration.*

class RegistrationFragment : Fragment(R.layout.fragment_registration) {

    private lateinit var mAuth: FirebaseAuth
    var databaseReference: DatabaseReference? = null
    var database: FirebaseDatabase? = null

    override fun onStart() {
        super.onStart()
        registration_auth.setOnClickListener { backAuthorization() }
        registration_btn.setOnClickListener { signUpUser() }

        mAuth = FirebaseAuth.getInstance()
        database = FirebaseDatabase.getInstance()
        databaseReference = database?.reference!!.child("Users")
    }

    private fun backAuthorization() {
        replaceFragment(AuthorizationFragment())
    }

    private fun signUpUser() {
        when {
            TextUtils.isEmpty(bio_email_text_view.text.toString()) -> {
                showToast(getString(R.string.email_edt_text_not_filled))
                return
            }
            TextUtils.isEmpty(registration_password_edt_text.text.toString()) -> {
                showToast(getString(R.string.password_edt_text_not_filled))
                return
            }
            TextUtils.isEmpty(registration_name_edt_text.text.toString()) -> {
                showToast(getString(R.string.name_edt_text_not_filled))
                return
            }
            TextUtils.isEmpty(registration_course_edt_text.text.toString()) -> {
                showToast(getString(R.string.course_edt_text_not_filled))
                return
            }
            else -> mAuth.createUserWithEmailAndPassword(
                bio_email_text_view.text.toString(),
                registration_password_edt_text.text.toString()
            )
                .addOnCompleteListener {
                    if (it.isSuccessful) {
                        val currentUser = mAuth.currentUser
                        val currentUSerDb = databaseReference?.child((currentUser?.uid!!))
                        currentUSerDb?.child("name")
                            ?.setValue(registration_name_edt_text.text.toString())
                        currentUSerDb?.child("course")
                            ?.setValue(registration_course_edt_text.text.toString())
                        currentUSerDb?.child("role")
                            ?.setValue("user")
                        replaceActivity(MainActivity())
                    } else {
                        showToast(getString(R.string.something_went_wrong))
                    }
                }
        }

    }
}


