package com.example.financemanager.presentation.components.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.financemanager.domain.model.TransactionType
import com.example.financemanager.domain.model.repositoiry.TransactionRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

@HiltViewModel
class BalanceViewModel @Inject constructor(
    private val repository: TransactionRepository) : ViewModel() {
     val totalExpense: StateFlow<Double> = repository.getTotalByType(TransactionType.EXPENSE.name)
        .stateIn(
            viewModelScope,
            SharingStarted.WhileSubscribed(5000),
            0.0
        )
    val totalIncome: StateFlow<Double> = repository.getTotalByType(TransactionType.INCOME.name)
        .stateIn(
            viewModelScope,
            SharingStarted.WhileSubscribed(5000),
            0.0
        )

    val totalBalance: StateFlow<Double> = combine(totalIncome, totalExpense){ income, expense ->
        income - expense
    }.stateIn(
        viewModelScope,
        SharingStarted.WhileSubscribed(5000),
        0.0
    )

    val percentExpense: StateFlow<Double> = combine(totalIncome, totalExpense){ income, expense ->
        if (income == 0.0) 0.0
        else (expense/income * 100).coerceIn(0.0, 100.0)
    }.stateIn(
        viewModelScope,
        SharingStarted.WhileSubscribed(5000),
        0.0
    )
}