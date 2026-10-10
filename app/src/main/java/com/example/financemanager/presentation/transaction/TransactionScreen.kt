package com.example.financemanager.presentation.transaction

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.financemanager.R
import com.example.financemanager.domain.model.TransactionType
import com.example.financemanager.presentation.components.CardWithMessage
import com.example.financemanager.ui.theme.CaribbeanGreen
import com.example.financemanager.ui.theme.HoneyDew
import com.example.financemanager.ui.theme.OceanBlue
import com.example.financemanager.ui.theme.PoppinsFontFamily
import com.example.financemanager.ui.theme.Void
import java.time.LocalDate
import java.time.format.DateTimeFormatter.ofPattern

@Composable
fun TransactionScreen(
    viewModel: TransactionViewModel = hiltViewModel()
) {
    val transactions by viewModel.transaction.collectAsStateWithLifecycle()
    val selectedType by viewModel.selectedType.collectAsStateWithLifecycle()
    val totalExpense by viewModel.totalExpense.collectAsStateWithLifecycle()
    val totalIncome  by viewModel.totalIncome.collectAsStateWithLifecycle()
    val totalBalance by viewModel.totalBalance.collectAsStateWithLifecycle()


    Column(
        modifier = Modifier
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth(0.9f)
        ) {
            TotalBalanceComponent(
                totalBalanceText = viewModel.formatCurrency(totalBalance),
                onClick = {
                    viewModel.selectType(null)
                }
            )
            Spacer(Modifier.height(15.dp))
            Row(
                modifier = Modifier
                    .fillMaxWidth(),

            ) {
                TransactionTypeComponent(
                    modifier = Modifier.weight(1f),
                    type = TransactionType.INCOME,
                    selectType = selectedType,
                    balanceText = viewModel.formatCurrency(totalIncome),
                    onClick = {
                        viewModel.selectType(TransactionType.INCOME)
                    }
                )
                Spacer(modifier = Modifier.width(10.dp))

                TransactionTypeComponent(
                    modifier = Modifier.weight(1f),
                    type = TransactionType.EXPENSE,
                    selectType = selectedType,
                    balanceText = viewModel.formatCurrency(totalExpense),
                    onClick = {
                        viewModel.selectType(TransactionType.EXPENSE)
                    }
                )
            }
        }

        Box (modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight(0.9f)
            .clip(RoundedCornerShape(topStart = 72.dp, topEnd = 72.dp))
            .background(HoneyDew)
        ) {
            LazyColumn(
                modifier = Modifier.padding(top = 5.dp, start = 15.dp, end = 15.dp),
                verticalArrangement = Arrangement.spacedBy(30.dp),
                horizontalAlignment = Alignment.Start
            ) {
                itemsIndexed(transactions) {index, transaction ->
                    val currentDate = transaction.date
                    val previousDate = transactions.getOrNull(index - 1)?.date

                    if (viewModel.printDate(currentDate, previousDate)) {
                        Spacer(Modifier.height(20.dp))
                        MonthHeaderTransaction(currentDate)
                        Spacer(Modifier.height(15.dp))
                    }

                    CardWithMessage(transaction)
                }
            }
        }
    }
}

@Composable
fun MonthHeaderTransaction(
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

@Composable
fun TotalBalanceComponent(
    onClick: () -> Unit,
    totalBalanceText: String,
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .height(75.dp)
            .clip(RoundedCornerShape(14.dp))
            .background(HoneyDew)
            .clickable{
                onClick()
            },
        verticalArrangement = Arrangement.SpaceEvenly,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = stringResource(R.string.total_balance),
            fontFamily = PoppinsFontFamily,
            fontWeight = FontWeight.Normal,
            fontSize = 16.sp,
            color = Void
        )

        Text(
            text = totalBalanceText,
            fontFamily = PoppinsFontFamily,
            fontWeight = FontWeight.Bold,
            fontSize = 30.sp,
            color = Void
        )
    }
}

@Composable
fun TransactionTypeComponent(
    modifier: Modifier,
    type: TransactionType,
    selectType: TransactionType?,
    balanceText: String,
    onClick: () -> Unit
) {
    var backgroundColor = HoneyDew
    var iconColor = if (type == TransactionType.EXPENSE) OceanBlue else CaribbeanGreen
    var textAmountColor = if (type == TransactionType.EXPENSE) OceanBlue else Void
    var typeTextColor = Void
    val iconType = if (type == TransactionType.EXPENSE) R.drawable.income else R.drawable.expense_vector
    val textTitle = if (type == TransactionType.EXPENSE) stringResource(R.string.total_expense) else stringResource(R.string.total_income)

    if (selectType != null && selectType == type) {
        backgroundColor = OceanBlue
        iconColor = HoneyDew
        textAmountColor = HoneyDew
        typeTextColor = HoneyDew
    }

    Column(
        modifier = modifier
            .fillMaxWidth()
            .height(90.dp)
            .clip(RoundedCornerShape(14.dp))
            .background(backgroundColor)
            .clickable{
                onClick()
            },
        verticalArrangement = Arrangement.SpaceEvenly,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Icon(
            painter = painterResource(
                iconType
            ),
            contentDescription = "Income icon",
            tint = iconColor,
            modifier = Modifier.size(24.dp)
        )

        Text(
            text = textTitle,
            fontFamily = PoppinsFontFamily,
            fontWeight = FontWeight.Medium,
            fontSize = 18.sp,
            color = typeTextColor
        )

        Text(
            text = balanceText,
            fontFamily = PoppinsFontFamily,
            fontWeight = FontWeight.Medium,
            fontSize = 14.sp,
            color = textAmountColor
        )
    }
}


@Preview(showBackground = true)
@Composable
fun TransactionPreview() {
    TransactionScreen()
}
