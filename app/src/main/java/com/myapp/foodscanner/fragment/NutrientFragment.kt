package com.myapp.foodscanner.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.myapp.foodscanner.ArchitecturalFunctions
import com.myapp.foodscanner.R
import com.myapp.foodscanner.adapter.NutrientsAdapter
import com.myapp.foodscanner.data.Nutrients
import com.myapp.foodscanner.databinding.FragmentNutrientBinding
import com.myapp.foodscanner.databinding.FragmentProductBinding
import com.myapp.foodscanner.databinding.RvNutrientsBinding

class NutrientFragment : Fragment(), ArchitecturalFunctions {

    private lateinit var binding: FragmentNutrientBinding

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

    }



}