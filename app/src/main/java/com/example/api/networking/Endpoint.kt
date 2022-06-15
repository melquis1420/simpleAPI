package com.example.api.networking

import  com.example.api.model.PageUser
import retrofit2.Call
import retrofit2.http.GET

interface Endpoint {

    @GET("api/user")
    fun getUsers() : Call<PageUser>
}