package com.example.sampleapplication

import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface ApiInterface {
    @POST("api/External_Api/Mobile_Api/Invoke")
    fun getData(@Body request: ApiRequest): Call<MyData>
}