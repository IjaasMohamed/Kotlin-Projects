package com.example.sampleapplication

import DatabaseHelper
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.sampleapplication.databinding.ActivityRegisterBinding

class RegisterActivity : AppCompatActivity() {
    private lateinit var binding: ActivityRegisterBinding
    private lateinit var db: DatabaseHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Initialize the DatabaseHelper
        db = DatabaseHelper(this)

        binding.btnRegister.setOnClickListener {
            registerUser()
        }
    }

    private fun registerUser() {
        val username = binding.textInputUsername.text.toString()
        val email = binding.textInputEmail.text.toString()
        val password = binding.textInputPassword.text.toString()

        if (ValidationUtils.isTextNotEmpty(username) &&
            ValidationUtils.isValidEmail(email) &&
            ValidationUtils.isTextNotEmpty(password)
        ) {
            val user = User(username = username, email = email.trim(), password = password)
            db.registerUser(user)
            Toast.makeText(this, "User Registered", Toast.LENGTH_LONG).show()
        } else {
            Toast.makeText(this, "Please enter all fields or correct email format", Toast.LENGTH_SHORT).show()
        }
    }
}
