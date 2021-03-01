package com.example.additionalliterature.ui

import android.text.TextUtils
import androidx.fragment.app.Fragment
import com.example.additionalliterature.MainActivity
import com.example.additionalliterature.R
import com.example.additionalliterature.utilits.replaceActivity
import com.example.additionalliterature.utilits.replaceFragment
import com.example.additionalliterature.utilits.showToast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.ktx.Firebase

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
            showToast("Поле Email не заполнено!")
            return
        }
        val emailAddress = auth_email_edt_text.text.toString()
        mAuth.sendPasswordResetEmail(emailAddress)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    showToast("Сообщение для сброса пароля отправленно")
                }
                else{
                    showToast("Пользователя с таким E-mail не существует")
                }
            }
    }

    private fun openRegistrationFragment() {
        replaceFragment(RegistrationFragment())
    }

    private fun signInUser() {
        if (TextUtils.isEmpty(auth_email_edt_text.text.toString())){
            showToast("Заполните Email")
            return
        }else if(TextUtils.isEmpty(auth_password_edt_text.text.toString())){
            showToast("Заполните пароль")
            return
        }
        mAuth.signInWithEmailAndPassword(auth_email_edt_text.text.toString(), auth_password_edt_text.text.toString())
            .addOnCompleteListener { task ->
                if(task.isSuccessful){
                    replaceActivity(MainActivity())
                }
                else{
                    showToast("Не верно введен Email")
                }
            }
    }
}


