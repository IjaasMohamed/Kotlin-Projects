package com.example.sampleapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.sampleapplication.item.ApiRequestBody
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
        }
    }
}