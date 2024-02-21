package com.example.sampleapplication

//data class MyData(
//    val Message: String,
//    val Response_Body: List<ResponseBody>,
//    val Status_Code: Int,
//    val Sync_Time: String
//)

data class MyDataLogin(
    val Message: String,
    val Response_Body: List<ResponseBodyLogin>?,
    val Status_Code: Int,
    val Sync_Time: String
)