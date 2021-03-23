package com.example.additionalliterature.ui.fragments

import android.text.TextUtils
import androidx.fragment.app.Fragment
import com.example.additionalliterature.MainActivity
import com.example.additionalliterature.R
import com.example.additionalliterature.utilits.*
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import kotlinx.android.synthetic.main.fragment_registration.*

class RegistrationFragment : Fragment(R.layout.fragment_registration) {

    override fun onStart() {
        super.onStart()
        registration_auth.setOnClickListener { backAuthorization() }
        registration_btn.setOnClickListener { signUpUser() }

    }

    private fun backAuthorization() {
        replaceFragment(AuthorizationFragment())
    }

    private fun signUpUser() {
        when {
            TextUtils.isEmpty(bio_email_text_view_user.text.toString()) -> {
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
            else -> AUTH.createUserWithEmailAndPassword(
                bio_email_text_view_user.text.toString(),
                registration_password_edt_text.text.toString()
            )
                .addOnCompleteListener {
                    if (it.isSuccessful) {
                        REF_DATABASE_ROOT.child(NODE_USERS).child(UID).child(CHILD_NAME)
                            .setValue(registration_name_edt_text.text.toString())
                        REF_DATABASE_ROOT.child(NODE_USERS).child(UID).child(CHILD_COURSE)
                            .setValue(registration_course_edt_text.text.toString())
                        REF_DATABASE_ROOT.child(NODE_USERS).child(UID).child(CHILD_ROLE)
                            .setValue(getString(R.string.user))
                        replaceActivity(MainActivity())
                        showToast(getString(R.string.registration_user_toast))
                    } else {
                        showToast(getString(R.string.something_went_wrong))
                    }
                }
        }

    }
}


