package com.myapp.foodscanner

import com.google.gson.annotations.SerializedName

data class AllProducts(

    val id : Int,
    @SerializedName("manufacture_date")
    val manufactureDate:String,
    @SerializedName("expiry_date")
    val expiryDate:String,
    @SerializedName("barcode")
    val barcode:String

) {
}