package com.example.matrimonyinterview.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.example.matrimonyinterview.model.Customer
import com.example.matrimonyinterview.model.CustomerDataBase
import com.example.matrimonyinterview.repository.CustomerRepository
import kotlinx.coroutines.flow.Flow

class GestureScreenViewModel(application: Application) : AndroidViewModel(application) {
    private val repository: CustomerRepository

    init {
        val customerDao by lazy { CustomerDataBase.getDatabase(application).customerDao() }
        repository = CustomerRepository(customerDao)
    }

    fun getAllCustomer(): Flow<List<Customer>> {
        return repository.getAllCustomer()
    }
}