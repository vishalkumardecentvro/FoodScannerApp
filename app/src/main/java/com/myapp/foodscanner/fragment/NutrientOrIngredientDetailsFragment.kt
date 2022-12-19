package com.myapp.foodscanner.fragment

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.myapp.foodscanner.ArchitecturalFunctions
import com.myapp.foodscanner.FoodService
import com.myapp.foodscanner.R
import com.myapp.foodscanner.Retrofit
import com.myapp.foodscanner.adapter.ViewPagerAdapter
import com.myapp.foodscanner.data.NutrientDetails
import com.myapp.foodscanner.data.Nutrients
import com.myapp.foodscanner.databinding.FragmentNutrientOrIngredientDetailsBinding
import retrofit2.Call
import retrofit2.Response
import javax.security.auth.callback.Callback


class NutrientOrIngredientDetailsFragment : Fragment() , ArchitecturalFunctions {

    lateinit var binding : FragmentNutrientOrIngredientDetailsBinding
    private var nutrientOrIngredient : Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentNutrientOrIngredientDetailsBinding.inflate(inflater,container,false)
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

        nutrientOrIngredient = requireArguments().getInt("nutrientId")

    }

    override fun initialize() {

    }

    override fun listen() {


    }

    override fun load() {

        val nutrientDetails = Retrofit.getInstance().create(FoodService::class.java)

       val details =  nutrientDetails.getNutrientDetails(nutrientOrIngredient)

        details.enqueue(object : retrofit2.Callback<ArrayList<NutrientDetails>>{
            override fun onResponse(
                call: Call<ArrayList<NutrientDetails>>,
                response: Response<ArrayList<NutrientDetails>>
            ) {
                if (response.isSuccessful && response.body()?.size!! > 0) {
                    Log.i("--TAG--", "response body size = ${response.body()?.size!!}")

                    binding.tvDetails.text = response.body()!![0].details

                } else {
                    Toast.makeText(
                        requireContext(),
                        "response is unsuccessful or response body size is empty",
                        Toast.LENGTH_SHORT
                    ).show()
                    Log.i("--TAG--", "response body size = ${response.body()?.size!!}")
                }
            }

            override fun onFailure(call: Call<ArrayList<NutrientDetails>>, t: Throwable) {
                Log.i("--TAG--", "error in nutrient details = $t")
            }

        })

    }


}