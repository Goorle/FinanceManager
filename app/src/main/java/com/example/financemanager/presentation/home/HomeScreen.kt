package com.example.financemanager.presentation.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.financemanager.presentation.components.BalanceOverview
import com.example.financemanager.presentation.components.CardWithMessage
import com.example.financemanager.presentation.home.components.SegmentedComponent
import com.example.financemanager.ui.theme.HoneyDew
import com.example.financemanager.ui.theme.PoppinsFontFamily
import com.example.financemanager.ui.theme.Void
import java.time.LocalDate
import java.time.format.DateTimeFormatter.ofPattern

@Composable
fun HomeScreen(
    viewModel: HomeViewModel = hiltViewModel()
) {
    val transactions by viewModel.transaction.collectAsStateWithLifecycle()

    Column(
        horizontalAlignment = Alignment.CenterHorizontally
    ){
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
                    verticalArrangement = Arrangement.spacedBy(5.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    SegmentedComponent()

                    LazyColumn(
                        verticalArrangement = Arrangement.spacedBy(15.dp)
                    ) {
                        itemsIndexed(
                            items = transactions,
                        ) { index, transaction ->
                            val currentDate = transaction.date
                            val previousDate = transactions.getOrNull(index - 1)?.date

                            if (viewModel.printDate(currentDate, previousDate)) {
                                Spacer(Modifier.height(20.dp))
                                MonthHeaderHome(currentDate)
                                Spacer(Modifier.height(15.dp))
                            }
                            CardWithMessage(transaction)
                        }
                    }
                }
        }
    }
}

@Composable
fun MonthHeaderHome(
    date: LocalDate
) {
    val localDate = LocalDate.now()
    val text = when (date) {
        localDate -> {
            "Сегодня"
        }
        localDate.minusDays(1) -> {
            "Вчера"
        }
        else -> {
            date.format(ofPattern("d MMMM yyyy"))
        }
    }
    Text(
        text = text,
        fontFamily = PoppinsFontFamily,
        fontSize = 16.sp,
        fontWeight = FontWeight.Medium,
        color = Void
    )
}

@Preview
@Composable
fun HomePreview() {
    HomeScreen()
}