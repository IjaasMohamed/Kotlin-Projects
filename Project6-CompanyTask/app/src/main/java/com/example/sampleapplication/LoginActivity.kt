package com.example.sampleapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.sampleapplication.databinding.ActivityLoginBinding

class LoginActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding
    private lateinit var db: DatabaseHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)
        db = DatabaseHelper(this)

        binding.tvRegister.setOnClickListener {
            startActivity(Intent(this, RegisterActivity::class.java))
        }

        binding.btnLogin.setOnClickListener() {
            loginUser()
        }
    }

    private fun loginUser() {
        val email = binding.textInputEmail.text.toString().trim()
        val password = binding.textInputPassword.text.toString().trim()

        if (ValidationUtils.isTextNotEmpty(email) &&
            ValidationUtils.isTextNotEmpty(password)) {
            if (ValidationUtils.isValidEmail(email)) {
                val isSuccess = db.loginUser(email, password)
                if (isSuccess) {
                    Toast.makeText(this, "You are Logged in!", Toast.LENGTH_SHORT).show()
                }
            } else {
                Toast.makeText(this, "Invalid Email ...!", Toast.LENGTH_SHORT).show()
            }
        } else {
            Toast.makeText(this, "Please enter all fields...!", Toast.LENGTH_SHORT).show()
        }
    }
}
