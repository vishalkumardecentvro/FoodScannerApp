package com.myapp.foodscanner

import com.myapp.foodscanner.data.AllProducts
import com.myapp.foodscanner.data.Ingredients
import com.myapp.foodscanner.data.NutrientDetails
import com.myapp.foodscanner.data.Nutrients
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface FoodService {

    @GET("allproduct")
    suspend fun getAllProduct(): Response<ArrayList<AllProducts>>

    @GET("product/{barcode}")
    fun getProduct(@Path("barcode") barcode: String): Call<ArrayList<AllProducts>>

    @GET("product/ingredient/{product_id}")
    fun getIngredients(@Path("product_id") productId: Int): Call<ArrayList<Ingredients>>

    @GET("product/nutrient/{product_id}")
    fun getNutrients(@Path("product_id") productId: Int): Call<ArrayList<Nutrients>>

    @GET("product/nutrient/details/{nutrient_id}")
    fun getNutrientDetails(@Path("nutrient_id") nutrientId: Int): Call<ArrayList<NutrientDetails>>
}