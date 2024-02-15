package com.example.sampleapplication

import DatabaseHelper
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.sampleapplication.databinding.ActivityEmployeeBinding

class EmployeeActivity : AppCompatActivity() {
    private lateinit var binding: ActivityEmployeeBinding
    private lateinit var db: DatabaseHelper
    private var employeeId: Int? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityEmployeeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        db = DatabaseHelper(this)

        val nameEditText = binding.textInputName
        val emailEditText = binding.textInputEmail
        val addressEditText = binding.textInputAddress
        val phoneEditText = binding.textInputPhone
        val registerButton = binding.btnRegister

        val employee = intent.getSerializableExtra("employee") as? Employee
        if (employee != null) {
            nameEditText.setText(employee.name)
            emailEditText.setText(employee.email)
            addressEditText.setText(employee.address)
            phoneEditText.setText(employee.phone)
            employeeId = employee.id
        }

        registerButton.setOnClickListener {
            val name = nameEditText.text.toString().trim()
            val email = emailEditText.text.toString().trim()
            val address = addressEditText.text.toString().trim()
            val phone = phoneEditText.text.toString().trim()

            if (ValidationUtils.isTextNotEmpty(name) &&
                ValidationUtils.isValidEmail(email) &&
                ValidationUtils.isTextNotEmpty(address) &&
                ValidationUtils.isTextNotEmpty(phone)) {

                val employee = Employee(id = employeeId, name = name, email = email, address = address, phone = phone)
                if (employeeId == null) {
                    db.registerEmployee(employee)
                    showToast("Employee registered successfully")
                } else {
                    db.updateEmployee(employee)
                    showToast("Employee updated successfully")
                }

                startActivity(Intent(this, HomeActivity::class.java)
                    .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_NEW_TASK))
                finish()
            } else {
                showToast("Please enter all fields with valid data")
            }
        }
    }

    private fun showToast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }
}
