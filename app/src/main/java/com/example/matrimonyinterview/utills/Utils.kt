package com.example.matrimonyinterview.utills

import com.example.matrimonyinterview.model.Customer

class Utils {
    companion object {
        val customerList = mutableSetOf<Customer>()
        val input = listOf(
            Customer(
                name = "Sachin",
                age = "37 yrs",
                height = "5 ft 2 in",
                language = "Hindi",
                designation = "Cricketer",
                image = "sachin",
                address = "Mumbai,India"
            ),
            Customer(
                name = "Sehwag",
                age = "39 yrs",
                height = "5 ft 9 in",
                language = "Hindi",
                designation = "Cricketer",
                image = "sehwag",
                address = "Mumbai,India"
            ),
            Customer(
                name = "Yuvraj",
                age = "35 yrs",
                height = "6 ft 1 in",
                language = "Hindi",
                designation = "Cricketer",
                image = "yuvraj",
                address = "Mumbai,India"
            ),
            Customer(
                name = "Dhoni",
                age = "38 yrs",
                height = "6 ft 0 in",
                language = "Hindi",
                designation = "Cricketer",
                image = "dhoni",
                address = "Mumbai,India"
            ),
            Customer(
                name = "Kohli",
                age = "35 yrs",
                height = "5 ft 7 in",
                language = "Hindi",
                designation = "Cricketer",
                image = "kohli",
                address = "Mumbai,India"
            )
        )
    }
}