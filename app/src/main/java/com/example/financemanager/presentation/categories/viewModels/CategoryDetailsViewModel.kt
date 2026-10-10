package com.example.financemanager.presentation.categories.viewModels

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.toRoute
import com.example.financemanager.domain.model.repositoiry.TransactionRepository
import com.example.financemanager.presentation.navigation.RoutesScreen
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn
import java.time.LocalDate
import javax.inject.Inject

@HiltViewModel
class CategoryDetailsViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val repository: TransactionRepository
): ViewModel() {
    val category = savedStateHandle.toRoute<RoutesScreen.CategoryDetails>().category

    val transaction = repository.getTransactionByCategory(category)
        .stateIn(
            viewModelScope,
            SharingStarted.WhileSubscribed(5000),
            emptyList()
        )
    fun printDate(currentDate: LocalDate, previousDate: LocalDate?): Boolean = currentDate != previousDate
}