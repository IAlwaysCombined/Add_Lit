package com.example.additionalliterature.ui.fragments

import android.text.TextUtils
import androidx.fragment.app.Fragment
import com.example.additionalliterature.MainActivity
import com.example.additionalliterature.R
import com.example.additionalliterature.utilits.replaceActivity
import com.example.additionalliterature.utilits.replaceFragment
import com.example.additionalliterature.utilits.showToast
import com.google.firebase.auth.FirebaseAuth

import kotlinx.android.synthetic.main.fragment_authorization.*



class AuthorizationFragment : Fragment(R.layout.fragment_authorization) {

    private lateinit var mAuth: FirebaseAuth

    override fun onStart() {
        super.onStart()
        auth_btn.setOnClickListener { signInUser() }
        auth_registration.setOnClickListener { openRegistrationFragment() }
        auth_restore_password.setOnClickListener { restorePass() }
        mAuth = FirebaseAuth.getInstance()
    }

    private fun restorePass() {
        if (TextUtils.isEmpty(auth_email_edt_text.text)){
            showToast(getString(R.string.email_edt_text_not_filled))
            return
        }
        val emailAddress = auth_email_edt_text.text.toString()
        mAuth.sendPasswordResetEmail(emailAddress)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    showToast(getString(R.string.password_reset_message_sent))
                }
                else{
                    showToast(getString(R.string.something_went_wrong))
                }
            }
    }

    private fun openRegistrationFragment() {
        replaceFragment(RegistrationFragment())
    }

    private fun signInUser() {
        if (TextUtils.isEmpty(auth_email_edt_text.text.toString())){
            showToast(getString(R.string.email_edt_text_not_filled))
            return
        }else if(TextUtils.isEmpty(auth_password_edt_text.text.toString())){
            showToast(getString(R.string.password_edt_text_not_filled))
            return
        }
        mAuth.signInWithEmailAndPassword(auth_email_edt_text.text.toString(), auth_password_edt_text.text.toString())
            .addOnCompleteListener { task ->
                if(task.isSuccessful){
                    replaceActivity(MainActivity())
                }
                else{
                    showToast(getString(R.string.something_went_wrong))
                }
            }
    }
}


