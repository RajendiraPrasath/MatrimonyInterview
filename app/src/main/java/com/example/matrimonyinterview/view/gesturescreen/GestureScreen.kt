package com.example.matrimonyinterview.view.gesturescreen

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.example.matrimonyinterview.databinding.GuesterscreenBinding
import com.example.matrimonyinterview.utills.SliderTransformer
import com.example.matrimonyinterview.view.gesturescreen.adapter.GestureAdapter
import com.example.matrimonyinterview.viewmodel.GestureScreenViewModel
import kotlinx.coroutines.launch

class GestureScreen : AppCompatActivity() {
    private lateinit var gestureScreenBinding: GuesterscreenBinding
    private lateinit var gestureAdapter: GestureAdapter
    private lateinit var viewModel: GestureScreenViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        gestureScreenBinding = GuesterscreenBinding.inflate(layoutInflater)
        setContentView(gestureScreenBinding.root)
        viewModel = ViewModelProvider.AndroidViewModelFactory(application)
            .create(GestureScreenViewModel::class.java)
        setAdapter()
        backAction()
        setViewPager()
    }

    private fun setViewPager() {
        gestureScreenBinding.guesterViewpager.apply {
            adapter = gestureAdapter
            offscreenPageLimit = 3
            setPageTransformer(SliderTransformer(3))

        }
    }

    private fun setAdapter() {
        gestureAdapter = GestureAdapter {
            gestureScreenBinding.emptyNotification.visibility = View.VISIBLE
        }
        lifecycleScope.launch {
            viewModel.getAllCustomer().collect {
                gestureAdapter.customerList = it.toMutableList()
            }
        }

    }

    private fun backAction() {
        gestureScreenBinding.gestureBackArrow.setOnClickListener {
            this.finish()
        }
    }

}