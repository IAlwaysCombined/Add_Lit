package com.example.additionalliterature.ui.fragments

import androidx.fragment.app.Fragment
import com.example.additionalliterature.MainActivity

open class BaseAdminFragment(layout: Int) : Fragment(layout) {

    override fun onStart() {
        super.onStart()
        val parentActivity = activity
        if (parentActivity is MainActivity) parentActivity.mAppDrawerAdmin.disableDrawer()
    }
    override fun onStop() {
        super.onStop()
        val parentActivity = activity
        if (parentActivity is MainActivity) parentActivity.mAppDrawerAdmin.enableDrawer()
    }
}