package com.loopsquad.styleup.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.fragment.app.Fragment
import com.google.android.material.tabs.TabLayout
import com.loopsquad.styleup.R

class ClothingCatalogFragment : Fragment() {
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?
        ,savedInstanceState: Bundle?):View? {
        val view = inflater.inflate(R.layout.fragment_clothing_catalog, container, false)
        view.findViewById<ImageView>(R.id.btn_profile_image).setOnClickListener {
            activity?.supportFragmentManager?.beginTransaction()
                ?.replace(R.id.fragmentContainer, ProfileFragment())
                ?.commit()

            activity?.findViewById<TabLayout>(R.id.tabLayoutLeft)?.selectTab(null)
            activity?.findViewById<TabLayout>(R.id.tabLayoutRight)?.getTabAt(0)?.select()

        }
        return view
    }
}