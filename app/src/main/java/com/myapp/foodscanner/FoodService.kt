package com.myapp.foodscanner

import com.myapp.foodscanner.data.AllProducts
import com.myapp.foodscanner.data.Nutrients
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface FoodService {

    @GET("allproduct")
    suspend fun getAllProduct():Response<ArrayList<AllProducts>>

    @GET("product/{barcode}")
    suspend fun getProduct(@Path("barcode") barcode : String):Response<ArrayList<AllProducts>>

    @GET("product/nutrients/{product_id}")
    suspend fun getNutrients(@Path("product_id") productId : Int) : Response<ArrayList<Nutrients>>
}