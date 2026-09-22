package com.example.financemanager.presentation.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier


@Composable
fun BalanceOverview() {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight(0.2f),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceEvenly
    ) {
        BalanceComponent()
        ProgressBarComponent()
        StatisticComponent()
    }
}