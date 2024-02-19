package com.example.sampleapplication.Login

import com.example.sampleapplication.Login.LoginRequest
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface ApiService {

    @POST("api/External_Api/Mobile_Api/Invoke")
    fun loginUser(@Body request: LoginRequest): Call<LoginResponse>
}