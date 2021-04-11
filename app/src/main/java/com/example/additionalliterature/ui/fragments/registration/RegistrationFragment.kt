package com.example.additionalliterature.ui.fragments.registration

import android.text.TextUtils
import androidx.fragment.app.Fragment
import com.example.additionalliterature.MainActivity
import com.example.additionalliterature.R
import com.example.additionalliterature.utilits.*
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
                        val uid = AUTH.currentUser?.uid.toString()
                        val dateMap = mutableMapOf<String, Any>()
                        dateMap[CHILD_EMAIL] = bio_email_text_view_user.text.toString()
                        dateMap[CHILD_NAME] = registration_name_edt_text.text.toString()
                        dateMap[CHILD_COURSE] = registration_course_edt_text.text.toString()
                        dateMap[CHILD_ROLE] = USER_ROLE
                        dateMap[CHILD_UID] = uid
                        REF_DATABASE_ROOT.child(NODE_USERS).child(uid).updateChildren(dateMap)
                            .addOnCompleteListener {task ->
                                if(task.isSuccessful){
                                    replaceActivity(MainActivity())
                                    showToast(getString(R.string.registration_user_toast))
                                } else showToast(getString(R.string.something_went_wrong))
                            }
                    } else showToast(getString(R.string.something_went_wrong))
                }
        }
    }
}


