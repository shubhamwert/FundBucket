package com.stechapps.myapplication.models


import com.google.gson.annotations.SerializedName

data class APIaddressrandom(
    @SerializedName("account")
    val account: String,
    @SerializedName("response")
    val response: Boolean,
    @SerializedName("name")
    val name:String
)