package com.example.sampleapplication

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class EmployeeAdapter(private val employeeList: List<Employee>, private val listener: EmployeeAdapterListener) :
    RecyclerView.Adapter<EmployeeAdapter.EmployeeViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EmployeeViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.item_employee, parent, false)
        return EmployeeViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: EmployeeViewHolder, position: Int) {
        val currentItem = employeeList[position]

        holder.nameTextView.text = currentItem.name
        holder.emailTextView.text = currentItem.email

        holder.editButton.setOnClickListener {
            listener.onEditClick(currentItem)
        }

        holder.deleteButton.setOnClickListener {
            listener.onDeleteClick(currentItem)
        }
    }

    override fun getItemCount() = employeeList.size

    class EmployeeViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val nameTextView: TextView = itemView.findViewById(R.id.text_view_name)
        val emailTextView: TextView = itemView.findViewById(R.id.text_view_email)
        val editButton: Button = itemView.findViewById(R.id.btn_edit)
        val deleteButton: Button = itemView.findViewById(R.id.btn_delete)
    }

    interface EmployeeAdapterListener {
        fun onEditClick(employee: Employee)
        fun onDeleteClick(employee: Employee)
    }
}
