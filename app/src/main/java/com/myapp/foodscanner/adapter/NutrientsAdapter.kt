package com.myapp.foodscanner.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.myapp.foodscanner.data.Nutrients
import com.myapp.foodscanner.databinding.RvIngredientsBinding
import com.squareup.picasso.Picasso

class NutrientsAdapter(var nutrientsList: ArrayList<Nutrients>) :

    RecyclerView.Adapter<NutrientsAdapter.ViewHolder>() {

    private var onNutrientCLick : OnNutrientClickInterface?  = null

    inner class ViewHolder(var binding: RvIngredientsBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(nutrients: Nutrients) {
            binding.tvIngredient.text = nutrients.name + " " + nutrients.nutrient_quantity
            if (nutrients.image != null)
                Picasso.get().load(nutrients.image).into(binding.ivIngredient)



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

    fun onClickItem(onClick : OnNutrientClickInterface){
        this.onNutrientCLick = onClick
    }

    interface OnNutrientClickInterface {
        fun onNutrient(adapterPosition: Int, nutrients: Nutrients)
    }


}