package com.myapp.foodscanner.data

import com.google.gson.annotations.SerializedName

data class AllProducts(

    val id : Int,
    @SerializedName("manufacture_date")
    val manufactureDate:String,
    @SerializedName("expiry_date")
    val expiryDate:String,
    @SerializedName("barcode")
    val barcode:String,
    @SerializedName("name")
    val name : String,
    @SerializedName("weight")
    val weight : String,
    @SerializedName("price")
    val price : String,
    @SerializedName("description")
    val description : String,
    @SerializedName("image")
    val image : String

) {
}