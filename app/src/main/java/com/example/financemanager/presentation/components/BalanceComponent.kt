package com.example.financemanager.presentation.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
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
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.financemanager.R
import com.example.financemanager.domain.model.TransactionType
import com.example.financemanager.presentation.components.viewModel.BalanceViewModel
import com.example.financemanager.ui.theme.HoneyDew
import com.example.financemanager.ui.theme.OceanBlue
import com.example.financemanager.ui.theme.PoppinsFontFamily
import com.example.financemanager.ui.theme.Red
import com.example.financemanager.ui.theme.Void


@Composable
fun BalanceComponent(
    viewModel: BalanceViewModel = hiltViewModel()
) {
    Box(
        modifier = Modifier.fillMaxWidth(),
        contentAlignment = Alignment.Center
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(IntrinsicSize.Min),
            horizontalArrangement = Arrangement.SpaceAround
        ) {

            Component(
                modifier = Modifier.weight(1f),
                transactionType = TransactionType.INCOME,
                balanceViewModel = viewModel
            )
            VerticalDivider(
                thickness = 1.dp,
                color = HoneyDew,
                modifier = Modifier.padding(vertical = 5.dp)
            )
            Component(
                modifier = Modifier.weight(1f),
                transactionType = TransactionType.EXPENSE,
                balanceViewModel = viewModel

            )
        }
    }
}

@Composable
fun Component(
    modifier: Modifier,
    transactionType: TransactionType,
    balanceViewModel: BalanceViewModel = hiltViewModel()
    ) {
    val totalExpense by balanceViewModel.totalExpense.collectAsStateWithLifecycle()
    val totalBalance by balanceViewModel.totalBalance.collectAsStateWithLifecycle()
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Row(
            modifier = Modifier,
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
                        stringResource(R.string.total_balance)
                    }
                    TransactionType.EXPENSE -> {
                        stringResource(R.string.total_expense)
                    }
                },
                fontFamily = PoppinsFontFamily,
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold,
                color = Void,
                textAlign = TextAlign.Start
            )
        }
        Text(
            text = when(transactionType) {
                TransactionType.INCOME -> {
                    balanceViewModel.formatCurrency(totalBalance)
                }
                TransactionType.EXPENSE -> {
                   "-" + balanceViewModel.formatCurrency(totalExpense)
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
            },
            maxLines = 1,
            overflow = TextOverflow.Ellipsis
        )
    }
}