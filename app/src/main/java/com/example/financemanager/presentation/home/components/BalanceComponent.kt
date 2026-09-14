package com.example.financemanager.presentation.home.components

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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.financemanager.R
import com.example.financemanager.domain.model.TransactionType
import com.example.financemanager.ui.theme.HoneyDew
import com.example.financemanager.ui.theme.OceanBlue
import com.example.financemanager.ui.theme.PoppinsFontFamily
import com.example.financemanager.ui.theme.Void


@Composable
fun BalanceComponent() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(IntrinsicSize.Min),
        horizontalArrangement = Arrangement.SpaceAround
    ) {
        Component(TransactionType.INCOME)
        VerticalDivider(
            thickness = 1.dp,
            color = HoneyDew,
            modifier = Modifier.padding(vertical = 5.dp)
        )
        Component(TransactionType.EXPENSE)
    }
}

@Composable
fun Component(transactionType: TransactionType) {

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
                    "$7,783.00"
                }
                TransactionType.EXPENSE -> {
                    "$-700.00"
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