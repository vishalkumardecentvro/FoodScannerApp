package com.myapp.foodscanner.data

import android.os.Parcelable
import com.google.gson.annotations.SerializedName

data class Ingredients(
    var id : Int,
    var name : String,
    @SerializedName("ingredient_quantity")
    var quantity : String,
    var product_id : Int,
    var ingredient_id : Int,
    var description : String,
    var image : String
):java.io.Serializable {
}