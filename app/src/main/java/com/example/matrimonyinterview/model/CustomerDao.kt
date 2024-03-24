package com.example.matrimonyinterview.model

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import androidx.room.Update
import kotlinx.coroutines.flow.Flow

@Dao
interface CustomerDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addCustomer(customer: Customer): Long

    @Query("SELECT * FROM customer")
    fun getAllCustomer(): Flow<List<Customer>>

    @Update
    suspend fun update(customer: Customer): Int

    @Transaction
    suspend fun insertOrUpdate(customer: Customer): Long {
        val id = addCustomer(customer)
        return if (id == -1L) {
            update(customer)
            customer.id
        } else {
            id
        }
    }
}