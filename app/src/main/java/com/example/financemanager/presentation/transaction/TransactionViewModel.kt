package com.example.financemanager.presentation.transaction

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.financemanager.domain.model.Transaction
import com.example.financemanager.domain.model.TransactionType
import com.example.financemanager.domain.model.repositoiry.TransactionRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.stateIn
import java.text.NumberFormat
import java.time.LocalDate
import java.util.Locale
import javax.inject.Inject

@HiltViewModel
class TransactionViewModel @Inject constructor(
    private val repository: TransactionRepository
): ViewModel() {
    private val _selectedType = MutableStateFlow<TransactionType?>(null)
    val selectedType = _selectedType.asStateFlow()

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

    val totalBalance: StateFlow<Double> = combine(totalIncome, totalExpense) { income, expense ->
        income - expense
    }.stateIn(
        viewModelScope,
        SharingStarted.WhileSubscribed(5000),
        0.0
    )

    fun printDate(currentDate: LocalDate, previousDate: LocalDate?): Boolean = currentDate != previousDate

    @OptIn(ExperimentalCoroutinesApi::class)
    val transaction: StateFlow<List<Transaction>> = selectedType
        .flatMapLatest { type ->
            if (type == null) {
                repository.getAllTransaction()
            } else {
                repository.getTransactionByType(type)
            }
        }
        .stateIn(
            viewModelScope,
            SharingStarted.WhileSubscribed(5000),
            listOf()
        )

    fun selectType(type: TransactionType?) {
        if (_selectedType.value != null && _selectedType.value == type) {
            _selectedType.value = null
        } else {
            _selectedType.value = type
        }
    }

    fun formatCurrency(amount: Double): String {
        val formatter = NumberFormat.getNumberInstance(Locale.FRANCE).apply {
            minimumFractionDigits = 2
            maximumFractionDigits = 2
        }

        return "${formatter.format(amount)} ₽"
    }
}