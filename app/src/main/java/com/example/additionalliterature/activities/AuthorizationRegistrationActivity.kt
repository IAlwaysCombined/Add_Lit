package com.example.additionalliterature.activities

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.additionalliterature.databinding.ActivityAuthorizationrRegistrationBinding
import com.example.additionalliterature.ui.fragments.AuthorizationFragment
import com.example.additionalliterature.utilits.replaceFragmentAuthorization


class AuthorizationRegistrationActivity : AppCompatActivity() {

    private lateinit var mBinding: ActivityAuthorizationrRegistrationBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = ActivityAuthorizationrRegistrationBinding.inflate(layoutInflater)
        setContentView(mBinding.root)
    }

    override fun onStart() {
        super.onStart()
        startFragment()
    }

    private fun startFragment() {
        replaceFragmentAuthorization(AuthorizationFragment())
    }
}