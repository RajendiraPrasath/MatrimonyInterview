package com.example.matrimonyinterview.repository

import com.example.matrimonyinterview.model.Customer
import com.example.matrimonyinterview.model.CustomerDao
import kotlinx.coroutines.flow.Flow

class CustomerRepository(private val customerDao: CustomerDao) {

    suspend fun addCustomer(customer: Customer) = customerDao.insertOrUpdate(customer)

    fun getAllCustomer(): Flow<List<Customer>> = customerDao.getAllCustomer()
}