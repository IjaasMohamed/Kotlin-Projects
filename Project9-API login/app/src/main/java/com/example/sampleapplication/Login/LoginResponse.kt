package com.example.sampleapplication.Login

data class LoginResponse(
    val Status_Code: Int,
    val Sync_Time: String,
    val Message: String,
    val Response_Body: List<UserData>
)

data class UserData(
    val User_Code: String,
    val User_Display_Name: String,
    val Email: String,
    val User_Employee_Code: String,
    val Company_Code: String,
    val User_Locations: List<Location>,
    val User_Permissions: List<Permission>
)

data class Location(
    val Location_Code: String
)

data class Permission(
    val Permisson_Name: String,
    val Permission_Status: String
)
