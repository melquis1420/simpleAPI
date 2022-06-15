package com.example.api.model

import com.google.gson.annotations.SerializedName

data class User(

    @SerializedName("id")
    var id: Int,
    @SerializedName("email")
    var email: String,

    @SerializedName("first_name")
    var firstName: String,

    @SerializedName("last_name")
    var lastName: Int,

    @SerializedName("avatar")
    var avatar: String
)