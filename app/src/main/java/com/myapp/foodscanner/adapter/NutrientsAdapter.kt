package com.myapp.foodscanner.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.myapp.foodscanner.data.Nutrients
import com.myapp.foodscanner.databinding.RvNutrientsBinding

class NutrientsAdapter(var nutrientsList: ArrayList<Nutrients>) :
    RecyclerView.Adapter<NutrientsAdapter.ViewHolder>() {

    inner class ViewHolder(var binding: RvNutrientsBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(nutrients: Nutrients) {
            binding.tvNutrient.text = nutrients.name

        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        val inflater = LayoutInflater.from(parent.context)
        val binding: RvNutrientsBinding =
            RvNutrientsBinding.inflate(inflater, parent, false)
        return ViewHolder(binding)

    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(nutrientsList.get(position))
    }

    override fun getItemCount(): Int {
        return nutrientsList.size
    }


}