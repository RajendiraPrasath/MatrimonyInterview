package com.example.matrimonyinterview.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.matrimonyinterview.model.Customer
import com.example.matrimonyinterview.model.CustomerDataBase
import com.example.matrimonyinterview.repository.CustomerRepository
import com.example.matrimonyinterview.utills.Utils
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch

class HomeScreenViewModel(application: Application) : AndroidViewModel(application) {
    private val repository: CustomerRepository

    init {
        val customerDao by lazy { CustomerDataBase.getDatabase(application).customerDao() }
        repository = CustomerRepository(customerDao)
    }

    //Add customer details to Room database
    fun addCustomer() {
        viewModelScope.launch(Dispatchers.IO) {
            for (customer in Utils.input) {
                repository.addCustomer(customer)
            }
        }
    }

    //Get customer details from room database
    fun getAllCustomer(): Flow<List<Customer>> {
        return repository.getAllCustomer()
    }
}