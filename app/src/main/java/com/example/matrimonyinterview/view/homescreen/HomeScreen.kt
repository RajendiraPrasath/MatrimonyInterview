package com.example.matrimonyinterview.view.homescreen

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.matrimonyinterview.R
import com.example.matrimonyinterview.databinding.HomescreenBinding
import com.example.matrimonyinterview.utills.Utils
import com.example.matrimonyinterview.view.carousalscreen.CarouselScreen
import com.example.matrimonyinterview.view.gesturescreen.GestureScreen
import com.example.matrimonyinterview.view.homescreen.adapter.HomeScreenAdapter
import com.example.matrimonyinterview.viewmodel.HomeScreenViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class HomeScreen : AppCompatActivity() {
    private lateinit var homeScreenAdapter: HomeScreenAdapter
    private lateinit var homeScreenBinding: HomescreenBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        homeScreenBinding = HomescreenBinding.inflate(layoutInflater)
        setContentView(homeScreenBinding.root)
        setSupportActionBar(findViewById(R.id.toolbar))
        setupRecyclerView()

        val viewModel = ViewModelProvider.AndroidViewModelFactory(application)
            .create(HomeScreenViewModel::class.java)

        viewModel.addCustomer()

        lifecycleScope.launch {
            viewModel.getAllCustomer().collect {
                for (customer in it) {
                    Utils.customerList.add(customer)
                }
                homeScreenAdapter.customerList = Utils.customerList.toMutableList()
                updateRecyclerViewHeading()
            }

        }


    }

    private fun setupRecyclerView() {
        homeScreenBinding.contentMain.recyclerView.apply {
            layoutManager =
                LinearLayoutManager(applicationContext, LinearLayoutManager.HORIZONTAL, false)
            homeScreenAdapter = HomeScreenAdapter({ click ->
                if (click) {
                    if (Utils.customerList.size > 0) {
                        updateRecyclerViewHeading()
                    } else {
                        homeScreenBinding.contentMain.heading.visibility = View.INVISIBLE
                        homeScreenBinding.emptyNotification.visibility = View.VISIBLE
                    }

                }
            }, {
                startActivity(Intent(this@HomeScreen, CarouselScreen::class.java))
            })
            adapter = homeScreenAdapter

        }
    }

    @SuppressLint("SetTextI18n")
    private fun updateRecyclerViewHeading() {
        homeScreenBinding.contentMain.heading.text =
            Utils.customerList.size.toString() + " " + applicationContext.resources.getString(R.string.home_screen_profile_header)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.home_screen_menu, menu)
        menu?.getItem(0)?.icon?.setTint(applicationContext.getColor(R.color.black))
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.receiver -> {
                lifecycleScope.launch(Dispatchers.Main) {
                    startActivity(Intent(this@HomeScreen, GestureScreen::class.java))
                }

                return true
            }

            else -> super.onOptionsItemSelected(item)
        }
    }

}