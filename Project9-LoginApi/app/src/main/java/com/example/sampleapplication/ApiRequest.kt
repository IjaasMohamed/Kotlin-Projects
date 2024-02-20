package com.example.sampleapplication

data class ApiRequest (
    val API_Body: List<RequestBodyItemLogin>,
    val Api_Action:String,
    val Sync_Time:String,
    val Company_Code:String
)

