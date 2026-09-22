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
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.financemanager.domain.model.Transaction
import com.example.financemanager.domain.model.TransactionCategories
import com.example.financemanager.domain.model.TransactionType
import com.example.financemanager.presentation.components.CardCategory
import com.example.financemanager.presentation.components.BalanceComponent
import com.example.financemanager.presentation.components.BalanceOverview
import com.example.financemanager.presentation.components.ProgressBarComponent
import com.example.financemanager.presentation.home.components.SegmentedComponent
import com.example.financemanager.presentation.components.StatisticComponent
import com.example.financemanager.ui.theme.HoneyDew
import com.example.financemanager.ui.theme.Void
import java.time.LocalDate
import java.util.UUID

@Composable
fun HomeScreen() {
    val listTransaction: List<Transaction> = listOf()
    Column{
        BalanceOverview()
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .fillMaxHeight(0.9f)
                    .clip(RoundedCornerShape(topStart = 72.dp, topEnd = 72.dp))
                    .background(HoneyDew),
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 5.dp, start = 15.dp, end = 15.dp),
                    verticalArrangement = Arrangement.spacedBy(15.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    SegmentedComponent()

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

@Preview
@Composable
fun HomePreview() {
    HomeScreen()
}