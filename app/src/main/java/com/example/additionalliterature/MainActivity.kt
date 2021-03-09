package com.example.additionalliterature

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import com.example.additionalliterature.activities.AuthorizationRegistrationActivity
import com.example.additionalliterature.databinding.ActivityMainBinding
import com.example.additionalliterature.ui.fragments.ListNewsFragment
import com.example.additionalliterature.ui.objects.AppDrawerAdmin
import com.example.additionalliterature.ui.objects.AppDrawerUser
import com.example.additionalliterature.utilits.AUTH
import com.example.additionalliterature.utilits.initFirebase
import com.example.additionalliterature.utilits.replaceActivity
import com.example.additionalliterature.utilits.replaceFragment
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*

class MainActivity : AppCompatActivity() {

    private lateinit var mAuth: FirebaseAuth
    private lateinit var mBinding: ActivityMainBinding
    private lateinit var mToolbar: Toolbar
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
        initFirebase()
    }

    private fun initFunc() {
        if (AUTH.currentUser != null) {
            val user = mAuth.currentUser
            val unreferenced = databaseReference.child(user?.uid!!)
            unreferenced.addValueEventListener(object : ValueEventListener {
                override fun onDataChange(snapshot: DataSnapshot) {
                    val roleUser = snapshot.child("role").value.toString()
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
}