package com.myapp.foodscanner.fragment

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.myapp.foodscanner.*
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import retrofit2.create


class ProductFragment : Fragment(), ArchitecturalFunctions {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_product, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        instantiate()
        initialize()
        listen()
        load()
    }

    override fun instantiate() {
        val barcode = requireArguments().getString("barcode")
        Log.i("--TAG--", "" + barcode)

    }

    override fun initialize() {

    }

    override fun listen() {

    }

    override fun load() {

        val Products = Retrofit.getInstance().create(FoodService::class.java)

        GlobalScope.launch {
            val result = Products.getAllProduct()
            Log.i("--TAG--", "barcode in api call ${result.body().toString()}")
        }

    }
}