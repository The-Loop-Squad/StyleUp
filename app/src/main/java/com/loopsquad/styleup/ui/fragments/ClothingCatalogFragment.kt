package com.loopsquad.styleup.ui.fragments

import Product
import ProductAdapter
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.tabs.TabLayout
import com.loopsquad.styleup.R


class ClothingCatalogFragment : Fragment() {

    private var recyclerView: RecyclerView? = null
    private var productAdapter: ProductAdapter? = null
    private var productList: ArrayList<Product>? = null
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?
        ,savedInstanceState: Bundle?):View? {
        val view = inflater.inflate(R.layout.fragment_clothing_catalog, container, false)
        view.findViewById<ImageView>(R.id.btn_profile_image).setOnClickListener {
            activity?.supportFragmentManager?.beginTransaction()
                ?.replace(R.id.fragmentContainer, ProfileFragment())
                ?.commit()

            activity?.findViewById<TabLayout>(com.loopsquad.styleup.R.id.tabLayoutLeft)
                ?.selectTab(null)
            activity?.findViewById<TabLayout>(com.loopsquad.styleup.R.id.tabLayoutRight)
                ?.getTabAt(0)?.select()
        }

        recyclerView = view.findViewById(R.id.recyclerViewCatalog)
        recyclerView?.setLayoutManager(GridLayoutManager(context, 2)) // 2 columns

        productList = ArrayList()
        productList!!.add(Product("Shirt", R.drawable.ic_profile))
        productList!!.add(Product("Pants", R.drawable.ic_home))
        productList!!.add(Product("Shirt", R.drawable.ic_profile))
        productList!!.add(Product("Pants", R.drawable.ic_home))
        productList!!.add(Product("Shirt", R.drawable.ic_profile))
        productList!!.add(Product("Pants", R.drawable.ic_home))
        productList!!.add(Product("Shirt", R.drawable.ic_profile))
        productList!!.add(Product("Pants", R.drawable.ic_home))
        productList!!.add(Product("Shirt", R.drawable.ic_profile))
        productList!!.add(Product("Pants", R.drawable.ic_home))


        // Add more products
        productAdapter = ProductAdapter(productList!!, requireContext())
        recyclerView?.setAdapter(productAdapter)
        return view
    }


}