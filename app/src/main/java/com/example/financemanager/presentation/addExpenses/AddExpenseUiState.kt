package com.example.financemanager.presentation.addExpenses

import java.time.LocalDate

data class AddExpenseUiState(
    val amount: String = "",
    val amountError: String? = null,
    val selectedCategory: String = "",
    val categoryError: String? = null,
    val selectedDate: LocalDate = LocalDate.now(),
    val selectTitle: String = "",
    val titleError: String? = null,
    val selectMessage: String = "",
    val messageError: String? = null,
    val showDatePicker: Boolean = false,
    val expandedDropDownMenu: Boolean = false,
    val isSaving: Boolean = false
)
