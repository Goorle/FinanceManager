package com.example.financemanager.presentation.addExpenses

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.example.financemanager.domain.model.repositoiry.TransactionRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class AddExpenseViewModel @Inject constructor(
    private val repository: TransactionRepository
): ViewModel() {
    var expandedDropDownMenu by mutableStateOf(false)
    var textSelectCategory by mutableStateOf("Select category")
}