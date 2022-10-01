package com.example.krishiapp.network

import com.example.krishiapp.domain.User
import retrofit2.Call
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

interface ApiService {
    companion object{
        val BASE_URL="http://127.0.0.1/login/"
    }
    @FormUrlEncoded
    @POST("signup.php")
    fun setDetail(
        @Field("phone") phone:String,
        @Field("email")email:String,
        @Field("password")password:String,
    ): Call<User>

    @FormUrlEncoded
    @POST("signin.php")
    fun setLoginDetail(
        @Field("email")email:String,
        @Field("password")password:String,
    ): Call<User>
}