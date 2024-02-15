package com.example.sampleapplication


import android.content.Intent
import android.os.Bundle
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
