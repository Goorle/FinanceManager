package com.example.financemanager.domain.model

enum class TransactionCategories(vararg val types: TransactionType){
    FOOD(TransactionType.EXPENSE),
    TRANSPORT(TransactionType.EXPENSE),
    MEDICINE(TransactionType.EXPENSE),
    GROCERIES(TransactionType.EXPENSE),
    RENT(TransactionType.EXPENSE),
    GIFTS(TransactionType.EXPENSE, TransactionType.INCOME),
    SAVINGS(TransactionType.INCOME),
    ENTERTAINMENT(TransactionType.EXPENSE),
    SALARY(TransactionType.INCOME),
    OTHER(TransactionType.EXPENSE, TransactionType.INCOME)
}