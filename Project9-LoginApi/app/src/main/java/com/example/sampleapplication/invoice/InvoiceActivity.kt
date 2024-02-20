package com.example.sampleapplication.invoice

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.example.sampleapplication.ApiInterface
import com.example.sampleapplication.R
import com.example.sampleapplication.RetrofitClient  // Import the RetrofitClient object
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class InvoiceActivity : AppCompatActivity() {
    private lateinit var apiInterface: ApiInterface

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_invoice)

        // Initialize Retrofit client using the RetrofitClient object
        apiInterface = RetrofitClient.create()

        // Call method to send API request
        sendApiRequest()
    }

    private fun sendApiRequest() {
        // Create your request data here with 5 random items
        val requestData = createRequestData()

        // Make API call using the ApiInterface
        val call = apiInterface.sendInvoiceData(requestData)

        // Execute the API call asynchronously
        call.enqueue(object : Callback<ApiResponse> {
            override fun onResponse(call: Call<ApiResponse>, response: Response<ApiResponse>) {
                if (response.isSuccessful) {
                    // API call successful, handle response here
                    val apiResponse = response.body()
                    apiResponse?.let {
                        Log.d("InvoiceActivity", "Response: $apiResponse")
                        // Handle the response data accordingly
                    }
                } else {
                    // API call failed, handle error here
                    Log.e("InvoiceActivity", "API call failed with code ${response.code()}")
                }
            }

            override fun onFailure(call: Call<ApiResponse>, t: Throwable) {
                // API call failed, handle error here
                Log.e("InvoiceActivity", "API call failed: ${t.message}", t)
            }
        })
    }

    private fun createRequestData(): RequestData {
        // Create your request data here with 5 random items
        // For simplicity, I'm providing a dummy implementation
        val itemDetails = listOf(
            ItemDetail("EZCMP314/EZLOC8/ITM-124", "5", "2000", "1500.0", "0", "8500")
            // Add more items if needed
        )
        return RequestData(
            listOf(
                RequestBody(
                    "EZCMP314/EZLOC8/CUS-293",
                    "2023-10-12",
                    "C314/L12/1/MINV-839",
                    "10:23 am",
                    "",
                    itemDetails,
                    "6.8948495",
                    "EZCMP314/EZLOC-12",
                    "79.9215317",
                    "8500",
                    "",
                    "",
                    "",
                    "2AF5FFF2-C1D8-4CB5-BFCF-1701B563272B"
                )
            ),
            "SaveInvoiceData",
            "EZCMP-314",
            "41"
        )
    }
}
