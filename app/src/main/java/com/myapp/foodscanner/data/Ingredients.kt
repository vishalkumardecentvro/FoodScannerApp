package com.myapp.foodscanner.data

data class Ingredients(
    var id : Int,
    var name : String,
    var quantity : String,
    var product_id : Int,
    var ingredient_id : Int,
    var description : String,
    var image : String
) {
}