package com.example.additionalliterature.ui.fragments.user

import androidx.fragment.app.Fragment
import com.example.additionalliterature.MainActivity
import com.example.additionalliterature.utilits.hideKeyboard

open class BaseUserFragment(layout: Int) : Fragment(layout) {

    override fun onStart() {
        super.onStart()
        val parentActivity = activity
        if (parentActivity is MainActivity) parentActivity.mAppDrawerUser.disableDrawer()
    }
    override fun onStop() {
        super.onStop()
        val parentActivity = activity
        if (parentActivity is MainActivity) parentActivity.mAppDrawerUser.enableDrawer()
    }
}