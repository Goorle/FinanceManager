package com.example.financemanager.presentation.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
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
import com.example.financemanager.R
import com.example.financemanager.presentation.components.viewModel.BalanceViewModel
import com.example.financemanager.ui.theme.PoppinsFontFamily
import com.example.financemanager.ui.theme.Void

@Composable
fun StatisticComponent(
    viewModel: BalanceViewModel = hiltViewModel()
) {
    val percentExpense by viewModel.percentExpense.collectAsStateWithLifecycle()
    Box {
        Row(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                painter = painterResource(R.drawable.check_vector),
                contentDescription = "Check Icon",
                tint = Void,
                modifier = Modifier.size(12.dp)
            )

            Spacer(Modifier.size(5.dp))

            Text(
                text = "${"%.0f".format(percentExpense)} % of your expenses",
                fontFamily = PoppinsFontFamily,
                fontSize = 15.sp,
                fontWeight = FontWeight.Normal,
                color = Void,
            )
        }
    }
}