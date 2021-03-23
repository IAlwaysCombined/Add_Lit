package com.example.additionalliterature.ui.objects

import android.annotation.SuppressLint
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.drawerlayout.widget.DrawerLayout
import com.example.additionalliterature.R
import com.example.additionalliterature.activities.AuthorizationRegistrationActivity
import com.example.additionalliterature.ui.fragments.AboutUsFragment
import com.example.additionalliterature.ui.fragments.AccountInformationUserFragment
import com.example.additionalliterature.ui.fragments.SendErrorFragment
import com.example.additionalliterature.ui.fragments.SendNewsFragment
import com.example.additionalliterature.utilits.replaceActivity
import com.example.additionalliterature.utilits.replaceFragment
import com.google.firebase.auth.FirebaseAuth
import com.mikepenz.materialdrawer.Drawer
import com.mikepenz.materialdrawer.DrawerBuilder
import com.mikepenz.materialdrawer.model.PrimaryDrawerItem
import com.mikepenz.materialdrawer.model.interfaces.IDrawerItem

class AppDrawerUser(
    var mainActivity: AppCompatActivity,
    var toolBar: Toolbar) {

    private lateinit var mDrawer: Drawer
    private lateinit var mDrawerLayout: DrawerLayout

    fun create() {
        createDrawer()
        mDrawerLayout = mDrawer.drawerLayout
    }

    fun disableDrawer() {
        mDrawer.actionBarDrawerToggle?.isDrawerIndicatorEnabled = false
        mainActivity.supportActionBar?.setDisplayHomeAsUpEnabled(true)
        mDrawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED)
        toolBar.setNavigationOnClickListener {
            mainActivity.supportFragmentManager.popBackStack()
        }
    }

    fun enableDrawer() {
        mainActivity.supportActionBar?.setDisplayHomeAsUpEnabled(false)
        mDrawer.actionBarDrawerToggle?.isDrawerIndicatorEnabled = true
        mDrawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_UNLOCKED)
        toolBar.setNavigationOnClickListener {
            mDrawer.openDrawer()
        }
    }

    @SuppressLint("ResourceAsColor")
    private fun createDrawer() {
        mDrawer = DrawerBuilder()
            .withActivity(mainActivity)
            .withToolbar(toolBar)
            .withSliderBackgroundColorRes(R.color.colorToolbar)
            .withActionBarDrawerToggle(true)
            .withSelectedItem(-1)
            .addDrawerItems(
                PrimaryDrawerItem().withIdentifier(100)
                    .withIconTintingEnabled(true)
                    .withName("Информация об аккаунте")
                    .withSelectable(false)
                    .withIcon(R.drawable.account_info_toolbar),
                PrimaryDrawerItem().withIdentifier(101)
                    .withIconTintingEnabled(true)
                    .withName("Предложить новость")
                    .withSelectable(false)
                    .withIcon(R.drawable.account_send_news_toolbar),
                PrimaryDrawerItem().withIdentifier(102)
                    .withIconTintingEnabled(true)
                    .withName("Сообщить об ошибке")
                    .withSelectable(false)
                    .withIcon(R.drawable.account_error_toolbar),
                PrimaryDrawerItem().withIdentifier(103)
                    .withIconTintingEnabled(true)
                    .withName("О нас")
                    .withSelectable(false)
                    .withIcon(R.drawable.account_about_us_toolbar),
                PrimaryDrawerItem().withIdentifier(104)
                    .withIconTintingEnabled(true)
                    .withName("Выйти из аккаунта")
                    .withSelectable(false)
                    .withIcon(R.drawable.account_leave_toolbar),
            ).withOnDrawerItemClickListener(object : Drawer.OnDrawerItemClickListener {
                override fun onItemClick(
                    view: View?,
                    position: Int,
                    drawerItem: IDrawerItem<*>
                ): Boolean {
                    when (position) {
                        0 -> mainActivity.replaceFragment(AccountInformationUserFragment())
                        1 -> mainActivity.replaceFragment(SendNewsFragment())
                        2 -> mainActivity.replaceFragment(SendErrorFragment())
                        3 -> mainActivity.replaceFragment(AboutUsFragment())
                        4 -> {
                            mainActivity.replaceActivity(AuthorizationRegistrationActivity())
                        }
                    }
                    return false
                }
            }).build()
    }


}