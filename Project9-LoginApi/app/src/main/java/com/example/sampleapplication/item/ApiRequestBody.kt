package com.example.sampleapplication.item

data class ApiRequestBody(
    val API_Body: List<Map<String, String>>,
    val Api_Action: String,
    val Sync_Time: String,
    val Company_Code: String
)