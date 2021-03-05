package com.example.additionalliterature.ui.objects

import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.drawerlayout.widget.DrawerLayout
import com.example.additionalliterature.R
import com.example.additionalliterature.activities.AuthorizationRegistrationActivity
import com.example.additionalliterature.ui.fragments.AccountInformationFragment
import com.example.additionalliterature.utilits.replaceActivity
import com.example.additionalliterature.utilits.replaceFragment
import com.google.firebase.auth.FirebaseAuth
import com.mikepenz.materialdrawer.Drawer
import com.mikepenz.materialdrawer.DrawerBuilder
import com.mikepenz.materialdrawer.model.DividerDrawerItem
import com.mikepenz.materialdrawer.model.PrimaryDrawerItem
import com.mikepenz.materialdrawer.model.interfaces.IDrawerItem

class AppDrawerAdmin(var mainActivity: AppCompatActivity, var toolBar: Toolbar, var auth: FirebaseAuth) {

    private lateinit var mDrawerAdmin: Drawer

    fun create(){
        createDrawer()
    }

    private fun createDrawer() {
        mDrawerAdmin = DrawerBuilder()
            .withActivity(mainActivity)
            .withToolbar(toolBar)
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
                    .withName("Предложенные новости")
                    .withSelectable(false)
                    .withIcon(R.drawable.account_send_news_toolbar),
                PrimaryDrawerItem().withIdentifier(102)
                    .withIconTintingEnabled(true)
                    .withName("Список ошибок")
                    .withSelectable(false)
                    .withIcon(R.drawable.account_error_toolbar),
                PrimaryDrawerItem().withIdentifier(103)
                    .withIconTintingEnabled(true)
                    .withName("Список пользователей")
                    .withSelectable(false)
                    .withIcon(R.drawable.account_about_us_toolbar),
                DividerDrawerItem(),
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
                        0 -> mainActivity.replaceFragment(AccountInformationFragment())
                        5 -> {
                            auth.signOut()
                            mainActivity.replaceActivity(AuthorizationRegistrationActivity())
                        }
                    }
                    return false
                }
            }).build()
    }
}