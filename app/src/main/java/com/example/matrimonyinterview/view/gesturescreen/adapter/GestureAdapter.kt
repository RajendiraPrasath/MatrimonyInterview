package com.example.matrimonyinterview.view.gesturescreen.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.matrimonyinterview.databinding.GuesteritemsBinding
import com.example.matrimonyinterview.model.Customer

typealias NotifyEmptyLayout = () -> Unit

class GestureAdapter(private val notifyEmptyLayout: NotifyEmptyLayout) :
    RecyclerView.Adapter<GestureAdapter.GestureItemViewHolder>() {

    var customerList: MutableList<Customer?> = mutableListOf()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    class GestureItemViewHolder(val binding: GuesteritemsBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GestureItemViewHolder {
        return GestureItemViewHolder(
            GuesteritemsBinding.inflate(
                LayoutInflater.from(
                    parent.context
                ), parent, false
            )
        )
    }

    override fun onBindViewHolder(holder: GestureItemViewHolder, position: Int) {
        holder.binding.customer = customerList[position]
        holder.binding.adapter = this
    }

    override fun getItemCount(): Int {
        return customerList.size
    }

    fun removeParticularPosition(customer: Customer) {
        customerList.remove(customer)
        if (customerList.size == 0) {
            notifyEmptyLayout.invoke()
        }
        notifyDataSetChanged()
    }
}