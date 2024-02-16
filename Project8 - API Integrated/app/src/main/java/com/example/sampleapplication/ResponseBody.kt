package com.example.sampleapplication

data class ResponseBody(
    val Barcode: String,
    val Code: String,
    val Item_Group: String,
    val Item_Group_Code: String,
    val Minus_Stock: Boolean,
    val Name: String,
    val Order_No: Double,
    val Pack_Size: String,
    val Price: Double,
    val Printing_Name: String,
    val Status: Int,
    val Stock_Handle: Boolean,
    val Sync_Time: String
)
