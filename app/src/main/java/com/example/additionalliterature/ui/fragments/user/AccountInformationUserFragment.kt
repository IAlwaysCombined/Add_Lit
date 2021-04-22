package com.example.additionalliterature.ui.fragments.user

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.View
import com.example.additionalliterature.R
import com.example.additionalliterature.databinding.FragmentAccountInformationUserBinding
import com.example.additionalliterature.utilits.EMAIL
import com.example.additionalliterature.utilits.USER

class AccountInformationUserFragment : BaseUserFragment(R.layout.fragment_account_information_user) {

    private lateinit var binding: FragmentAccountInformationUserBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentAccountInformationUserBinding.bind(view)
    }

    override fun onResume() {
        super.onResume()
        binding.bioEmailTextViewUser.text = EMAIL
        binding.bioNameTextViewUser.text = USER.name

        binding.circleViewImageProfileUser.setOnClickListener {
            val intent = Intent(Intent.ACTION_PICK)
            intent.type = "image/*"
            startActivityForResult(intent, 1)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (resultCode == Activity.RESULT_OK && requestCode == 1) {
            binding.circleViewImageProfileUser.setImageURI(data?.data)
        }
    }
}