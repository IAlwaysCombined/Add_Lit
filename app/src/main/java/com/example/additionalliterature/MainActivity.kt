package com.example.additionalliterature

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import com.example.additionalliterature.activities.AuthorizationRegistrationActivity
import com.example.additionalliterature.databinding.ActivityMainBinding
import com.example.additionalliterature.models.Users
import com.example.additionalliterature.ui.fragments.user.ListNewsFragment
import com.example.additionalliterature.ui.objects.AppDrawerAdmin
import com.example.additionalliterature.ui.objects.AppDrawerUser
import com.example.additionalliterature.utilits.*
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener


class MainActivity : AppCompatActivity() {

    private lateinit var mBinding: ActivityMainBinding
    private lateinit var mToolbar: Toolbar
    lateinit var mAppDrawerUser: AppDrawerUser
    lateinit var mAppDrawerAdmin: AppDrawerAdmin


    override fun onCreate(savedInstanceState: Bundle?) {
        mBinding = ActivityMainBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        APP_ACTIVITY = this
        setContentView(mBinding.root)
    }

    override fun onStart() {
        super.onStart()
        initFields()
        initFunc()
        initUser()
    }

    private fun initFields() {
        mToolbar = mBinding.mainToolbarUsers
        mAppDrawerUser = AppDrawerUser(this, mToolbar)
        mAppDrawerAdmin = AppDrawerAdmin(this, mToolbar)
        initFirebase()
    }

    private fun initFunc() {
        if (AUTH.currentUser != null) {
            REF_DATABASE_ROOT.child(NODE_USERS).child(UID)
                .addValueEventListener(object: ValueEventListener{
                    override fun onDataChange(snapshot: DataSnapshot) {
                        val roleUser = snapshot.child(CHILD_ROLE).value.toString()
                        if (roleUser == getString(R.string.admin)) {
                            setSupportActionBar(mToolbar)
                            mAppDrawerAdmin.create()
                            replaceFragment(ListNewsFragment())
                        } else {
                            setSupportActionBar(mToolbar)
                            mAppDrawerUser.create()
                            replaceFragment(ListNewsFragment())
                        }
                    }
                    override fun onCancelled(error: DatabaseError) {
                    }
                })
        } else {
            replaceActivity(AuthorizationRegistrationActivity())
        }
    }

    //Initial Users
    private fun initUser() {
        REF_DATABASE_ROOT.child(NODE_USERS).child(UID)
            .addListenerForSingleValueEvent(AppValueEventListener {
                USER = it.getValue(Users::class.java) ?: Users()
            })
    }
}