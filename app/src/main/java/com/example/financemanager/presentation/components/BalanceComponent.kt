package com.example.financemanager.presentation.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.VerticalDivider
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.financemanager.R
import com.example.financemanager.domain.model.TransactionType
import com.example.financemanager.presentation.components.viewModel.BalanceViewModel
import com.example.financemanager.ui.theme.HoneyDew
import com.example.financemanager.ui.theme.OceanBlue
import com.example.financemanager.ui.theme.PoppinsFontFamily
import com.example.financemanager.ui.theme.Void


@Composable
fun BalanceComponent(
    viewModel: BalanceViewModel = hiltViewModel()
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(IntrinsicSize.Min),
        horizontalArrangement = Arrangement.SpaceAround
    ) {
        Component(
            transactionType = TransactionType.INCOME,
            viewModel
        )
        VerticalDivider(
            thickness = 1.dp,
            color = HoneyDew,
            modifier = Modifier.padding(vertical = 5.dp)
        )
        Component(
            transactionType = TransactionType.EXPENSE,
            viewModel

        )
    }
}

@Composable
fun Component(
    transactionType: TransactionType,
    balanceViewModel: BalanceViewModel = hiltViewModel()
    ) {
    val totalExpense by balanceViewModel.totalExpense.collectAsStateWithLifecycle()
    val totalBalance by balanceViewModel.totalBalance.collectAsStateWithLifecycle()
    Column {
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                painter = when (transactionType) {
                    TransactionType.INCOME -> {
                        painterResource(R.drawable.income)
                    }
                    TransactionType.EXPENSE -> {
                        painterResource(R.drawable.expense_vector)
                    }
                },
                contentDescription = "Transaction icon",
                modifier = Modifier.size(12.dp),
                tint = Void
            )

            Spacer(modifier = Modifier.size(5.dp))

            Text(
                text = when(transactionType) {
                    TransactionType.INCOME -> {
                        "Total Balance"
                    }
                    TransactionType.EXPENSE -> {
                        "Total expense"
                    }
                },
                fontFamily = PoppinsFontFamily,
                fontSize = 12.sp,
                fontWeight = FontWeight.Normal,
                color = Void
            )
        }
        Text(
            text = when(transactionType) {
                TransactionType.INCOME -> {
                    "${"%.2f".format(totalBalance)} ₽"
                }
                TransactionType.EXPENSE -> {
                    "${"%.2f".format(totalExpense)} ₽"
                }
            },
            fontFamily = PoppinsFontFamily,
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            color =  when(transactionType) {
                TransactionType.INCOME -> {
                    HoneyDew
                }
                TransactionType.EXPENSE -> {
                    OceanBlue
                }
            }
        )
    }
}