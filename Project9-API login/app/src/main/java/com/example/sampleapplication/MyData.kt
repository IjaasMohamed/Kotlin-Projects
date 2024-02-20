package com.example.sampleapplication

data class MyData(
    val Message: String,
    val Response_Body: List<ResponseBody>,
    val Status_Code: Int,
    val Sync_Time: String
)