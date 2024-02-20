package com.example.sampleapplication.invoice

data class RequestData(
    val API_Body: List<RequestBody>,
    val Api_Action: String,
    val Company_Code: String,
    val Device_Id: String
)

data class RequestBody(
    val Customer_Code: String,
    val Doc_Date: String,
    val Doc_No: String,
    val Doc_Time: String,
    val Employee_Code: String,
    val Item_Details: List<ItemDetail>,
    val Latitude: String,
    val Location_Code: String,
    val Longitude: String,
    val Net_Amount: String,
    val Plan_Id: String,
    val Setup_Employee_Code: String,
    val Trip_Id: String,
    val Type_Code: String
)

data class ItemDetail(
    val Amount: String,
    val Discount: String,
    val Free_Qty: String,
    val Item_Code: String,
    val Price: String,
    val Qty: String
)
