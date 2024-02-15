package com.example.sampleapplication

import java.io.Serializable

data class Employee(
    val id: Int? = null,
    val name: String,
    val email: String,
    val address: String,
    val phone: String
) : Serializable
