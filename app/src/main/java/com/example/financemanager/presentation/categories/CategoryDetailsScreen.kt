package com.example.financemanager.presentation.categories

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.financemanager.domain.model.Category
import com.example.financemanager.domain.model.Transaction
import com.example.financemanager.domain.model.TransactionCategories
import com.example.financemanager.domain.model.TransactionType
import com.example.financemanager.presentation.categories.components.CardTransaction
import com.example.financemanager.presentation.components.BalanceComponent
import com.example.financemanager.presentation.components.BalanceOverview
import com.example.financemanager.presentation.components.ProgressBarComponent
import com.example.financemanager.presentation.components.StatisticComponent
import com.example.financemanager.ui.theme.HoneyDew
import java.time.LocalDate
import java.util.UUID

@Composable
fun CategoryDetails(category: Category) {
    val listEntertainment = listOf(
        Transaction(
            id = UUID.randomUUID(),
            title = "Salary",
            message = "TEST MESSAGE",
            amount = 120.4,
            type = TransactionType.EXPENSE,
            category = TransactionCategories.ENTERTAINMENT,
            date = LocalDate.now()
        ),
        Transaction(
            id = UUID.randomUUID(),
            title = "Salary",
            message = "TEST MESSAGE",
            amount = 120.4,
            type = TransactionType.EXPENSE,
            category = TransactionCategories.ENTERTAINMENT,
            date = LocalDate.now()
        ),
        Transaction(
            id = UUID.randomUUID(),
            title = "Salary",
            message = "TEST MESSAGE",
            amount = 120.4,
            type = TransactionType.EXPENSE,
            category = TransactionCategories.ENTERTAINMENT,
            date = LocalDate.now()
        ),
        Transaction(
            id = UUID.randomUUID(),
            title = "Salary",
            message = "TEST MESSAGE",
            amount = 120.4,
            type = TransactionType.EXPENSE,
            category = TransactionCategories.ENTERTAINMENT,
            date = LocalDate.now()
        ),
        Transaction(
            id = UUID.randomUUID(),
            title = "Salary",
            message = "TEST MESSAGE",
            amount = 120.4,
            type = TransactionType.EXPENSE,
            category = TransactionCategories.ENTERTAINMENT,
            date = LocalDate.now()
        ),
    )
    Column(
        modifier = Modifier
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        BalanceOverview()
        Box(
            modifier = Modifier
                .fillMaxSize()
                .fillMaxHeight(0.9f)
                .clip(RoundedCornerShape(topStart = 72.dp, topEnd = 72.dp))
                .background(HoneyDew),
        ) {
            LazyColumn(
                verticalArrangement = Arrangement.spacedBy(30.dp)
            ) {
                items(listEntertainment) { item ->
                    CardTransaction(item)
                }
            }
        }
    }
}

@Preview
@Composable
fun CategoryDetailsPreview(){
    val category: Category = Category.FOOD
    CategoryDetails(category)
}