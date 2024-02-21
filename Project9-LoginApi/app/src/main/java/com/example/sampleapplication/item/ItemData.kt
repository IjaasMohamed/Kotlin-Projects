package com.example.sampleapplication.item

data class ItemData(
    val Message: String,
    val Response_Body: List<ResponseBody>,
    val Status_Code: Int,
    val Sync_Time: String
)