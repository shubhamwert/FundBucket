package com.stechapps.myapplication.models

import com.google.gson.annotations.SerializedName

data class FunderApiModel(
    @SerializedName("name")
     var name:String,
    @SerializedName("account")
     var acc:String,
    @SerializedName("response")
     val response: Boolean
)