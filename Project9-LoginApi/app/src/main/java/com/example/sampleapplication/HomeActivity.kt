package com.example.sampleapplication


import DatabaseHelper
import android.content.Intent
import android.os.Bundle
import android.os.Environment
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.sampleapplication.databinding.ActivityHomeBinding
import com.example.sampleapplication.invoice.InvoiceActivity
import com.itextpdf.text.Document
import com.itextpdf.text.Paragraph
import com.itextpdf.text.pdf.PdfWriter
import java.io.File
import java.io.FileOutputStream
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
        binding.btnProducts.setOnClickListener {
            startActivity(Intent(this, ProductActivity::class.java))
        }

        binding.btnGenerateReport.setOnClickListener {
            try {
                val pdfFile =
                    File(Environment.getExternalStorageDirectory(), "employee_details.pdf")
                if (!pdfFile.exists()) {
                    pdfFile.createNewFile()
                }
                val document = Document()
                PdfWriter.getInstance(document, FileOutputStream(pdfFile))
                document.open()

                // Add meta-data to the document
                document.addAuthor("Sample App")
                document.addTitle("Employee Details")

                // Fetch the data from the database
                val employees = db.getAllEmployees()

                // Loop through the employees and add them to the PDF
                for (employee in employees) {
                    document.add(Paragraph("Name: ${employee.name}"))
                    document.add(Paragraph("Email: ${employee.email}"))
                    document.add(Paragraph("Address: ${employee.address}"))
                    document.add(Paragraph("Phone: ${employee.phone}"))
                    document.add(Paragraph("------------------------------"))
                }

                document.close()

                Toast.makeText(
                    this,
                    "PDF Generated successfully! Saved at ${pdfFile.path}",
                    Toast.LENGTH_LONG
                ).show()
            } catch (e: Exception) {
                e.printStackTrace()
                Toast.makeText(this, "Error generating PDF", Toast.LENGTH_SHORT).show()
            }
        }

        binding.btnInvoice.setOnClickListener {
            startActivity(Intent(this, InvoiceActivity::class.java))
        }

        binding.btnItems.setOnClickListener {
            startActivity(Intent(this, InvoiceActivity::class.java))
        }


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
