package com.example.additionalliterature.utilits

import com.example.additionalliterature.models.CommonModel
import com.example.additionalliterature.models.Users
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase


lateinit var AUTH: FirebaseAuth
lateinit var UID: String
lateinit var REF_DATABASE_ROOT: DatabaseReference
lateinit var USER:Users
lateinit var EMAIL: String

//Node realtime database
const val NODE_USERS = "users"
const val NODE_ERROR = "error"
const val NODE_NEWS = "news"
const val NODE_NEWS_REQUEST = "news_request"


const val CHILD_UID = "uid"
const val CHILD_PHONE = "phone"
const val CHILD_USERNAME = "username"

const val CHILD_COURSE = "course"
const val CHILD_NAME = "name"
const val CHILD_ROLE = "role"
const val CHILD_EMAIL = "email"
const val CHILD_ERROR = "error"


//News const
const val CHILD_NEWS_TEXT = "newsText"
const val CHILD_NEWS_TITLE = "newsTitle"
const val CHILD_NEWS_IMAGE_URL = "newsImageUrl"
const val CHILD_NEWS_URL = "newsUrl"

//Role const
const val USER_ROLE = "user"
const val ADMIN_ROLE = "admin"


fun initFirebase() {
    AUTH = FirebaseAuth.getInstance()
    REF_DATABASE_ROOT = FirebaseDatabase.getInstance().reference
    USER = Users()
    UID = AUTH.currentUser?.uid.toString()
    EMAIL = AUTH.currentUser?.email.toString()
}

fun DataSnapshot.getUsers(): Users =
    this.getValue(Users::class.java) ?: Users()

fun DataSnapshot.getCommonModel(): CommonModel =
    this.getValue(CommonModel::class.java) ?: CommonModel()
