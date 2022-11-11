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
import com.myapp.foodscanner.adapter.NutrientsAdapter
import com.myapp.foodscanner.data.Ingredients
import com.myapp.foodscanner.data.Nutrients
import com.myapp.foodscanner.databinding.FragmentNutrientBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class NutrientFragment(bundleData: Bundle) : Fragment(), ArchitecturalFunctions {

    private lateinit var binding: FragmentNutrientBinding
    private var productId = bundleData.getInt("productId")
    private lateinit var nutrientsAdapter: NutrientsAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentNutrientBinding.inflate(inflater,container,false)
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

    }

    override fun initialize() {

    }

    override fun listen() {

    }

    override fun load() {
        val Product = Retrofit.getInstance().create(FoodService::class.java)

        val nutrients = Product.getNutrients(productId)

        nutrients.enqueue(object : Callback<ArrayList<Nutrients>>{
            override fun onResponse(
                call: Call<ArrayList<Nutrients>>,
                response: Response<ArrayList<Nutrients>>
            ) {
                if (response.isSuccessful && response.body()?.size!! > 0) {
                    populateNutrients(response.body())
                }
            }

            override fun onFailure(call: Call<ArrayList<Nutrients>>, t: Throwable) {
                Log.i("--TAG--", "error in nutrient details = $t")
            }

        })

    }

    private fun populateNutrients(nutrients: ArrayList<Nutrients>?) {
        Log.i("--TAG--", "inside ingredients")
        if (nutrients != null) {
            nutrientsAdapter = nutrients.let { NutrientsAdapter(it) }
            binding.rvNutrition.adapter = nutrientsAdapter
            nutrientsAdapter.notifyDataSetChanged()
        }

    }



}