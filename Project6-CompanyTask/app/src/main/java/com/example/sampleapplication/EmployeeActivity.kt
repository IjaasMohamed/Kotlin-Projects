package com.example.sampleapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.sampleapplication.databinding.ActivityEmployeeBinding

class EmployeeActivity : AppCompatActivity() {

    private lateinit var binding: ActivityEmployeeBinding
    private lateinit var db: DatabaseHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityEmployeeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Initialize DatabaseHelper
        db = DatabaseHelper(this)

        val nameEditText = binding.textInputName
        val emailEditText = binding.textInputEmail
        val addressEditText = binding.textInputAddress
        val phoneEditText = binding.textInputPhone
        val registerButton = binding.btnRegister

        registerButton.setOnClickListener {
            val name = nameEditText.text.toString().trim()
            val email = emailEditText.text.toString().trim()
            val address = addressEditText.text.toString().trim()
            val phone = phoneEditText.text.toString().trim()

            if (ValidationUtils.isTextNotEmpty(name) &&
                ValidationUtils.isValidEmail(email) &&
                ValidationUtils.isTextNotEmpty(address) &&
                ValidationUtils.isTextNotEmpty(phone)) {
                // Create Employee object
                val employee = Employee(name = name, email = email, address = address, phone = phone)
                // Register employee
                registerEmployee(employee)
            } else {
                // Show error message
                showToast("Please enter all fields with valid data")
            }
        }
    }

    private fun registerEmployee(employee: Employee) {
        // Register employee in the database
        db.registerEmployee(employee)
        showToast("Employee registered successfully")
        // You can add additional logic here, like navigating to another activity
    }

    private fun showToast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }

    private fun updateEmployee(employee: Employee) {
        db.updateEmployee(employee)
        showToast("Employee updated successfully")
        // You can add additional logic here if needed
    }
    private fun deleteEmployee(employeeId: Int) {
        db.deleteEmployee(employeeId)
        showToast("Employee deleted successfully")
        // You can add additional logic here if needed
    }


}
