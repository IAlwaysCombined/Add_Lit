package com.example.additionalliterature.models

data class CommonModel (
    var uid: String = "",
    var email: String = "",
    val course: String = "",
    val name: String = "",
    val role: String = "",
    var photoUrl: String = "",

    val error: String ="",


    val newsTitle: String = "",
    val newsText: String = "",
    val newsImageUrl: String = "",
    val newsUrl: String = ""
)