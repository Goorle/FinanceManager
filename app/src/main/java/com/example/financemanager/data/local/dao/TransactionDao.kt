package com.example.financemanager.data.local.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.financemanager.data.local.entity.TransactionEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface TransactionDao {
    @Query("SELECT * FROM `transaction` ORDER BY date DESC, id DESC")
    fun getAllTransactions(): Flow<List<TransactionEntity>>

    @Query("SELECT * FROM `transaction` WHERE category = :category ORDER BY date DESC, id DESC")
    fun getTransactionByCategory(category: String): Flow<List<TransactionEntity>>

    @Query("SELECT * FROM `transaction` WHERE type = :type ORDER BY date DESC, id DESC")
    fun getTransactionByType(type: String): Flow<List<TransactionEntity>>

    @Query("SELECT COALESCE(SUM(amount), 0.0) FROM `transaction` WHERE type = :type")
    fun getTotalByType(type: String): Flow<Double>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(transaction: TransactionEntity): Long

    @Delete
    suspend fun delete(transaction: TransactionEntity)
}