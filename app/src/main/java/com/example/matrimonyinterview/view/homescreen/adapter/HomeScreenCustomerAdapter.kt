package com.example.matrimonyinterview.view.homescreen.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.matrimonyinterview.R
import com.example.matrimonyinterview.databinding.HomescreenListItemBinding
import com.example.matrimonyinterview.model.Customer
import com.example.matrimonyinterview.utills.Utils

typealias buttonClick = (boolean: Boolean) -> Unit
typealias OpenProfilePage = (boolean: Boolean) -> Unit

class HomeScreenAdapter(
    private val buttonClick: buttonClick,
    private val openProfilePage: OpenProfilePage
) :
    RecyclerView.Adapter<HomeScreenAdapter.HomeScreenViewHolder>() {

    var customerList: MutableList<Customer?> = mutableListOf()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    inner class HomeScreenViewHolder(val binding: HomescreenListItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomeScreenViewHolder {
        return HomeScreenViewHolder(
            HomescreenListItemBinding.inflate(
                LayoutInflater.from(
                    parent.context
                ), parent, false
            )
        )
    }

    override fun onBindViewHolder(holder: HomeScreenViewHolder, position: Int) {
        holder.binding.customer = customerList[position]
        holder.binding.adapter = this
    }

    //Removing clicked profile
    fun removeParticularPosition(context: Context,customer: Customer) {
        customerList.remove(customer)
        Utils.customerList.remove(customer)
        buttonClick.invoke(true)
        Utils.showToast(context,context.resources.getString(R.string.button_click_message))
        notifyDataSetChanged()
    }

    //Open product details page
    fun openProfileDetailsPage() {
        openProfilePage.invoke(true)
    }

    override fun getItemCount(): Int {
        return customerList.size
    }
}