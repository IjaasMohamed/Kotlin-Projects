package com.example.sampleapplication

import DatabaseHelper
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.example.sampleapplication.databinding.ActivityLoginBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

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
//        binding.btnLogin.setOnClickListener {
//            val email = binding.textInputEmail.text.toString().trim()
//            val password = binding.textInputPassword.text.toString().trim()
//
//            if (ValidationUtils.isTextNotEmpty(email) &&
//                ValidationUtils.isValidEmail(email) &&
//                ValidationUtils.isTextNotEmpty(password)) {
//
//                val apiInterface = RetrofitClient.create()
//                val request = ApiRequest(
//                    API_Body = listOf(RequestBodyItemLogin(Unique_Id = email, Pw = password)),
//                    Api_Action = "GetUserData",
//                    Sync_Time = "", // Assuming Sync_Time is not required for login
//                    Company_Code = email
//                )
//
//                apiInterface.loginUser(request).enqueue(object : Callback<MyDataLogin> {
//                    override fun onResponse(call: Call<MyDataLogin>, response: Response<MyDataLogin>) {
//                        if (response.isSuccessful) {
//                            // Handle successful login
//                            Toast.makeText(this@LoginActivity, "Logged in successfully!", Toast.LENGTH_SHORT).show()
//                            navigateToHomeActivity()
//                        } else {
//                            // Handle unsuccessful login
//                            Toast.makeText(this@LoginActivity, "Login failed", Toast.LENGTH_SHORT).show()
//                        }
//                    }
//
//                    override fun onFailure(call: Call<MyDataLogin>, t: Throwable) {
//                        // Handle network error
//                        Toast.makeText(this@LoginActivity, "Network error: ${t.localizedMessage}", Toast.LENGTH_SHORT).show()
//                    }
//                })
//            } else {
//                Toast.makeText(this, "Please enter all fields or correct email format", Toast.LENGTH_SHORT).show()
//            }
//        }
//
//    }

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


//    private fun loginUser() {
//        val email = binding.textInputEmail.text.toString().trim()
//        val password = binding.textInputPassword.text.toString().trim()
//
//        if (ValidationUtils.isTextNotEmpty(email) &&
//            ValidationUtils.isValidEmail(email) &&
//            ValidationUtils.isTextNotEmpty(password)) {
//
//            val apiInterface = RetrofitClient.create()
//            val request = ApiRequest(
//                API_Body = listOf(RequestBodyItemLogin(Unique_Id = email, Pw = password)),
//                Api_Action = "GetUserData",
//                Sync_Time = "", // Assuming Sync_Time is not required for login
//                Company_Code = email
//            )
//
//            apiInterface.loginUser(request).enqueue(object : Callback<MyData> {
//                override fun onResponse(call: Call<MyData>, response: Response<MyData>) {
//                    if (response.isSuccessful) {
//                        val myData = response.body()
//                        Log.d("API Response", "Response Body: $myData")
//                        if (myData?.Status_Code == 200 ) {
//                            // Handle successful login
//                            Toast.makeText(this@LoginActivity, "Logged in successfully!", Toast.LENGTH_SHORT).show()
//                            navigateToHomeActivity()
//                        } else {
//                            // Handle unsuccessful login
//                            Toast.makeText(this@LoginActivity, "Login failed", Toast.LENGTH_SHORT).show()
//                        }
//                    } else {
//                        // Handle unsuccessful HTTP response
//                        Toast.makeText(this@LoginActivity, "Request failed with status code: ${response.code()}", Toast.LENGTH_SHORT).show()
//                    }
//                }
//
//                override fun onFailure(call: Call<MyData>, t: Throwable) {
//                    // Handle network error
//                    Toast.makeText(this@LoginActivity, "Network error: ${t.localizedMessage}", Toast.LENGTH_SHORT).show()
//                }
//            })
//        } else {
//            Toast.makeText(this, "Please enter all fields or correct email format", Toast.LENGTH_SHORT).show()
//        }
//    }


