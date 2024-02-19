package com.example.sampleapplication


import DatabaseHelper
import android.content.Intent
import android.os.Bundle
import android.os.Environment
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.sampleapplication.databinding.ActivityHomeBinding
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
            val databaseHelper = DatabaseHelper(this)
            // Check if the external storage is writable
            if (Environment.getExternalStorageState() == Environment.MEDIA_MOUNTED) {
                val pdfPath = databaseHelper.generatePdf()
                Toast.makeText(this, "PDF generated at: $pdfPath", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(this, "External storage is not writable", Toast.LENGTH_SHORT).show()
            }
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
