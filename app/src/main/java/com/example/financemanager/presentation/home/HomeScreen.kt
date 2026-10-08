package com.example.financemanager.presentation.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.financemanager.presentation.components.BalanceOverview
import com.example.financemanager.presentation.components.CardWithMessage
import com.example.financemanager.presentation.home.components.SegmentedComponent
import com.example.financemanager.ui.theme.HoneyDew


@Composable
fun HomeScreen(
    viewModel: HomeViewModel = hiltViewModel()
) {
    val transactions by viewModel.transaction.collectAsStateWithLifecycle()

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
                        items(
                            items = transactions,
                            key = {it.id}
                        ) { transaction ->
                            CardWithMessage(transaction)
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