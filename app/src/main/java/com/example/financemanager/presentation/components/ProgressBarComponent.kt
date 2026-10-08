package com.example.financemanager.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.financemanager.presentation.components.viewModel.BalanceViewModel
import com.example.financemanager.ui.theme.CaribbeanGreen
import com.example.financemanager.ui.theme.Cyprus
import com.example.financemanager.ui.theme.FenceGreen
import com.example.financemanager.ui.theme.HoneyDew
import com.example.financemanager.ui.theme.LightBlue
import com.example.financemanager.ui.theme.OceanBlue
import com.example.financemanager.ui.theme.PoppinsFontFamily
import com.example.financemanager.ui.theme.Red
import com.example.financemanager.ui.theme.RedDark
import com.example.financemanager.ui.theme.RedLight
import com.example.financemanager.ui.theme.VividBlue
import com.example.financemanager.ui.theme.Void

@Composable
fun ProgressBarComponent(
    viewModel: BalanceViewModel = hiltViewModel()
) {
    val percentExpense by viewModel.percentExpense.collectAsStateWithLifecycle()
    val totalIncome by viewModel.totalIncome.collectAsStateWithLifecycle()
    Column {
        Box(
            modifier = Modifier
                .fillMaxWidth(0.8f)
                .height(30.dp)
                .clip(RoundedCornerShape(14.dp))
                .background(VividBlue)
                .align(Alignment.CenterHorizontally),
            contentAlignment = Alignment.CenterEnd
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth( (percentExpense/100).toFloat())
                    .height(30.dp)
                    .clip(RoundedCornerShape(14.dp))
                    .align(Alignment.CenterEnd)
                    .background(FenceGreen),
                contentAlignment = Alignment.CenterStart
            ) {}
            Text(
                text = "${"%.2f".format(totalIncome)}₽",
                fontFamily = PoppinsFontFamily,
                fontSize = 16.sp,
                fontWeight = FontWeight.Medium,
                color = HoneyDew,
                modifier = Modifier.padding(start = 10.dp).align(Alignment.CenterStart)
            )

            Text(
                text = "${"%.0f".format(percentExpense)} %",
                fontFamily = PoppinsFontFamily,
                fontSize = 16.sp,
                fontWeight = FontWeight.Medium,
                color = LightBlue,
                modifier = Modifier.padding(end = 10.dp)
            )
        }
    }
}