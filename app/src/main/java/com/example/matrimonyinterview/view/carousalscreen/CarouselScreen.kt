package com.example.matrimonyinterview.view.carousalscreen

import android.content.res.Resources
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.doOnPreDraw
import androidx.viewpager2.widget.CompositePageTransformer
import androidx.viewpager2.widget.MarginPageTransformer
import com.example.matrimonyinterview.databinding.CarousalscreencontentBinding
import com.example.matrimonyinterview.utills.Utils
import com.example.matrimonyinterview.view.carousalscreen.adapter.CarouselAdapter
import kotlin.math.abs

class CarouselScreen : AppCompatActivity() {
    private lateinit var carouselAdapter: CarouselAdapter
    private lateinit var carousalScreenContentBinding: CarousalscreencontentBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        carousalScreenContentBinding = CarousalscreencontentBinding.inflate(layoutInflater)
        setContentView(carousalScreenContentBinding.root)
        setUpViewPager()
        setViewPagerCarousalTransition()
        setAdapter()

    }

    //Initialize adapter
    private fun setAdapter() {
        carouselAdapter = CarouselAdapter {
            this.finish()
        }
        carousalScreenContentBinding.viewpager.adapter = carouselAdapter
        carouselAdapter.customerList = Utils.customerList.toMutableList()


    }

    //Initialize viewpager2 config fields
    private fun setUpViewPager() {
        carousalScreenContentBinding.viewpager.apply {
            clipChildren = false  // No clipping the left and right items
            clipToPadding = false  // Show the viewpager in full width without clipping the padding
        }
        carousalScreenContentBinding.viewpager.doOnPreDraw {
            carousalScreenContentBinding.viewpager.currentItem = 0
        }
    }

    //Handle carousal transition
    private fun setViewPagerCarousalTransition() {
        val compositePageTransformer = CompositePageTransformer()
        compositePageTransformer.addTransformer(MarginPageTransformer((1 * Resources.getSystem().displayMetrics.density).toInt()))
        compositePageTransformer.addTransformer { page, position ->
            val r = 1 - abs(position)
            page.scaleY = (0.80f + r * 0.20f)
        }
        carousalScreenContentBinding.viewpager.setPageTransformer(compositePageTransformer)
    }

}