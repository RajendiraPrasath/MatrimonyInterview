package com.example.matrimonyinterview.model

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.matrimonyinterview.R

@Database(
    entities = [Customer::class],
    version = 1,
    exportSchema = true
)
abstract class CustomerDataBase : RoomDatabase() {

    abstract fun customerDao(): CustomerDao

    companion object {

        @Volatile
        private var INSTANCE: CustomerDataBase? = null

        fun getDatabase(context: Context): CustomerDataBase {
            if (INSTANCE == null) {
                synchronized(this) {
                    INSTANCE = buildDatabase(context)
                }
            }
            return INSTANCE!!
        }

        private fun buildDatabase(context: Context): CustomerDataBase {
            return Room.databaseBuilder(
                context.applicationContext,
                CustomerDataBase::class.java,
                context.resources.getString(R.string.database_name)
            )
                .build()
        }
    }
}