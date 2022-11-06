package com.myapp.foodscanner.fragment

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.myapp.foodscanner.*
import com.myapp.foodscanner.adapter.NutrientsAdapter
import com.myapp.foodscanner.data.AllProducts
import com.myapp.foodscanner.data.Nutrients
import com.myapp.foodscanner.databinding.FragmentProductBinding
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.Response


class ProductFragment : Fragment(), ArchitecturalFunctions {

    private lateinit var barcode: String
    private lateinit var binding: FragmentProductBinding
    private lateinit var nutrientsAdapter : NutrientsAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentProductBinding.inflate(inflater, container, false)
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
        barcode = requireArguments().getString("barcode").toString()
        Log.i("--TAG--", "" + barcode)

    }

    override fun initialize() {

    }

    override fun listen() {

    }

    override fun load() {

        val Product = Retrofit.getInstance().create(FoodService::class.java)
        var productId : Int

        GlobalScope.launch {
            var result = Product.getProduct(barcode)
            Log.i("--TAG--", "barcode in api call ${result.body().toString()}")
            populateData(result.body())

            kotlin.run {
                GlobalScope.launch {
                    val nutrients = result.body()?.get(0)?.id?.let { Product.getNutrients(it) }
                    if (nutrients != null) {
                        Log.i("--TAG--", "nutrients ${nutrients.body().toString()}")
                        populateNutrients(nutrients.body())
                    }
                }
            }
        }



    }

    private fun populateData(body: ArrayList<AllProducts>?) {

        val data = body?.get(0)
        binding.tvProductName.text = data?.name + " (${data?.weight} g)"
        binding.tvBarcode.text = data?.barcode

    }

    private fun populateNutrients(nutrients: ArrayList<Nutrients>?) {
        if(nutrients!=null){
            nutrientsAdapter = nutrients.let { NutrientsAdapter(it) }
            binding.rvNutrition.adapter = nutrientsAdapter
            nutrientsAdapter.notifyDataSetChanged()
        }


    }
}