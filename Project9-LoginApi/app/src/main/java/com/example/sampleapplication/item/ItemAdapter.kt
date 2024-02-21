package com.example.sampleapplication.item

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.sampleapplication.R

class ItemAdapter(private val items: List<ResponseBody>) : RecyclerView.Adapter<ItemAdapter.ItemViewHolder>() {

    class ItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val nameTextView: TextView = itemView.findViewById(R.id.nameTextView)
        val barcodeTextView: TextView = itemView.findViewById(R.id.barcodeTextView)
        val codeTextView: TextView = itemView.findViewById(R.id.codeTextView)
        val itemGroupTextView: TextView = itemView.findViewById(R.id.itemGroupTextView)
        val itemGroupCodeTextView: TextView = itemView.findViewById(R.id.itemGroupCodeTextView)
        val minusStockTextView: TextView = itemView.findViewById(R.id.minusStockTextView)
        val orderNoTextView: TextView = itemView.findViewById(R.id.orderNoTextView)
        val packSizeTextView: TextView = itemView.findViewById(R.id.packSizeTextView)
        // Add other views as needed...
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_view, parent, false)
        return ItemViewHolder(view)
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        val item = items[position]
        holder.nameTextView.text = item.Name
        holder.barcodeTextView.text = item.Barcode
        holder.codeTextView.text = item.Code
        holder.itemGroupTextView.text = item.Item_Group
        holder.itemGroupCodeTextView.text = item.Item_Group_Code
        holder.minusStockTextView.text = item.Minus_Stock.toString()
        holder.orderNoTextView.text = item.Order_No.toString()
        holder.packSizeTextView.text = item.Pack_Size
        // Set other views...
    }

    override fun getItemCount() = items.size
}
