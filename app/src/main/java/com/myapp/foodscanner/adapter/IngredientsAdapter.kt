package com.myapp.foodscanner.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.myapp.foodscanner.data.Ingredients
import com.myapp.foodscanner.databinding.RvIngredientsBinding
import com.squareup.picasso.Picasso

class IngredientsAdapter(var ingredientList: ArrayList<Ingredients>) :
    RecyclerView.Adapter<IngredientsAdapter.ViewHolder>() {

    private var ingredientClick: onIngredientClick? = null;

    inner class ViewHolder(var binding: RvIngredientsBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(ingredient: Ingredients) {
            binding.tvIngredient.text = ingredient.name
            if (ingredient.image != null)
                Picasso.get().load(ingredient.image).into(binding.ivIngredient)

            binding.mcvNutrientsOrIngredients.setOnClickListener {
                ingredientClick?.ingredient(ingredient)
            }

        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        val inflater = LayoutInflater.from(parent.context)
        val binding: RvIngredientsBinding =
            RvIngredientsBinding.inflate(inflater, parent, false)
        return ViewHolder(binding)

    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(ingredientList.get(position))
    }

    override fun getItemCount(): Int {
        return ingredientList.size
    }

    interface onIngredientClick {
        fun ingredient(ingredient: Ingredients)
    }

    fun clickIngredinet(onIngredientClick: onIngredientClick) {
        this.ingredientClick = onIngredientClick
    }


}