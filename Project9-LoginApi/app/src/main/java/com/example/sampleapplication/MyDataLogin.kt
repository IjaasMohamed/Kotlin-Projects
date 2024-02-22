package com.example.sampleapplication

data class MyDataLogin(
    val Message: String,
    val Response_Body: List<ResponseBodyLogin>?,
    val Status_Code: Int,
    val Sync_Time: String
)