package com.example.financemanager.presentation.transaction

import androidx.compose.runtime.MutableState
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
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class TransactionViewModel @Inject constructor(
    private val repository: TransactionRepository
): ViewModel() {
    private val _selectedType = MutableStateFlow<TransactionType?>(null)
    val selectedType = _selectedType.asStateFlow()

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

    fun selectedType(type: TransactionType?) {
        _selectedType.value = type
    }
}