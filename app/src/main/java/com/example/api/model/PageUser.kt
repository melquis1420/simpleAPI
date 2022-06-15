package com.example.api.model

import com.google.gson.annotations.SerializedName

data class PageUser(
    @SerializedName("page")
    var page: Int,
    @SerializedName("per_page")
    var parPage: Int,

    @SerializedName("total")
    var total: Int,

    @SerializedName("total_pages")
    var totalPages: Int,

    @SerializedName("data")
    var data: List<User>
)

