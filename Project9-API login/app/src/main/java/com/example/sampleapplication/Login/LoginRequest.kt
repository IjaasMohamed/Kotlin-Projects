package com.example.sampleapplication.Login

data class LoginRequest(
    val API_Body: List<LoginRequestBody>,
    val Api_Action: String,
    val Company_Code: String
)

data class LoginRequestBody(
    val Unique_Id: String,
    val Pw: String
)
