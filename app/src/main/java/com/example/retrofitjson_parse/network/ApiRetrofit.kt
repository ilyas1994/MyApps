package com.example.retrofitjson_parse.network

import com.example.retrofitjson_parse.UpcomingEventsData.Data.ApiClient
import retrofit2.Retrofit
import retrofit2.converter.jackson.JacksonConverterFactory

const val API_BASE_URL = "http://api.football-data.org/"

val apiRetrofit: Retrofit = Retrofit.Builder()
    .baseUrl(API_BASE_URL)
    .addConverterFactory(JacksonConverterFactory.create())
    .build()

val apiClient: ApiClient = apiRetrofit.create(ApiClient::class.java)