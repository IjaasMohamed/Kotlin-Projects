package com.example.sampleapplication

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.sampleapplication.Employee
import com.example.sampleapplication.R

class EmployeeAdapter(private val employeeList: List<Employee>) :
    RecyclerView.Adapter<EmployeeAdapter.EmployeeViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EmployeeViewHolder {
        val itemView =
            LayoutInflater.from(parent.context).inflate(R.layout.item_employee, parent, false)
        return EmployeeViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: EmployeeViewHolder, position: Int) {
        val currentItem = employeeList[position]

        holder.nameTextView.text = currentItem.name
        holder.emailTextView.text = currentItem.email
    }

    override fun getItemCount() = employeeList.size

    class EmployeeViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val nameTextView: TextView = itemView.findViewById(R.id.text_view_name)
        val emailTextView: TextView = itemView.findViewById(R.id.text_view_email)
    }
}
