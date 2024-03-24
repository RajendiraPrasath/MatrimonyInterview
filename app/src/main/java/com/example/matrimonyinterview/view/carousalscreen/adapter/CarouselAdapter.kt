package com.example.matrimonyinterview.view.carousalscreen.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.matrimonyinterview.databinding.ItemcarouselBinding
import com.example.matrimonyinterview.model.Customer

typealias backArrowClick = (boolean: Boolean) -> Unit

class CarouselAdapter(private val backArrowClick: backArrowClick) :
    RecyclerView.Adapter<CarouselAdapter.CarouselItemViewHolder>() {

    var customerList: MutableList<Customer?> = mutableListOf()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    class CarouselItemViewHolder(val binding: ItemcarouselBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CarouselItemViewHolder {
        return CarouselItemViewHolder(
            ItemcarouselBinding.inflate(
                LayoutInflater.from(
                    parent.context
                ), parent, false
            )
        )
    }

    override fun onBindViewHolder(holder: CarouselItemViewHolder, position: Int) {
        holder.binding.customer = customerList[position]
        val currentPosition = position + 1
        holder.binding.adapter = this
        holder.binding.position = currentPosition.toString()
    }

    override fun getItemCount(): Int {
        return customerList.size
    }

    fun onBackArrowClick() {
        backArrowClick.invoke(true)
    }

}