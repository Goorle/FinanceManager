package com.example.financemanager.presentation.addExpenses

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
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.requiredSizeIn
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.DpOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import com.example.financemanager.R
import com.example.financemanager.domain.model.Category
import com.example.financemanager.presentation.navigation.RoutesScreen
import com.example.financemanager.ui.theme.CaribbeanGreen
import com.example.financemanager.ui.theme.Cyprus
import com.example.financemanager.ui.theme.FenceGreen
import com.example.financemanager.ui.theme.HoneyDew
import com.example.financemanager.ui.theme.LightGreen
import com.example.financemanager.ui.theme.PoppinsFontFamily
import com.example.financemanager.ui.theme.Void


@Composable
fun AddExpenseScreen(
    viewModel: AddExpenseViewModel = hiltViewModel()
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
    ) {

        Box(
            modifier = Modifier
                .fillMaxSize()
                .clip(RoundedCornerShape(topStart = 72.dp, topEnd = 72.dp))
                .background(HoneyDew)
                .align(Alignment.BottomCenter),
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                SelectDateComponent()
                SelectCategoryComponent(viewModel)
            }
        }
    }
}

@Composable
fun SelectDateComponent() {
    Column(
        modifier = Modifier
            .fillMaxWidth(0.8f)
            .height(75.dp),
    ) {
        Text(
            text = "Date",
            fontSize = 16.sp,
            fontFamily = PoppinsFontFamily,
            fontWeight = FontWeight.Medium,
            color = Void,
        )

        TextField(
            modifier = Modifier
                .fillMaxWidth()
                .clip(RoundedCornerShape(18.dp)),
            value = "",
            onValueChange = {},
            trailingIcon = {
                Box(
                    modifier = Modifier
                        .size(32.dp)
                        .clip(RoundedCornerShape(12.dp))
                        .background(CaribbeanGreen),
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        painter = painterResource(R.drawable.calendar_vector),
                        contentDescription = "Calendar",
                        tint = Void,
                        modifier = Modifier.size(16.dp)
                    )
                }
            },
            singleLine = true,
            colors = TextFieldDefaults.colors(
                unfocusedContainerColor = LightGreen,
                focusedContainerColor = LightGreen,
                cursorColor = CaribbeanGreen,
                unfocusedIndicatorColor = LightGreen,
                focusedIndicatorColor = LightGreen
            )
        )
    }
}

@Composable
fun SelectCategoryComponent(
    viewModel: AddExpenseViewModel
) {
    val scrollState = rememberScrollState(0)

    Column(
        modifier = Modifier
            .fillMaxWidth(0.8f)
            .height(75.dp),
    ) {
        Text(
            text = "Category",
            fontSize = 16.sp,
            fontFamily = PoppinsFontFamily,
            fontWeight = FontWeight.Medium,
            color = Void,
        )
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight()
                .clip(RoundedCornerShape(18.dp))
                .background(LightGreen)
                .padding(5.dp)
                .clickable {
                    viewModel.expandedDropDownMenu = !viewModel.expandedDropDownMenu
                },
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = viewModel.textSelectCategory,
                fontSize = 14.sp,
                fontFamily = PoppinsFontFamily,
                fontWeight = FontWeight.Normal,
                color = FenceGreen
            )

            IconButton(
                onClick = {
                    viewModel.expandedDropDownMenu = !viewModel.expandedDropDownMenu
                }
            ) {
                Icon(
                    painter = painterResource(R.drawable.confirm_vector),
                    tint = CaribbeanGreen,
                    contentDescription = "Category Choose",
                    modifier = Modifier.size(16.dp)
                )
            }
        }
        Spacer(modifier = Modifier.height(15.dp))
        DropdownMenu(
            expanded = viewModel.expandedDropDownMenu,
            onDismissRequest = {
                viewModel.expandedDropDownMenu = false
            },
            modifier = Modifier
                .fillMaxWidth(0.8f)
                .heightIn(max = 175.dp),
            shape = RoundedCornerShape(18.dp),
            scrollState = scrollState,
            containerColor = LightGreen,
            offset = DpOffset(0.dp, 5.dp)
            ) {
            Category.entries.forEach { category ->

                if (Category.MORE == category) return@forEach

                DropdownMenuItem(
                    text = {
                        Text(
                            text = category.displayName,
                            fontSize = 14.sp,
                            fontFamily = PoppinsFontFamily,
                            fontWeight = FontWeight.Normal,
                        )
                    },
                    onClick = {
                        viewModel.textSelectCategory = category.displayName
                        viewModel.expandedDropDownMenu = false
                    },
                )
                HorizontalDivider(Modifier.background(CaribbeanGreen))
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun AddExpensesPreview() {
    AddExpenseScreen()
}
