package com.example.additionalliterature.ui.fragments.registration

import android.os.Bundle
import android.text.TextUtils
import android.view.View
import androidx.fragment.app.Fragment
import com.example.additionalliterature.MainActivity
import com.example.additionalliterature.R
import com.example.additionalliterature.databinding.FragmentRegistrationBinding
import com.example.additionalliterature.utilits.*

class RegistrationFragment : Fragment(R.layout.fragment_registration) {

    private lateinit var binding: FragmentRegistrationBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentRegistrationBinding.bind(view)
    }

    override fun onStart() {
        super.onStart()
        binding.registrationAuth.setOnClickListener { backAuthorization() }
        binding.registrationBtn.setOnClickListener { signUpUser() }

    }

    private fun backAuthorization() {
        replaceFragment(AuthorizationFragment())
    }

    private fun signUpUser() {
        when {
            TextUtils.isEmpty(binding.bioEmailTextViewUser.text.toString()) -> {
                showToast(getString(R.string.email_edt_text_not_filled))
                return
            }
            TextUtils.isEmpty(binding.registrationPasswordEdtText.text.toString()) -> {
                showToast(getString(R.string.password_edt_text_not_filled))
                return
            }
            TextUtils.isEmpty(binding.registrationNameEdtText.text.toString()) -> {
                showToast(getString(R.string.name_edt_text_not_filled))
                return
            }
            else -> AUTH.createUserWithEmailAndPassword(
                binding.bioEmailTextViewUser.text.toString(),
                binding.registrationPasswordEdtText.text.toString()
            )
                .addOnCompleteListener {
                    if (it.isSuccessful) {
                        val uid = AUTH.currentUser?.uid.toString()
                        val dateMap = mutableMapOf<String, Any>()
                        dateMap[CHILD_EMAIL] = binding.bioEmailTextViewUser.text.toString()
                        dateMap[CHILD_NAME] = binding.registrationNameEdtText.text.toString()
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


