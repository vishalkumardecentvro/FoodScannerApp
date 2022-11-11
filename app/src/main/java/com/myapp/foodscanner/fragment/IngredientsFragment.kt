package com.myapp.foodscanner.fragment

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.myapp.foodscanner.ArchitecturalFunctions
import com.myapp.foodscanner.FoodService
import com.myapp.foodscanner.Retrofit
import com.myapp.foodscanner.adapter.IngredientsAdapter
import com.myapp.foodscanner.data.Ingredients
import com.myapp.foodscanner.databinding.FragmentIngredientsBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class IngredientsFragment(bundleData: Bundle) : Fragment(),ArchitecturalFunctions {

    private var productId = bundleData.getInt("productId")

    private lateinit var binding: FragmentIngredientsBinding
    private lateinit var ingredientsAdapter: IngredientsAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentIngredientsBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        instantiate()
        initialize()
        listen()
        load()
    }

    override fun instantiate() {
        Log.i("--TAG--", "prod id = $productId")

    }

    override fun initialize() {

    }

    override fun listen() {

    }

    override fun load() {

        val Product = Retrofit.getInstance().create(FoodService::class.java)

        val ingredients = Product.getIngredients(productId)

        ingredients?.enqueue(object : Callback<ArrayList<Ingredients>> {
            override fun onResponse(
                call: Call<ArrayList<Ingredients>>,
                response: Response<ArrayList<Ingredients>>
            ) {
                if (response.isSuccessful && response.body()?.size!! > 0) {
                    populateNutrients(response.body())
                }
            }

            override fun onFailure(call: Call<ArrayList<Ingredients>>, t: Throwable) {
                Log.i("--TAG--", "error in nutrient details = $t")
            }

        })

    }

    private fun populateNutrients(nutrients: ArrayList<Ingredients>?) {
        Log.i("--TAG--", "inside ingredients")
        if (nutrients != null) {
            ingredientsAdapter = nutrients.let { IngredientsAdapter(it) }
            binding.rvNutrition.adapter = ingredientsAdapter
            ingredientsAdapter.notifyDataSetChanged()
        }

    }
}