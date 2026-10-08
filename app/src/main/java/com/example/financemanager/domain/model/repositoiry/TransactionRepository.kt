package com.example.financemanager.domain.model.repositoiry

import com.example.financemanager.domain.model.Category
import com.example.financemanager.domain.model.Transaction
import com.example.financemanager.domain.model.TransactionType
import kotlinx.coroutines.flow.Flow

interface TransactionRepository {
    fun getAllTransaction(): Flow<List<Transaction>>
    fun getTransactionByCategory(category: Category): Flow<List<Transaction>>

    fun getTransactionByType(type: TransactionType): Flow<List<Transaction>>

    fun getTotalByType(type: String): Flow<Double>

    suspend fun addTransaction(transaction: Transaction)
    suspend fun deleteTransaction(transaction: Transaction)
}