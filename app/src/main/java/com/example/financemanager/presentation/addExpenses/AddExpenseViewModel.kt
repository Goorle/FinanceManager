package com.example.financemanager.presentation.addExpenses

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableLongStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.financemanager.domain.model.Transaction
import com.example.financemanager.domain.model.TransactionCategories
import com.example.financemanager.domain.model.TransactionType
import com.example.financemanager.domain.model.repositoiry.TransactionRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import java.time.Instant
import java.time.LocalDate
import java.time.ZoneId
import javax.inject.Inject


@HiltViewModel
class AddExpenseViewModel @Inject constructor(
    private val repository: TransactionRepository
): ViewModel() {
    var expandedDropDownMenu by mutableStateOf(false)
    var textSelectCategory by mutableStateOf("Select category")
    var textSelectAmount by mutableStateOf("")
    var textSelectTitle by mutableStateOf("")
    var textSelectMessage by mutableStateOf("")
    var showDatePicker by mutableStateOf(false)

    var currentDate by mutableStateOf(LocalDate.now())

    init {

    }
    fun changeDate(newDate: Long) {
        currentDate = Instant.ofEpochMilli(newDate).atZone(ZoneId.systemDefault()).toLocalDate()
    }

    fun addExpense() {
        val transaction = Transaction(
            title = textSelectTitle,
            message = textSelectMessage,
            amount = textSelectAmount.toDouble(),
            category = TransactionCategories.valueOf(textSelectCategory),
            date = currentDate,
            type = getType(textSelectCategory)
        )
        viewModelScope.launch {
            try {
                repository.addTransaction(transaction)
                Log.d("Transaction", "Success")
            } catch (e: Exception) {
                Log.e("Transaction", e.message.toString())
            }

        }
    }

    fun getType(category: String): TransactionType  {
        return when(category) {
            TransactionCategories.SALARY.name ->
                TransactionType.INCOME
            else -> {
                TransactionType.EXPENSE
            }
        }
    }
}