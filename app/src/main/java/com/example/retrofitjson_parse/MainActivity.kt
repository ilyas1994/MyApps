package com.example.retrofitjson_parse

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.retrofitjson_parse.UpcomingEventsData.Data.ApiClient
import com.example.retrofitjson_parse.UpcomingEventsData.model.ApiBranchData
import com.example.retrofitjson_parse.network.apiClient
import okhttp3.ResponseBody
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

const val TAG = "MainActivity"

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        apiClient.upcomingEvents().enqueue(object : Callback<ResponseBody>{
            override fun onFailure(call: Call<ResponseBody>, t: Throwable) {
                Log.d(TAG, t.localizedMessage ?: "occured failure")
            }

            override fun onResponse(call: Call<ResponseBody>, response: Response<ResponseBody>) {
                val responseBody: ResponseBody = response.body()!!
                val responseJsonString = responseBody.string()
                val responseJsonObject = JSONObject(responseJsonString)

                val apiBranchDataList = parseBranchesJsonObject(responseJsonObject)
                Log.d("ParsingResult", apiBranchDataList.toString())
            }

        })
    }

   private fun parseBranchesJsonObject(responseJSONObject: JSONObject): List<ApiBranchData>{
        val branclist = mutableListOf<ApiBranchData>()
        for (index in 0 until responseJSONObject.length()){
            val branchJsonObject = (responseJSONObject[index.toString()] as? JSONObject) ?: continue

            val apiBranchData = parseBranchJsonObject(branchJsonObject)
            branclist.add(apiBranchData)
        }
       return branclist
    }

    private fun parseBranchJsonObject(brancJSONObject: JSONObject): ApiBranchData {
       val count = brancJSONObject.getInt("Count")

        return ApiBranchData(count = count)
    }

//

}