package com.example.additionalliterature.ui

import androidx.fragment.app.Fragment
import com.example.additionalliterature.MainActivity
import com.example.additionalliterature.R
import com.example.additionalliterature.utilits.replaceActivity
import com.example.additionalliterature.utilits.replaceFragment
import com.example.additionalliterature.utilits.showToast
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.android.synthetic.main.fragment_avtorization.*


class AuthorizationFragment : Fragment(R.layout.fragment_avtorization) {

    override fun onStart() {
        super.onStart()
        login_btn.setOnClickListener { signInUser() }
        login_registration.setOnClickListener { openRegistrationFragment() }
        login_restore_password.setOnClickListener { openRestorePassFragment() }
    }

    private fun openRestorePassFragment() {
        replaceFragment(RestorePassFragment())
    }

    private fun openRegistrationFragment() {
        replaceFragment(RegistrationFragment())
    }

    private fun signInUser() {
        val db = FirebaseFirestore.getInstance()
        db.collection("users").document("user")
            .collection("user_id")
            .get()
            .addOnCompleteListener {
                val result: StringBuffer = StringBuffer()
                if (it.isSuccessful) {
                    for (document in it.result!!) {
                        result.append(document.data.getValue("Email")).append(" ")
                            .append(document.data.getValue("Password")).append("\n\n")
                        val loginAuthorization = document["Email"] as String?
                        val passAuthorization = document["Password"] as String?
                        if (loginAuthorization == login_edt_text.text.toString() && passAuthorization == login_password_edt_text.text.toString()) {
                            //Производить старт Activity
                            replaceActivity(MainActivity())
                            break
                        } else {
                            showToast("Пользователь не авторизован!")
                            break
                        }
                    }
                }
            }
    }
}


