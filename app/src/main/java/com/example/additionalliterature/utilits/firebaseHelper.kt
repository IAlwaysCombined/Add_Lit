package com.example.additionalliterature.utilits

import com.example.additionalliterature.models.Users
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase


lateinit var AUTH: FirebaseAuth
lateinit var UID: String
lateinit var REF_DATABASE_ROOT: DatabaseReference
lateinit var USER:Users
lateinit var EMAIL: String

const val NODE_USERS = "Users"
const val CHILD_ID = "id"
const val CHILD_COURSE = "course"
const val CHILD_NAME = "name"
const val CHILD_ROLE = "role"

fun initFirebase() {
    AUTH = FirebaseAuth.getInstance()
    REF_DATABASE_ROOT = FirebaseDatabase.getInstance().reference
    USER = Users()
    UID = AUTH.currentUser?.uid.toString()
    EMAIL = AUTH.currentUser?.email.toString()
}