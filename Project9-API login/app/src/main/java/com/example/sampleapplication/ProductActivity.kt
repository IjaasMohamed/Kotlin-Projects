package com.example.sampleapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ProductActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.item_product_data)

        // Create the instance of Api Request
        val apiInterface = RetrofitClient.create()

        val request = ApiRequest(
            listOf(RequestBodyItem("05e0e2342d4594f6")),
            "GetItemsData",
            "",
            "EZCMP-1"
        )

        // Make the API call
        val call: Call<MyData> = apiInterface.getData(request)

        // Enqueue the call asynchronously
        call.enqueue(object : Callback<MyData> {
            override fun onResponse(call: Call<MyData>, response: Response<MyData>) {
                if (response.isSuccessful) {
                    val data: MyData? = response.body()
                    // Update TextViews with data from API response
                    data?.let {
                        findViewById<TextView>(R.id.tv_message).text = it.Message
                        findViewById<TextView>(R.id.tv_status_code).text = it.Status_Code.toString()
                        findViewById<TextView>(R.id.tv_sync_time).text = it.Sync_Time
                    }
                } else {
                    // Handle error response
                    val textView = findViewById<TextView>(R.id.tv_message)
                    textView.text = "Error: ${response.errorBody()}"
                }
            }

            override fun onFailure(call: Call<MyData>, t: Throwable) {
                // Handle network error
                val textView = findViewById<TextView>(R.id.tv_message)
                textView.text = "Network error: ${t.localizedMessage}"
            }
        })
    }
}
