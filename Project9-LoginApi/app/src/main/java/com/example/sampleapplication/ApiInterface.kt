package com.example.sampleapplication

import com.example.sampleapplication.invoice.ApiResponse
import com.example.sampleapplication.invoice.RequestData
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface ApiInterface {
    @POST("api/External_Api/Mobile_Api/Invoke")
    fun getData(@Body request: ApiRequest): Call<MyData>

    @POST("api/External_Api/Mobile_Api/Invoke")
    fun loginUser(@Body request: ApiRequest): Call<MyData>

    @POST("api/External_Api/Mobile_Api/Invoke")
    fun sendInvoiceData(@Body requestData: RequestData): Call<ApiResponse>
}