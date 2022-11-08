package com.myapp.foodscanner.fragment

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.google.android.material.tabs.TabLayout
import com.myapp.foodscanner.*
import com.myapp.foodscanner.adapter.NutrientsAdapter
import com.myapp.foodscanner.adapter.ViewPagerAdapter
import com.myapp.foodscanner.data.AllProducts
import com.myapp.foodscanner.data.Nutrients
import com.myapp.foodscanner.databinding.FragmentProductBinding
import com.squareup.picasso.Picasso
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class ProductFragment : Fragment(), ArchitecturalFunctions {

    private lateinit var barcode: String
    private lateinit var binding: FragmentProductBinding
    private lateinit var nutrientsAdapter: NutrientsAdapter
    private lateinit var bundle: Bundle
    //private lateinit var viewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentProductBinding.inflate(inflater, container, false)
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
        barcode = requireArguments().getString("barcode").toString()
        Log.i("--TAG--", "" + barcode)

    }

    override fun initialize() {


    }

    override fun listen() {

        binding.tlTabs.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab?) {


                tab?.position?.let { binding.vpIngredientsAndNutrients.currentItem = it }
            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {
            }

            override fun onTabReselected(tab: TabLayout.Tab?) {
            }

        })

    }

    override fun load() {

        val Product = Retrofit.getInstance().create(FoodService::class.java)

        val productDetails = Product.getProduct(barcode.toString())


        productDetails.enqueue(object : Callback<ArrayList<AllProducts>> {

            override fun onResponse(
                call: Call<ArrayList<AllProducts>>,
                response: Response<ArrayList<AllProducts>>
            ) {
                Log.i("--TAG--", response.body().toString())

                if (response.isSuccessful && response.body()?.size!! > 0) {

                    populateData(response.body())

                    bundle = Bundle()
                    bundle.apply {
                        putInt("productId", response.body()!![0].id)
                    }
                    binding.vpIngredientsAndNutrients.adapter = ViewPagerAdapter(requireActivity(),bundle)

//                    if (response.body()?.size!! > 0) {
//
//                        val nutrients = response.body()?.get(0)?.let { Product.getNutrients(it.id) }
//
//                        nutrients?.enqueue(object : Callback<ArrayList<Nutrients>> {
//                            override fun onResponse(
//                                call: Call<ArrayList<Nutrients>>,
//                                response: Response<ArrayList<Nutrients>>
//                            ) {
//                                if (response.isSuccessful && response.body()?.size!! > 0) {
//                                    populateNutrients(response.body())
//                                }
//                            }
//
//                            override fun onFailure(call: Call<ArrayList<Nutrients>>, t: Throwable) {
//                                Log.i("--TAG--", "error in nutrient details = $t")
//                            }
//
//                        })
//
//                    }

                } else {
                    Toast.makeText(
                        requireContext(),
                        "response is unsuccessful or response body size is empty",
                        Toast.LENGTH_SHORT
                    ).show()
                    Log.i("--TAG--", "response body size = ${response.body()?.size!!}")
                }
            }

            override fun onFailure(call: Call<ArrayList<AllProducts>>, t: Throwable) {
                Log.i("--TAG--", "error in product details = $t")
            }

        })

    }

    private fun populateData(body: ArrayList<AllProducts>?) {

        if (body?.size!! > 0) {

            val data = body[0]

            binding.tvProductName.text = data.name + " (${data.weight} g)"
            binding.tvBarcode.text = data.barcode
            binding.tvDescription.text = data.description
            Picasso.get().load(data.image).into(binding.ivProductImage)

        } else {
            Log.i("--TAG--", "body size ${body.size}")
        }


    }

//    private fun populateNutrients(nutrients: ArrayList<Nutrients>?) {
//        if (nutrients != null) {
//            nutrientsAdapter = nutrients.let { NutrientsAdapter(it) }
//            binding.rvNutrition.adapter = nutrientsAdapter
//            nutrientsAdapter.notifyDataSetChanged()
//        }
//
//
//    }
}