package com.example.financemanager.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.financemanager.data.local.dao.TransactionDao
import com.example.financemanager.data.local.entity.TransactionEntity

@Database(entities = [TransactionEntity::class], version = 1)
abstract class AppDatabase: RoomDatabase() {
    abstract fun transactionDao(): TransactionDao
}