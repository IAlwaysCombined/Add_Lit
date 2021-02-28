package com.example.additionalliterature.ui


import androidx.fragment.app.Fragment
import com.example.additionalliterature.MainActivity
import com.example.additionalliterature.R
import com.example.additionalliterature.utilits.replaceActivity
import com.example.additionalliterature.utilits.replaceFragment
import com.example.additionalliterature.utilits.showToast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import kotlinx.android.synthetic.main.fragment_registration.*

class RegistrationFragment : Fragment(R.layout.fragment_registration) {


    override fun onStart() {
        super.onStart()
        registration_authorization.setOnClickListener { backAuthorization() }
        registration_btn.setOnClickListener { signUpUser() }
    }

    private fun backAuthorization() {
        replaceFragment(AuthorizationFragment())
    }

    private fun signUpUser() {
            val users = hashMapOf(
                "Email" to registration_edt_text.text.toString(),
                "Password" to registration_password_edt_text.text.toString(),
                "Full Name" to registration_name_edt_text.text.toString() + " " + registration_last_name_edt_text.text.toString(),
                "Course" to registration_course_edt_text.text.toString()
            )
            val db = Firebase.firestore
            db.collection("users").document("user")
                .collection("user_id")
                .add(users)
                .addOnSuccessListener {
                    showToast("Пользователь зарегистрирован!")
                    replaceActivity(MainActivity())
                }
                .addOnFailureListener {
                    showToast("Ошибка!")
                }
    }
}



