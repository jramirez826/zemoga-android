package com.jramirez.pruebazemoga.data.web

import com.google.gson.GsonBuilder
import com.jramirez.pruebazemoga.BuildConfig
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitClient {
    val client : JSONPlaceHolderAPI = Retrofit.Builder()
        .baseUrl(BuildConfig.BASE_URL)
        .addConverterFactory(GsonConverterFactory.create(GsonBuilder().setLenient().create()))
        .build()
        .create(JSONPlaceHolderAPI::class.java)
}