package com.example.sampleapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class HomeActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        val createEmployeeButton = findViewById<Button>(R.id.btn_create_employee)
        createEmployeeButton.setOnClickListener {
            startActivity(Intent(this, EmployeeActivity::class.java))
        }
    }
}
