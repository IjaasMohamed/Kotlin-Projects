package com.example.sampleapplication.invoice

data class ApiResponse(
    val Status_Code: Int,
    val Sync_Time: String,
    val Message: String,
    val Response_Body: String
)
