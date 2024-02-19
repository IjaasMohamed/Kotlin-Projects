package com.example.sampleapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.sampleapplication.databinding.ActivityLoginBinding

//class LoginActivity : AppCompatActivity() {
//
//    private lateinit var binding: ActivityLoginBinding
//    private lateinit var db: DatabaseHelper
//
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        binding = ActivityLoginBinding.inflate(layoutInflater)
//        setContentView(binding.root)
//        db = DatabaseHelper(this)
//
//        binding.tvRegister.setOnClickListener {
//            startActivity(Intent(this, RegisterActivity::class.java))
//        }
//
//        binding.btnLogin.setOnClickListener() {
//            loginUser()
//        }
//    }
//
//    private fun loginUser() {
//        val email = binding.textInputEmail.text.toString().trim()
//        val password = binding.textInputPassword.text.toString().trim()
//
//        if (ValidationUtils.isTextNotEmpty(email) &&
//            ValidationUtils.isTextNotEmpty(password)) {
//            if (ValidationUtils.isValidEmail(email)) {
//                val isSuccess = db.loginUser(email, password)
//                if (isSuccess) {
//                    Toast.makeText(this, "You are Logged in!", Toast.LENGTH_SHORT).show()
//                    navigateToHomeActivity() // Call the navigateToHomeActivity function
//                }
//            } else {
//                Toast.makeText(this, "Invalid Email ...!", Toast.LENGTH_SHORT).show()
//            }
//        } else {
//            Toast.makeText(this, "Please enter all fields...!", Toast.LENGTH_SHORT).show()
//        }
//    }
//
//    private fun navigateToHomeActivity() {
//        val intent = Intent(this, HomeActivity::class.java)
//        startActivity(intent)
//        finish()
//    }
//}

import com.example.sampleapplication.Login.ApiService
import com.example.sampleapplication.Login.LoginRequest
import com.example.sampleapplication.Login.LoginRequestBody
import com.example.sampleapplication.Login.LoginResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LoginActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding
    private lateinit var apiService: ApiService

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)
        apiService = RetrofitClient.createService(ApiService::class.java)

        binding.tvRegister.setOnClickListener {
            startActivity(Intent(this, RegisterActivity::class.java))
        }

        binding.btnLogin.setOnClickListener {
            loginUser()
        }
    }

    private fun loginUser() {
        val email = binding.textInputEmail.text.toString().trim()
        val password = binding.textInputPassword.text.toString().trim()

        if (ValidationUtils.isTextNotEmpty(email) && ValidationUtils.isTextNotEmpty(password)) {
            if (ValidationUtils.isValidEmail(email)) {
                val loginRequest = LoginRequest(
                    listOf(LoginRequestBody("05e0e2342000d4594f6", password)),
                    "GetUserData",
                    email
                )

                apiService.loginUser(loginRequest).enqueue(object : Callback<LoginResponse> {
                    override fun onResponse(
                        call: Call<LoginResponse>,
                        response: Response<LoginResponse>
                    ) {
                        if (response.isSuccessful) {
                            val loginResponse = response.body()
                            if (loginResponse != null && loginResponse.Status_Code == 200) {
                                Toast.makeText(
                                    this@LoginActivity,
                                    "You are Logged in!",
                                    Toast.LENGTH_SHORT
                                ).show()
                                navigateToHomeActivity() // Call the navigateToHomeActivity function
                            } else {
                                Toast.makeText(
                                    this@LoginActivity,
                                    "Login failed: ${loginResponse?.Message}",
                                    Toast.LENGTH_SHORT
                                ).show()
                            }
                        } else {
                            Toast.makeText(
                                this@LoginActivity,
                                "Failed to login: ${response.message()}",
                                Toast.LENGTH_SHORT
                            ).show()
                        }
                    }

                    override fun onFailure(call: Call<LoginResponse>, t: Throwable) {
                        Toast.makeText(
                            this@LoginActivity,
                            "Network error: ${t.message}",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                })
            } else {
                Toast.makeText(this, "Invalid Email ...!", Toast.LENGTH_SHORT).show()
            }
        } else {
            Toast.makeText(this, "Please enter all fields...!", Toast.LENGTH_SHORT).show()
        }
    }

    private fun navigateToHomeActivity() {
        val intent = Intent(this, HomeActivity::class.java)
        startActivity(intent)
        finish()
    }
}

private fun <T> Call<T>.enqueue(callback: Callback<LoginResponse>) {

}

