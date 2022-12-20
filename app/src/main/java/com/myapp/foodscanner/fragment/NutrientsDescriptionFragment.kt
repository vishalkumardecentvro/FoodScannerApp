package com.myapp.foodscanner.fragment

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.myapp.foodscanner.R
import com.myapp.foodscanner.data.Ingredients
import com.myapp.foodscanner.data.Nutrients
import com.myapp.foodscanner.databinding.FragmentNUtrientsDescriptionBinding
import com.myapp.foodscanner.databinding.FragmentNutrientBinding
import com.squareup.picasso.Picasso


class NutrientsDescriptionFragment : Fragment() {

    lateinit var binding : FragmentNUtrientsDescriptionBinding
    lateinit var nutrients : Nutrients

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentNUtrientsDescriptionBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        nutrients = requireArguments().getSerializable("nutrients") as Nutrients
        Log.i("--TAG M--",nutrients.description)

        if(nutrients!=null){
            Picasso.get().load(nutrients.image).into(binding.ivNutrient)
            binding.tvDescription.text = nutrients.description
            binding.tvNutrientName.text = nutrients.name
            binding.tvQuantity.text = "Nutrient quantity : ${nutrients.nutrient_quantity}"
            binding.tvQuantityPerServing.text = "Nutrient quantity (per serving) : ${nutrients.nutrient_quantity_per_serving}"


        }
    }
}