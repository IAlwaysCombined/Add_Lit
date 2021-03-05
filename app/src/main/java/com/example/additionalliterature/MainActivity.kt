package com.example.additionalliterature

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.additionalliterature.activities.AuthorizationRegistrationActivity
import com.example.additionalliterature.databinding.ActivityMainBinding
import com.example.additionalliterature.ui.fragments.ScrollNewsFragment
import com.example.additionalliterature.ui.objects.AppDrawerAdmin
import com.example.additionalliterature.ui.objects.AppDrawerUser
import com.example.additionalliterature.utilits.replaceActivity
import com.example.additionalliterature.utilits.replaceFragment
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*

class MainActivity : AppCompatActivity() {

    private lateinit var mBinding: ActivityMainBinding
    private lateinit var mToolbar: androidx.appcompat.widget.Toolbar
    private lateinit var mAuth: FirebaseAuth
    lateinit var mAppDrawerUser: AppDrawerUser
    lateinit var mAppDrawerAdmin: AppDrawerAdmin
    private lateinit var databaseReference: DatabaseReference
    private lateinit var database: FirebaseDatabase


    override fun onCreate(savedInstanceState: Bundle?) {
        mBinding = ActivityMainBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(mBinding.root)
    }

    override fun onStart() {
        super.onStart()
        initFields()
        initFunc()
    }

    private fun initFields() {
        mAuth = FirebaseAuth.getInstance()
        mToolbar = mBinding.mainToolbarUsers
        mAppDrawerUser = AppDrawerUser(this, mToolbar, mAuth)
        mAppDrawerAdmin = AppDrawerAdmin(this, mToolbar, mAuth)
        database = FirebaseDatabase.getInstance()
        databaseReference = database.reference.child("Users")
    }

    private fun initFunc() {
        val currentUser = mAuth.currentUser
        if (currentUser != null) {
            val user = mAuth.currentUser
            val unreferenced = databaseReference.child(user?.uid!!)
            unreferenced.addValueEventListener(object : ValueEventListener {
                override fun onDataChange(snapshot: DataSnapshot) {
                    val roleUser = snapshot.child("role").value.toString()
                    if (roleUser == "admin") {
                        replaceFragment(ScrollNewsFragment())
                        mAppDrawerAdmin.create()
                    } else {
                        replaceFragment(ScrollNewsFragment())
                        mAppDrawerUser.create()
                    }
                }
                override fun onCancelled(error: DatabaseError) {
                }
            })
        } else {
            replaceActivity(AuthorizationRegistrationActivity())
        }
    }
}