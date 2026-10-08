package com.example.financemanager.presentation.addExpenses

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.toRoute
import com.example.financemanager.domain.model.Category
import com.example.financemanager.domain.model.Transaction
import com.example.financemanager.domain.model.TransactionType
import com.example.financemanager.domain.model.repositoiry.TransactionRepository
import com.example.financemanager.presentation.navigation.RoutesScreen
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import java.time.Instant
import java.time.ZoneId
import javax.inject.Inject


@HiltViewModel
class AddExpenseViewModel @Inject constructor(
    private val repository: TransactionRepository,
    savedStateHandle: SavedStateHandle
): ViewModel() {

    val category = savedStateHandle.toRoute<RoutesScreen.CategoryDetails>().category
    private val _uiState = MutableStateFlow(AddExpenseUiState())
    val uiState: StateFlow<AddExpenseUiState> = _uiState.asStateFlow()

    private val _events = Channel<TransactionEvent>()
    val events = _events.receiveAsFlow()

    init {
        selectCategory(category.name)
    }

    fun onAmountChanged(value: String) {
        _uiState.update {
            it.copy(amount = value.replace(',', '.'), amountError = null)
        }
    }

    fun selectCategory(category: String) {
        _uiState.update{
            it.copy(selectedCategory = category, categoryError = null)
        }
    }

    fun selectedDate(newDate: Long) {
        val newDate = Instant.ofEpochMilli(newDate).atZone(ZoneId.systemDefault()).toLocalDate()
        _uiState.update{
            it.copy(selectedDate = newDate)
        }
    }

    fun titleChanged(title: String) {
        _uiState.update{
            it.copy(selectTitle = title, titleError = null)
        }
    }

    fun messageChanged(message: String) {
        _uiState.update {
            it.copy(selectMessage = message, messageError = null)
        }
    }

    fun changeExpandDropDownMenu(isOpen: Boolean) {
        _uiState.update{
            it.copy(expandedDropDownMenu = isOpen)
        }
    }

    fun showDateDialog(isShow: Boolean) {
        _uiState.update {
            it.copy(showDatePicker = isShow)
        }
    }

    suspend fun validateTransaction(): Boolean {
        val state = _uiState.value

        val amountValue = state.amount.toDoubleOrNull()
        val amountError = when {
            state.amount.isBlank() -> "Введите сумму"
            amountValue == null -> "Неккоректная сумма"
            amountValue <= 0 -> "Сумма должна быть больше нуля"
            else -> {
                null
            }
        }
        val categoryError = if (state.selectedCategory.isEmpty()) "Выберите категорию" else null
        val titleError = if (state.selectTitle.isEmpty()) "Выберите название" else null
        val messageError = if(state.selectMessage.isEmpty()) "Напишите сообщение" else null

        if (amountError != null || categoryError != null || titleError != null || messageError != null) {
            _uiState.update {
                it.copy(
                    amountError = amountError,
                    categoryError = categoryError,
                    titleError = titleError,
                    messageError = messageError
                )
            }
            _events.send(TransactionEvent.Error("Не удалость сохранить"))
            return false
        }
        return  true

    }
    fun saveTransaction() {

        viewModelScope.launch {
            val state = _uiState.value
            try {
                if (validateTransaction()) {
                    _uiState.update {
                        it.copy(
                            isSaving = true
                        )
                    }
                    val transaction = Transaction(
                        title = state.selectTitle,
                        message = state.selectMessage,
                        amount = state.amount.toDouble(),
                        category = Category.valueOf(state.selectedCategory),
                        date = state.selectedDate,
                        type = getType(state.selectedCategory)
                    )

                    repository.addTransaction(transaction)
                    _events.send(TransactionEvent.SavedSuccessfully)
                }
            } catch (e: Exception) {
                _events.send(TransactionEvent.Error("Не удалость сохранить: ${e.message}"))
            } finally {
                _uiState.update {
                    it.copy(
                        isSaving = false
                    )
                }
            }

        }
    }


    fun getType(category: String): TransactionType  {
        return when(category) {
            Category.SALARY.name ->
                TransactionType.INCOME
            else -> {
                TransactionType.EXPENSE
            }
        }
    }
}