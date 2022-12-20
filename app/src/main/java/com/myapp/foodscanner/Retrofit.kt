package com.myapp.foodscanner

import retrofit2.converter.gson.GsonConverterFactory

object Retrofit {

    val baseUrl = "http://192.168.1.5:3000/"

    fun getInstance(): retrofit2.Retrofit {
        return retrofit2.Retrofit.Builder().baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create()).build()
    }
}