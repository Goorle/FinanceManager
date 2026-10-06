package com.example.financemanager.domain.model

import java.time.LocalDate
import java.util.UUID

data class Transaction(
    val id: Long = 0,
    val title: String,
    val message: String,
    val amount: Double,
    val type: TransactionType,
    val category: TransactionCategories,
    val date: LocalDate,
    )