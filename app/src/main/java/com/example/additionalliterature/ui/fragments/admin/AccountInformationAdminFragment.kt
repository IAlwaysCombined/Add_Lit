package com.example.additionalliterature.ui.fragments.admin

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.View
import com.example.additionalliterature.R
import com.example.additionalliterature.databinding.FragmentAccountInformationAdminBinding
import com.example.additionalliterature.utilits.EMAIL
import com.example.additionalliterature.utilits.USER

class AccountInformationAdminFragment : BaseAdminFragment(R.layout.fragment_account_information_admin) {

    lateinit var binding: FragmentAccountInformationAdminBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentAccountInformationAdminBinding.bind(view)
    }

    override fun onResume() {
        super.onResume()
        binding.bioEmailTextViewAdmin.text = EMAIL
        binding.bioNameTextViewAdmin.text = USER.name

        binding.circleViewImageProfileAdmin.setOnClickListener {
            val intent = Intent(Intent.ACTION_PICK)
            intent.type = "image/*"
            startActivityForResult(intent, 1)
        }
    }
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (resultCode == Activity.RESULT_OK && requestCode == 1) {
            binding.circleViewImageProfileAdmin.setImageURI(data?.data)
        }
    }
}