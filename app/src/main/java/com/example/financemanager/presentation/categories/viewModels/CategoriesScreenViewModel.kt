package com.example.financemanager.presentation.categories.viewModels

import androidx.lifecycle.ViewModel
import com.example.financemanager.domain.model.repositoiry.TransactionRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class CategoriesScreenViewModel @Inject constructor(
    private val repository: TransactionRepository
): ViewModel() {


}