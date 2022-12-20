package com.myapp.foodscanner.fragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.myapp.foodscanner.data.Ingredients
import com.myapp.foodscanner.databinding.FragmentDescriptionOfNutrientOrIngredientBinding
import com.squareup.picasso.Picasso


class DescriptionOfNutrientOrIngredientFragment : Fragment() {

    lateinit var binding : FragmentDescriptionOfNutrientOrIngredientBinding

    lateinit var ingredients : Ingredients
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentDescriptionOfNutrientOrIngredientBinding.inflate(inflater,container,false)
        return binding.root


    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        ingredients = requireArguments().getSerializable("ingredient") as Ingredients
        Log.i("--TAG M--",ingredients.description)

        if(ingredients!=null){
            Picasso.get().load(ingredients.image).into(binding.ivIngredientOrNutrientImage)
            binding.tvDescription.text = ingredients.description
            binding.tvProductName.text = ingredients.name
            binding.tvQuantity.text = "Ingredient quantity : ${ingredients.quantity}"


        }
    }
}