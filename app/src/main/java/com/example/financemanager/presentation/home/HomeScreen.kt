package com.example.financemanager.presentation.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.financemanager.domain.model.Transaction
import com.example.financemanager.domain.model.TransactionCategories
import com.example.financemanager.domain.model.TransactionType
import com.example.financemanager.presentation.components.BottomBar
import com.example.financemanager.presentation.components.CardCategory
import com.example.financemanager.presentation.components.TopBarHome
import com.example.financemanager.presentation.home.components.BalanceComponent
import com.example.financemanager.presentation.home.components.ProgressBarComponent
import com.example.financemanager.presentation.home.components.StatisticComponent
import com.example.financemanager.ui.theme.CaribbeanGreen
import com.example.financemanager.ui.theme.HoneyDew
import com.example.financemanager.ui.theme.Void
import java.time.LocalDate
import java.util.UUID

@Composable
fun HomeScreen() {
    val listTransaction = listOf(
        Transaction(
            id = UUID.randomUUID(),
            title = "Food",
            message = "TEST MESSAGE",
            amount = 100.1,
            type = TransactionType.EXPENSE,
            category = TransactionCategories.FOOD,
            date = LocalDate.now()
        ),
        Transaction(
            id = UUID.randomUUID(),
            title = "Groceries",
            message = "TEST MESSAGE",
            amount = 20.2,
            type = TransactionType.EXPENSE,
            category = TransactionCategories.GROCERIES,
            date = LocalDate.now()
        ),
        Transaction(
            id = UUID.randomUUID(),
            title = "Medicine",
            message = "TEST MESSAGE",
            amount = 56.4,
            type = TransactionType.EXPENSE,
            category = TransactionCategories.MEDICINE,
            date = LocalDate.now()
        ),Transaction(
            id = UUID.randomUUID(),
            title = "Entertainment",
            message = "TEST MESSAGE",
            amount = 35.4,
            type = TransactionType.EXPENSE,
            category = TransactionCategories.ENTERTAINMENT,
            date = LocalDate.now()
        ),Transaction(
            id = UUID.randomUUID(),
            title = "Salary",
            message = "TEST MESSAGE",
            amount = 1_00_000.4,
            type = TransactionType.INCOME,
            category = TransactionCategories.SALARY,
            date = LocalDate.now()
        ),Transaction(
            id = UUID.randomUUID(),
            title = "Gifts",
            message = "TEST MESSAGE",
            amount = 150.00,
            type = TransactionType.EXPENSE,
            category = TransactionCategories.GIFTS,
            date = LocalDate.now()
        ),
    )

    Scaffold(
        containerColor = CaribbeanGreen,
        topBar = {
            TopBarHome()
        },
        bottomBar = {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(HoneyDew)
            ){
                BottomBar()
            }
        }
    ) { innerPadding ->

        Column(
            modifier =  Modifier.padding(innerPadding)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight(0.2f),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.SpaceEvenly
            ) {
                BalanceComponent()

                Box(
                    modifier = Modifier
                        .fillMaxWidth(0.8f)
                        .height(25.dp)
                        .clip(RoundedCornerShape(14.dp))
                        .background(Void)
                        .align(Alignment.CenterHorizontally),
                    contentAlignment = Alignment.CenterStart
                ) {
                    ProgressBarComponent()
                }

                StatisticComponent()
            }
            Box(
                modifier = Modifier.fillMaxSize(),

                ) {
                Box(
                    modifier = Modifier
                        .align(Alignment.BottomCenter)
                        .fillMaxWidth()
                        .fillMaxHeight(0.9f)
                        .clip(RoundedCornerShape(topStart = 72.dp, topEnd = 72.dp))
                        .background(HoneyDew)
                        .padding(top = 50.dp, start = 5.dp, end = 5.dp, bottom = 10.dp),
                ) {
                    LazyColumn(
                        verticalArrangement = Arrangement.spacedBy(30.dp)
                    ) {
                        items(listTransaction) { transaction ->
                            CardCategory(transaction)
                        }
                    }
                }
            }
        }
    }

}

@Preview
@Composable
fun HomePreview() {
    HomeScreen()
}