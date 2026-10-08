package com.example.financemanager.presentation.categories

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
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
import com.example.financemanager.domain.model.Category
import com.example.financemanager.presentation.categories.components.CardTransaction
import com.example.financemanager.presentation.categories.viewModels.CategoryDetailsViewModel
import com.example.financemanager.presentation.components.BalanceOverview
import com.example.financemanager.ui.theme.CaribbeanGreen
import com.example.financemanager.ui.theme.Cyprus
import com.example.financemanager.ui.theme.HoneyDew
import com.example.financemanager.ui.theme.PoppinsFontFamily

@Composable
fun CategoryDetails(
    viewModel: CategoryDetailsViewModel = hiltViewModel(),
    onClickAddExpense: (Category) -> Unit
) {
    val transaction by viewModel.transaction.collectAsStateWithLifecycle()

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
                    verticalArrangement = Arrangement.spacedBy(30.dp),
                    contentPadding = PaddingValues(bottom = 90.dp)
                ) {
                    items(transaction) { item ->
                        CardTransaction(item)
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
                        text = "Add expense",
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

@Preview
@Composable
fun CategoryDetailsPreview(){
    CategoryDetails(){}
}