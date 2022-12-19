package com.myapp.foodscanner.data

data class Nutrients(
    var id : Int,
    var product_id : Int,
    var nutrient_quantity : String,
    var nutrient_quantity_per_serving : String,
    var image : String,
    var name : String,
    var amount : String,
    var nutrient_id : Int,
    var description : String
) : java.io.Serializable{
}