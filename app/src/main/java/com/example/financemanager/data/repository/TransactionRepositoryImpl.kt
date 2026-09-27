package com.example.financemanager.data.repository

import com.example.financemanager.data.local.dao.TransactionDao
import com.example.financemanager.data.local.entity.TransactionEntity
import com.example.financemanager.domain.model.Transaction
import com.example.financemanager.domain.model.TransactionCategories
import com.example.financemanager.domain.model.TransactionType
import com.example.financemanager.domain.model.repositoiry.TransactionRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import java.time.Instant
import java.time.ZoneId
import javax.inject.Inject
import kotlin.collections.map

class TransactionRepositoryImpl @Inject constructor(
    private val dao: TransactionDao
): TransactionRepository {
    override fun getAllTransaction(): Flow<List<Transaction>>  {
        return dao.getAllTransactions().map { entities ->
            entities.map { entity -> mapEntityToDomain(entity) }
        }
    }

    override fun getTransactionByCategory(category: String): Flow<List<Transaction>> {
        return dao.getTransactionByCategory(category).map{entities ->
            entities.map { entity -> mapEntityToDomain(entity) }
        }
    }

    override suspend fun addTransaction(transaction: Transaction) {
        val entity = mapDomainToEntity(transaction)
        dao.insert(entity)
    }

    override suspend fun deleteTransaction(transaction: Transaction) {
        val entity = mapDomainToEntity(transaction)
        dao.delete(entity)
    }

    override fun getTotalByType(type: String): Flow<Double> {
        return dao.getTotalByType(type)
    }

    private fun mapEntityToDomain(entity: TransactionEntity): Transaction {
        val localDate = Instant.ofEpochMilli(entity.date)
            .atZone(ZoneId.systemDefault())
            .toLocalDate()

        return Transaction(
            id = entity.id,
            title = entity.title,
            message = entity.message,
            amount = entity.amount,
            type = TransactionType.valueOf(entity.type),
            category = TransactionCategories.valueOf(entity.category),
            date = localDate,
        )
    }


    private fun mapDomainToEntity(transaction: Transaction): TransactionEntity {
        val epochMilli = transaction.date
            .atStartOfDay(ZoneId.systemDefault())
            .toInstant()
            .toEpochMilli()

        return TransactionEntity(
            id = transaction.id,
            title = transaction.title,
            message = transaction.message,
            date = epochMilli,
            amount = transaction.amount,
            type = transaction.type.name,
            category = transaction.category.name
        )
    }
}