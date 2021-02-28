package com.example.additionalliterature.ui


import android.text.TextUtils
import androidx.fragment.app.Fragment
import com.example.additionalliterature.MainActivity
import com.example.additionalliterature.R
import com.example.additionalliterature.utilits.replaceActivity
import com.example.additionalliterature.utilits.replaceFragment
import com.example.additionalliterature.utilits.showToast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import kotlinx.android.synthetic.main.fragment_avtorization.*
import kotlinx.android.synthetic.main.fragment_registration.*

class RegistrationFragment : Fragment(R.layout.fragment_registration) {

    private lateinit var mAuth: FirebaseAuth

    override fun onStart() {
        super.onStart()
        registration_auth.setOnClickListener { backAuthorization() }
        registration_btn.setOnClickListener { signUpUser() }
        mAuth = FirebaseAuth.getInstance()
    }

    private fun backAuthorization() {
        replaceFragment(AuthorizationFragment())
    }

    private fun signUpUser() {
        if (TextUtils.isEmpty(registration_email_edt_text.text.toString())){
            showToast("Заполните Email")
            return
        }else if(TextUtils.isEmpty(registration_password_edt_text.text.toString())){
            showToast("Заполните пароль")
            return
        }

        mAuth.createUserWithEmailAndPassword(registration_email_edt_text.text.toString(), registration_password_edt_text.text.toString())
            .addOnCompleteListener { task ->
                if(task.isSuccessful){
                    val currentUser = mAuth.currentUser
                    showToast("Пользователь зарегистрирован")
                    replaceActivity(MainActivity())
                }
                else{
                    showToast("Что- то пошло не так!")
                }
            }
    }
}



