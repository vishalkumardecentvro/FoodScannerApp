package com.myapp.foodscanner

import retrofit2.Response
import retrofit2.http.GET

interface FoodService {

    @GET("allproduct")
    suspend fun getAllProduct():Response<ArrayList<AllProducts>>
}