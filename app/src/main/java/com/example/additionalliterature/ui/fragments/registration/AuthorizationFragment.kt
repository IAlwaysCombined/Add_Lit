package com.example.additionalliterature.ui.fragments.registration

import android.os.Bundle
import android.text.TextUtils
import android.view.View
import androidx.fragment.app.Fragment
import com.example.additionalliterature.MainActivity
import com.example.additionalliterature.R
import com.example.additionalliterature.databinding.FragmentAuthorizationBinding
import com.example.additionalliterature.utilits.AUTH
import com.example.additionalliterature.utilits.replaceActivity
import com.example.additionalliterature.utilits.replaceFragment
import com.example.additionalliterature.utilits.showToast

class AuthorizationFragment : Fragment(R.layout.fragment_authorization) {

    private lateinit var binding: FragmentAuthorizationBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentAuthorizationBinding.bind(view)
    }

    override fun onStart() {
        super.onStart()
        binding.authBtn.setOnClickListener { signInUser() }
        binding.authRegistration.setOnClickListener { openRegistrationFragment() }
        binding.authRestorePassword.setOnClickListener { restorePass() }
    }

    private fun restorePass() {
        if (TextUtils.isEmpty(binding.authEmailEdtText.text)) {
            showToast(getString(R.string.email_edt_text_not_filled))
            return
        }
        val emailAddress = binding.authEmailEdtText.text.toString()
        AUTH.sendPasswordResetEmail(emailAddress)
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
        if (TextUtils.isEmpty(binding.authEmailEdtText.text.toString())) {
            showToast(getString(R.string.email_edt_text_not_filled))
            return
        } else if (TextUtils.isEmpty(binding.authPasswordEdtText.text.toString())) {
            showToast(getString(R.string.password_edt_text_not_filled))
            return
        }
        AUTH.signInWithEmailAndPassword(
            binding.authEmailEdtText.text.toString(),
            binding.authPasswordEdtText.text.toString()
        )
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


