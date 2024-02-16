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
        setContentView(R.layout.activity_product)

        //create the instance of Api Request
        val apiInterface = RetrofitClient.create()

        val request = ApiRequest(
            listOf(RequestBodyItem("05e0e2342d4594f6")),
            "GetItemsData",
            "",
            "EZCMP-1"
        )

        //Make the API call
        val call: Call<MyData> = apiInterface.getData(request)

        // Enqueue the call asynchronously
        call.enqueue(object : Callback<MyData> {
            override fun onResponse(call: Call<MyData>, response: Response<MyData>) {
                if (response.isSuccessful) {
                    val data: MyData? = response.body()
                    val textView = findViewById<TextView>(R.id.tv_content)
                    // Display the 'Message' field from the 'MyData' object
                    textView.text = data?.Message
                } else {
                    // Handle error response
                    val textView = findViewById<TextView>(R.id.tv_content)
                    textView.text = "Error: ${response.errorBody()}"
                }
            }

            override fun onFailure(call: Call<MyData>, t: Throwable) {
                // Handle network error
                val textView = findViewById<TextView>(R.id.tv_content)
                textView.text = "Network error: ${t.localizedMessage}"
            }
        })
    }
}