//    fun loginUser() {
//        val email = binding.textInputEmail.text.toString().trim()
//        val password = binding.textInputPassword.text.toString().trim()
//
//        if (ValidationUtils.isTextNotEmpty(email) &&
//            ValidationUtils.isValidEmail(email) &&
//            ValidationUtils.isTextNotEmpty(password)) {
//
//            val apiInterface = RetrofitClient.create()
//            val request = ApiRequest(
//                API_Body = listOf(RequestBodyItemLogin(Unique_Id = email, Pw = password)),
//                Api_Action = "GetUserData",
//                Sync_Time = "", // Assuming Sync_Time is not required for login
//                Company_Code = email
//            )
//
//            apiInterface.loginUser(request).enqueue(object : Callback<MyDataLogin> {
//                override fun onResponse(call: Call<MyDataLogin>, response: Response<MyDataLogin>) {
//                    if (response.isSuccessful) {
//                        val myData = response.body()
//                        Log.d("API Response", "Response Body: $myData")
//                        if (myData?.Status_Code != 401 && myData?.Response_Body != null && myData.Response_Body[0].Doc_Msg != "Invalid Password") {
//                            // Handle successful login
//                            Toast.makeText(this@LoginActivity, "Logged in successfully!", Toast.LENGTH_SHORT).show()
//                            navigateToHomeActivity()
//                        } else {
//                            // Handle unsuccessful login
//                            Toast.makeText(this@LoginActivity, "Login failed", Toast.LENGTH_SHORT).show()
//                        }
//                    } else {
//                        // Handle unsuccessful HTTP response
//                        Toast.makeText(this@LoginActivity, "Request failed with status code: ${response.code()}", Toast.LENGTH_SHORT).show()
//                    }
//                }
//
//                override fun onFailure(call: Call<MyDataLogin>, t: Throwable) {
//                    // Handle network error
//                    Toast.makeText(this@LoginActivity, "Network error: ${t.localizedMessage}", Toast.LENGTH_SHORT).show()
//                }
//            })
//        } else {
//            Toast.makeText(this, "Please enter all fields or correct email format", Toast.LENGTH_SHORT).show()
//        }
//    }
//
//    private fun navigateToHomeActivity() {
//        val intent = Intent(this, HomeActivity::class.java)
//        startActivity(intent)
//        finish()
//    }
//}


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

        binding.btnLogin.setOnClickListener {
            loginUser()
        }
    }

    private fun loginUser() {
        val email = binding.textInputEmail.text.toString().trim()
        val password = binding.textInputPassword.text.toString().trim()

        if (ValidationUtils.isTextNotEmpty(email) &&
            ValidationUtils.isValidEmail(email) &&
            ValidationUtils.isTextNotEmpty(password)) {

            val apiInterface = RetrofitClient.create()
            val request = ApiRequest(
                API_Body = listOf(RequestBodyItemLogin(Unique_Id = email, Pw = password)),
                Api_Action = "GetUserData",
                Sync_Time = "", // Assuming Sync_Time is not required for login
                Company_Code = email
            )

            apiInterface.loginUser(request).enqueue(object : Callback<MyDataLogin> {
                override fun onResponse(call: Call<MyDataLogin>, response: Response<MyDataLogin>) {
                    val myData = response.body()
                    Log.d("API Response", "Response Body: $myData")
                    if (response.isSuccessful && myData?.Status_Code != 401 && myData?.Response_Body != null && myData.Response_Body[0].Doc_Msg != "Invalid Password") {
                        // Handle successful login
                        Toast.makeText(this@LoginActivity, "Logged in successfully!", Toast.LENGTH_SHORT).show()
                        navigateToHomeActivity()
                    } else {
                        // Handle unsuccessful login
                        Toast.makeText(this@LoginActivity, "Login failed", Toast.LENGTH_SHORT).show()
                    }
                }

                override fun onFailure(call: Call<MyDataLogin>, t: Throwable) {
                    // Handle network error
                    Toast.makeText(this@LoginActivity, "Network error: ${t.localizedMessage}", Toast.LENGTH_SHORT).show()
                }
            })
        } else {
            Toast.makeText(this, "Please enter all fields or correct email format", Toast.LENGTH_SHORT).show()
        }
    }

    private fun navigateToHomeActivity() {
        val intent = Intent(this, HomeActivity::class.java)
        startActivity(intent)
        finish()
    }
}