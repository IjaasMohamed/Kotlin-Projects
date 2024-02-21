package com.example.sampleapplication

import com.example.sampleapplication.invoice.ApiResponse
import com.example.sampleapplication.invoice.RequestData
import com.example.sampleapplication.item.ApiRequestBody
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface ApiInterface {
    @POST("api/External_Api/Mobile_Api/Invoke")
    fun getData(@Body request: ApiRequest): Call<MyDataLogin>
    @POST("api/External_Api/Mobile_Api/Invoke")
    suspend fun getItemsData(@Body requestBody: ApiRequestBody): ApiResponse
    @POST("api/External_Api/Mobile_Api/Invoke")
    fun loginUser(@Body request: ApiRequest): Call<MyDataLogin>

    @POST("api/External_Api/Mobile_Api/Invoke")
    fun sendInvoiceData(@Body requestData: RequestData): Call<ApiResponse>
}