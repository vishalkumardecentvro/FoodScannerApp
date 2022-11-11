package com.myapp.foodscanner.adapter

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.myapp.foodscanner.fragment.IngredientsFragment
import com.myapp.foodscanner.fragment.NutrientFragment

class ViewPagerAdapter(fragmentActivity: FragmentActivity, bundle: Bundle) :

    FragmentStateAdapter(fragmentActivity) {

    val bundleData = bundle

    override fun getItemCount(): Int {
        return 2
    }

    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> IngredientsFragment(bundleData)
            1 -> NutrientFragment(bundleData)
            else -> {
                IngredientsFragment(bundleData)
            }
        }
    }
}