package com.example.retrofitjson_parse.UpcomingEventsData.Data

import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.http.GET

interface ApiClient {
    @GET("v2/competitions/")
    fun upcomingEvents(): Call<ResponseBody>
}