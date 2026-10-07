package com.example.financemanager.presentation.addExpenses

sealed interface TransactionEvent {
    object SavedSuccessfully: TransactionEvent
    data class Error(val message: String): TransactionEvent
}