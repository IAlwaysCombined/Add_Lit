package com.example.additionalliterature

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.example.additionalliterature.activities.AuthorizationRegistrationActivity
import com.example.additionalliterature.databinding.ActivityMainBinding
import com.example.additionalliterature.ui.*
import com.example.additionalliterature.utilits.replaceActivity
import com.example.additionalliterature.utilits.replaceFragment
import com.google.firebase.auth.FirebaseAuth
import com.mikepenz.materialdrawer.Drawer
import com.mikepenz.materialdrawer.DrawerBuilder
import com.mikepenz.materialdrawer.model.PrimaryDrawerItem
import com.mikepenz.materialdrawer.model.interfaces.IDrawerItem


class MainActivity : AppCompatActivity() {

    private lateinit var mBinding: ActivityMainBinding
    private lateinit var mDrawer: Drawer
    private lateinit var mToolbar: androidx.appcompat.widget.Toolbar
    private lateinit var mAuth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        mBinding = ActivityMainBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(mBinding.root)
    }

    override fun onStart() {
        super.onStart()
        initFunc()
        initFields()
    }

    private fun initFunc() {
        mAuth = FirebaseAuth.getInstance()
        val currentUser = mAuth.currentUser
        if (currentUser != null) {
            replaceFragment(ScrollNewsFragment())
        } else {
            replaceActivity(AuthorizationRegistrationActivity())
        }
    }

    private fun initFields() {
        mToolbar = mBinding.mainToolbar
        createDrawer()
    }

    private fun createDrawer() {
        mDrawer = DrawerBuilder()
            .withActivity(this)
            .withToolbar(mToolbar)
            .withActionBarDrawerToggle(true)
            .withSelectedItem(-1)
            .addDrawerItems(
                PrimaryDrawerItem().withIdentifier(100)
                    .withIconTintingEnabled(true)
                    .withName("Информация об аккаунте!")
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
                        0 -> replaceFragment(AccountInformationFragment())
                        1 -> replaceFragment(SendNewsFragment())
                        2 -> replaceFragment(SendErrorFragment())
                        3 -> replaceFragment(AboutUsFragment())
                        4 -> replaceActivity(AuthorizationRegistrationActivity())
                    }
                    return false
                }
            }).build()
    }
}