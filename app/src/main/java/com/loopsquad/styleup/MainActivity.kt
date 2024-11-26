package com.loopsquad.styleup

import android.os.Bundle
import android.view.LayoutInflater
import android.widget.ImageView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.loopsquad.styleup.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
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

    }
}