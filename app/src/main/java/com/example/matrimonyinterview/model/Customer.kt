package com.example.matrimonyinterview.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(tableName = "customer", indices = [Index(value = ["name"], unique = true)])
data class Customer(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    var id: Long = 0,
    @ColumnInfo(name = "name")
    val name: String,
    @ColumnInfo(name = "age")
    val age: String,
    @ColumnInfo(name = "height")
    val height: String,
    @ColumnInfo(name = "language")
    val language: String,
    @ColumnInfo(name = "designation")
    val designation: String,
    @ColumnInfo(name = "image")
    val image: String,
    @ColumnInfo(name = "address")
    val address: String
)
