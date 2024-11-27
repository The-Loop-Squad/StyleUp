package com.loopsquad.styleup

import android.os.Bundle
import android.view.LayoutInflater
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.android.material.tabs.TabLayout
import com.loopsquad.styleup.databinding.ActivityMainBinding
import com.loopsquad.styleup.ui.fragments.ClothingCatalogFragment
import com.loopsquad.styleup.ui.fragments.ExploreFragment
import com.loopsquad.styleup.ui.fragments.FavouritesFragment
import com.loopsquad.styleup.ui.fragments.ProfileFragment

class MainActivity : AppCompatActivity() {
    override fun onResume() {
        super.onResume()
        val b = ActivityMainBinding.inflate(layoutInflater)
        setContentView(b.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        leftBottomLayoutSetup(b)
        rightBottomLayoutSetup(b)
    }

    fun leftBottomLayoutSetup(b: ActivityMainBinding){

        b.tabLayoutLeft.setSelectedTabIndicatorColor(resources.getColor(R.color.white))
        b.tabLayoutLeft.setSelectedTabIndicatorHeight(0)
        b.tabLayoutLeft.setTabIndicatorFullWidth(false)
        b.tabLayoutLeft.tabRippleColor = null
        b.tabLayoutLeft.setTabTextColors(resources.getColor(R.color.grey), resources.getColor(R.color.white))

        val homeTab = b.tabLayoutLeft.newTab()
        val homeTabView = LayoutInflater.from(this).inflate(R.layout.tablayout_item, null)
        val homeTabIcon = homeTabView.findViewById<ImageView>(R.id.tab_icon)
        homeTabIcon.setImageResource(R.drawable.ic_home)
        homeTabIcon.imageTintList = resources.getColorStateList(R.color.grey)
        homeTab.customView = homeTabView
        b.tabLayoutLeft.addTab(homeTab)

        val exploreTab = b.tabLayoutLeft.newTab()
        val exploreTabView = LayoutInflater.from(this).inflate(R.layout.tablayout_item, null)
        val exploreTabIcon = exploreTabView.findViewById<ImageView>(R.id.tab_icon)
        exploreTabIcon.setImageResource(R.drawable.ic_explore)
        exploreTabIcon.imageTintList = resources.getColorStateList(R.color.grey)
        exploreTab.customView = exploreTabView
        b.tabLayoutLeft.addTab(exploreTab)
        b.tabLayoutLeft.selectTab(null)
        b.tabLayoutLeft.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab?) {
                when(tab?.position){
                    0 -> {
                        homeTab.customView?.findViewById<ImageView>(R.id.tab_icon)?.imageTintList = resources.getColorStateList(R.color.button_blue)
                        exploreTab.customView?.findViewById<ImageView>(R.id.tab_icon)?.imageTintList = resources.getColorStateList(R.color.grey)
                        supportFragmentManager.beginTransaction().replace(R.id.fragmentContainer, ClothingCatalogFragment()).commit()
                        homeTab.customView?.findViewById<ImageView>(R.id.tab_icon)?.backgroundTintList = resources.getColorStateList(R.color.tablayoutBg_blue)
                        exploreTab.customView?.findViewById<ImageView>(R.id.tab_icon)?.backgroundTintList = resources.getColorStateList(R.color.white)

                        b.tabLayoutRight.selectTab(null)
                    }
                    1->{

                        homeTab.customView?.findViewById<ImageView>(R.id.tab_icon)?.imageTintList = resources.getColorStateList(R.color.grey)
                        exploreTab.customView?.findViewById<ImageView>(R.id.tab_icon)?.imageTintList = resources.getColorStateList(R.color.button_blue)
                        supportFragmentManager.beginTransaction().replace(R.id.fragmentContainer, ExploreFragment()).commit()
                        homeTab.customView?.findViewById<ImageView>(R.id.tab_icon)?.backgroundTintList = resources.getColorStateList(R.color.white)
                        exploreTab.customView?.findViewById<ImageView>(R.id.tab_icon)?.backgroundTintList = resources.getColorStateList(R.color.tablayoutBg_blue)
                        b.tabLayoutRight.selectTab(null)

                    }

                }
            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {
                when(tab?.position){
                    0 -> {
                        homeTab.customView?.findViewById<ImageView>(R.id.tab_icon)?.imageTintList =
                            resources.getColorStateList(R.color.grey)
                        exploreTab.customView?.findViewById<ImageView>(R.id.tab_icon)?.imageTintList =
                            resources.getColorStateList(R.color.grey)
                        homeTab.customView?.findViewById<ImageView>(R.id.tab_icon)?.backgroundTintList =
                            resources.getColorStateList(R.color.white)
                        exploreTab.customView?.findViewById<ImageView>(R.id.tab_icon)?.backgroundTintList =
                            resources.getColorStateList(R.color.white)
                    }
                    1->{
                        homeTab.customView?.findViewById<ImageView>(R.id.tab_icon)?.imageTintList =
                            resources.getColorStateList(R.color.grey)
                        exploreTab.customView?.findViewById<ImageView>(R.id.tab_icon)?.imageTintList = resources.getColorStateList(R.color.grey)
                        homeTab.customView?.findViewById<ImageView>(R.id.tab_icon)?.backgroundTintList =
                            resources.getColorStateList(R.color.white)
                        exploreTab.customView?.findViewById<ImageView>(R.id.tab_icon)?.backgroundTintList = resources.getColorStateList(R.color.white)
                    }
                }

            }

            override fun onTabReselected(tab: TabLayout.Tab?) {
            }

        })
        b.tabLayoutLeft.selectTab(b.tabLayoutLeft.getTabAt(0))


    }

    fun rightBottomLayoutSetup(b: ActivityMainBinding){
        b.tabLayoutRight.setSelectedTabIndicatorColor(resources.getColor(R.color.white))
        b.tabLayoutRight.setSelectedTabIndicatorHeight(0)
        b.tabLayoutRight.setTabIndicatorFullWidth(false)
        b.tabLayoutRight.tabRippleColor = null
        b.tabLayoutRight.setTabTextColors(resources.getColor(R.color.grey), resources.getColor(R.color.white))


        val profileTab = b.tabLayoutRight.newTab()
        val profileTabView = LayoutInflater.from(this).inflate(R.layout.tablayout_item, null)
        val profileTabIcon = profileTabView.findViewById<ImageView>(R.id.tab_icon)
        profileTabIcon.setImageResource(R.drawable.ic_profile)
        profileTabIcon.imageTintList = resources.getColorStateList(R.color.grey)
        profileTab.customView = profileTabView
        b.tabLayoutRight.addTab(profileTab)

        val bookmarkTab = b.tabLayoutRight.newTab()
        val bookmarkTabView = LayoutInflater.from(this).inflate(R.layout.tablayout_item, null)
        val bookmarkTabIcon = bookmarkTabView.findViewById<ImageView>(R.id.tab_icon)
        bookmarkTabIcon.setImageResource(R.drawable.ic_bookmark)
        bookmarkTabIcon.imageTintList = resources.getColorStateList(R.color.grey)
        bookmarkTab.customView = bookmarkTabView
        b.tabLayoutRight.addTab(bookmarkTab)

        b.tabLayoutRight.selectTab(null)
        b.tabLayoutRight.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab?) {
                when(tab?.position){
                    0 -> {
                        profileTab.customView?.findViewById<ImageView>(R.id.tab_icon)?.imageTintList = resources.getColorStateList(R.color.button_blue)
                        bookmarkTab.customView?.findViewById<ImageView>(R.id.tab_icon)?.imageTintList = resources.getColorStateList(R.color.grey)
                        supportFragmentManager.beginTransaction().replace(R.id.fragmentContainer, ProfileFragment()).commit()
                        profileTab.customView?.findViewById<ImageView>(R.id.tab_icon)?.backgroundTintList = resources.getColorStateList(R.color.tablayoutBg_blue)
                        bookmarkTab.customView?.findViewById<ImageView>(R.id.tab_icon)?.backgroundTintList = resources.getColorStateList(R.color.white)
                        b.tabLayoutLeft.selectTab(null)
                    }
                    1->{
                        profileTab.customView?.findViewById<ImageView>(R.id.tab_icon)?.imageTintList = resources.getColorStateList(R.color.grey)
                        bookmarkTab.customView?.findViewById<ImageView>(R.id.tab_icon)?.imageTintList = resources.getColorStateList(R.color.button_blue)
                        supportFragmentManager.beginTransaction().replace(R.id.fragmentContainer, FavouritesFragment()).commit()
                        profileTab.customView?.findViewById<ImageView>(R.id.tab_icon)?.backgroundTintList = resources.getColorStateList(R.color.white)
                        bookmarkTab.customView?.findViewById<ImageView>(R.id.tab_icon)?.backgroundTintList = resources.getColorStateList(R.color.tablayoutBg_blue)
                        b.tabLayoutLeft.selectTab(null)
                    }
                }
            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {
                when(tab?.position){
                    0 -> {
                        profileTab.customView?.findViewById<ImageView>(R.id.tab_icon)?.imageTintList =
                            resources.getColorStateList(R.color.grey)
                        bookmarkTab.customView?.findViewById<ImageView>(R.id.tab_icon)?.imageTintList =
                            resources.getColorStateList(R.color.grey)
                        profileTab.customView?.findViewById<ImageView>(R.id.tab_icon)?.backgroundTintList =
                            resources.getColorStateList(R.color.white)
                        bookmarkTab.customView?.findViewById<ImageView>(R.id.tab_icon)?.backgroundTintList =
                            resources.getColorStateList(R.color.white)
                    }
                    1->{
                        profileTab.customView?.findViewById<ImageView>(R.id.tab_icon)?.imageTintList =
                            resources.getColorStateList(R.color.grey)
                        bookmarkTab.customView?.findViewById<ImageView>(R.id.tab_icon)?.imageTintList = resources.getColorStateList(R.color.grey)
                        profileTab.customView?.findViewById<ImageView>(R.id.tab_icon)?.backgroundTintList =
                            resources.getColorStateList(R.color.white)
                        bookmarkTab.customView?.findViewById<ImageView>(R.id.tab_icon)?.backgroundTintList = resources.getColorStateList(R.color.white)
                    }
                }

            }

            override fun onTabReselected(tab: TabLayout.Tab?) {

            }
        })


    }
}