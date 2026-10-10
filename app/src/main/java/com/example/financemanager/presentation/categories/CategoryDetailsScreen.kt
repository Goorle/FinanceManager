package com.example.financemanager.presentation.categories

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.financemanager.R
import com.example.financemanager.domain.model.Category
import com.example.financemanager.presentation.categories.components.CardTransaction
import com.example.financemanager.presentation.categories.viewModels.CategoryDetailsViewModel
import com.example.financemanager.presentation.components.BalanceOverview
import com.example.financemanager.ui.theme.CaribbeanGreen
import com.example.financemanager.ui.theme.Cyprus
import com.example.financemanager.ui.theme.HoneyDew
import com.example.financemanager.ui.theme.PoppinsFontFamily
import com.example.financemanager.ui.theme.Void
import java.time.LocalDate
import java.time.format.DateTimeFormatter.ofPattern


@Composable
fun CategoryDetails(
    viewModel: CategoryDetailsViewModel = hiltViewModel(),
    onClickAddExpense: (Category) -> Unit
) {
    val transactions by viewModel.transaction.collectAsStateWithLifecycle()

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
                .clip(RoundedCornerShape(topStart = 72.dp, topEnd = 72.dp)
                )
                .background(HoneyDew),
        ) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(bottom = 15.dp)
            ) {
                LazyColumn(
                    verticalArrangement = Arrangement.spacedBy(10.dp),
                    contentPadding = PaddingValues(bottom = 90.dp)
                ) {
                    itemsIndexed(transactions) { index, transaction ->
                        val currentDate = transaction.date
                        val previousDate = transactions.getOrNull(index - 1)?.date

                        if (viewModel.printDate(currentDate, previousDate)) {
                            Spacer(Modifier.height(20.dp))
                            MonthHeaderCategoriesDetails(currentDate)
                            Spacer(Modifier.height(15.dp))
                        }

                        CardTransaction(transaction)
                    }
                }

                Button(
                    onClick = {
                        onClickAddExpense(viewModel.category)
                    },
                    modifier = Modifier
                        .height(50.dp)
                        .fillMaxWidth(0.5f)
                        .align(Alignment.BottomCenter),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = CaribbeanGreen
                    )
                ) {
                    Text(
                        text = stringResource(R.string.title_add_expense),
                        fontSize = 16.sp,
                        color = Cyprus,
                        fontFamily = PoppinsFontFamily,
                        fontWeight = FontWeight.Medium
                    )
                }
            }
        }
    }
}

@Composable
fun MonthHeaderCategoriesDetails(
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
fun CategoryDetailsPreview(){
    CategoryDetails{}
}