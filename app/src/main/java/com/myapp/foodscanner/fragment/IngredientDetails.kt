package com.myapp.foodscanner.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.myapp.foodscanner.R
import com.myapp.foodscanner.adapter.IngredientsAdapter

class IngredientDetails : Fragment() , IngredientsAdapter.onIngredientClick {


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_ingredient_details, container, false)
    }

    override fun ingredient() {
            TODO("Not yet implemented")
    }


}