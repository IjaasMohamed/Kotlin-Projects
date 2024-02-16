package com.example.sampleapplication

import android.app.Activity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class DataAdapter(val context: Activity, val data: List<MyData>) :
    RecyclerView.Adapter<DataAdapter.ViewHolder>() {

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val messageTextView: TextView = itemView.findViewById(R.id.tv_message)
        private val statusCodeTextView: TextView = itemView.findViewById(R.id.tv_status_code)
        private val syncTimeTextView: TextView = itemView.findViewById(R.id.tv_sync_time)

        fun bind(item: MyData) {
            messageTextView.text = item.Message
            statusCodeTextView.text = "Status Code: ${item.Status_Code}"
            syncTimeTextView.text = "Sync Time: ${item.Sync_Time}"
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.item_product_data, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return data.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val currentItem = data[position]
        holder.bind(currentItem)
    }
}
