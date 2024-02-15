package com.example.sampleapplication

import android.content.Intent
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.pdf.PdfDocument
import android.os.Bundle
import android.os.Environment
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.sampleapplication.databinding.ActivityHomeBinding
import java.io.File
import java.io.FileOutputStream
import java.io.IOException
import java.io.Serializable

class HomeActivity : AppCompatActivity(), EmployeeAdapter.EmployeeAdapterListener {

    private lateinit var binding: ActivityHomeBinding
    private lateinit var db: DatabaseHelper
    private lateinit var adapter: EmployeeAdapter
    private lateinit var employeeList: MutableList<Employee>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        db = DatabaseHelper(this)

        employeeList = db.getAllEmployees().toMutableList()
        adapter = EmployeeAdapter(employeeList, this)
        binding.recyclerView.adapter = adapter
        binding.recyclerView.layoutManager = LinearLayoutManager(this)

        binding.btnCreateEmployee.setOnClickListener {
            startActivity(Intent(this, EmployeeActivity::class.java))
        }

        binding.btnGenerateReport.setOnClickListener {
            generateReport()
        }
    }

    private fun generateReport() {
        val pdfDocument = PdfDocument()

        // Create a new page description
        val pageInfo = PdfDocument.PageInfo.Builder(500, 500, 1).create()

        // Start a page
        val page = pdfDocument.startPage(pageInfo)

        // Draw text on the page
        val canvas = page.canvas
        val paint = Paint()
        paint.color = Color.BLACK
        var y = 20f
        for (employee in employeeList) {
            canvas.drawText(employee.name, 20f, y, paint)
            canvas.drawText(employee.email, 200f, y, paint)
            y += 20f
        }

        // Finish the page
        pdfDocument.finishPage(page)

        // Write the document content to a file
        val file = File(Environment.getExternalStorageDirectory(), "employee_report.pdf")
        file.parentFile?.mkdirs()
        try {
            pdfDocument.writeTo(FileOutputStream(file))
            showToast("PDF report generated successfully")
        } catch (e: IOException) {
            e.printStackTrace()
            showToast("Failed to generate PDF report")
        }

        // Close the document
        pdfDocument.close()
    }

    private fun showToast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }

    override fun onEditClick(employee: Employee) {
        val intent = Intent(this, EmployeeActivity::class.java)
        intent.putExtra("employee", employee as Serializable)
        startActivity(intent)
    }

    override fun onDeleteClick(employee: Employee) {
        employee.id?.let { db.deleteEmployee(it) }
        employeeList.remove(employee)
        adapter.notifyDataSetChanged()
    }
}
