package com.myapp.foodscanner.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.myapp.foodscanner.data.Ingredients
import com.myapp.foodscanner.databinding.RvIngredientsBinding

class IngredientsAdapter(var nutrientsList: ArrayList<Ingredients>) :
    RecyclerView.Adapter<IngredientsAdapter.ViewHolder>() {

    inner class ViewHolder(var binding: RvIngredientsBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(nutrients: Ingredients) {
            binding.tvNutrient.text = nutrients.name

        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        val inflater = LayoutInflater.from(parent.context)
        val binding: RvIngredientsBinding =
            RvIngredientsBinding.inflate(inflater, parent, false)
        return ViewHolder(binding)

    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(nutrientsList.get(position))
    }

    override fun getItemCount(): Int {
        return nutrientsList.size
    }


}