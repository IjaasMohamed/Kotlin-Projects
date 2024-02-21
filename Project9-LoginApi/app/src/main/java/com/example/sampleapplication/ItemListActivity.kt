package com.example.sampleapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.sampleapplication.item.ApiRequestBody
import com.example.sampleapplication.item.ItemAdapter
import com.example.sampleapplication.item.ResponseBody
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

    class ItemListActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_item_list)

        val apiInterface = RetrofitClient.create()

        val request = ApiRequestBody(
            API_Body = listOf(mapOf("Unique_Id" to "05e0e2342d4594f6")),
            Api_Action = "GetItemsData",
            Sync_Time = "",
            Company_Code = "EZCMP-1"
        )

        // Launch a new coroutine to make the API call
        CoroutineScope(Dispatchers.Main).launch {
            val response = withContext(Dispatchers.IO) {
                apiInterface.getItemsData(request)
            }

            // Assuming 'response' is an ApiResponse and it has a property Response_Body which is a list of items
            val items: String = response.Response_Body

            val recyclerView = findViewById<RecyclerView>(R.id.recyclerView)
            recyclerView.layoutManager = LinearLayoutManager(this@ItemListActivity)
        //    recyclerView.adapter = ItemAdapter(items)
        }
    }
}
