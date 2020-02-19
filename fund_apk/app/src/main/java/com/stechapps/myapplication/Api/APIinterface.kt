package com.stechapps.myapplication.Api

import com.google.gson.JsonObject
import com.stechapps.myapplication.models.APIaddressrandom
import com.stechapps.myapplication.models.FunderApiModel
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface APIinterface {
    @GET("/checkconnectivity/")
    fun checkConnectivity(): Call<FunderApiModel>

    @GET("/getRandomAddress")
    fun getRandomAddress():Call<APIaddressrandom>

    @POST("/registerCampaign")
    fun RegisterCampaign(@Body body: JsonObject):Call<APIaddressrandom>

    @POST("/registerFunder")
    fun RegisterAsFunder(@Body body: JsonObject):Call<APIaddressrandom>

    @POST("/sendMoneyToFundSeeker")
    fun Donate(@Body body: JsonObject):Call<APIaddressrandom>
    companion object {
        fun create(): APIinterface {

            val retrofit = Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl("http://192.168.43.219:5000")
                .build()

            return retrofit.create(APIinterface::class.java)
        }
    }
}