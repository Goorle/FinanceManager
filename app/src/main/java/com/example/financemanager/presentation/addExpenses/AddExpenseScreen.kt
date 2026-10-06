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
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.DatePicker
import androidx.compose.material3.DatePickerDialog
import androidx.compose.material3.DisplayMode
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.capitalize
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.DpOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import com.example.financemanager.R
import com.example.financemanager.domain.model.Category
import com.example.financemanager.domain.model.TransactionCategories
import com.example.financemanager.presentation.navigation.RoutesScreen
import com.example.financemanager.ui.theme.CaribbeanGreen
import com.example.financemanager.ui.theme.Cyprus
import com.example.financemanager.ui.theme.FenceGreen
import com.example.financemanager.ui.theme.HoneyDew
import com.example.financemanager.ui.theme.LightGreen
import com.example.financemanager.ui.theme.PoppinsFontFamily
import com.example.financemanager.ui.theme.Void
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.Locale


@Composable
fun AddExpenseScreen(
    viewModel: AddExpenseViewModel = hiltViewModel(),
    back: () -> Unit
) {
    val scrollState = rememberScrollState()
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
                    .fillMaxSize()
                    .verticalScroll(scrollState),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.SpaceEvenly,
            ) {
                SelectDateComponent(viewModel)
                SelectCategoryComponent(viewModel)
                SelectAmountComponent(viewModel)
                SelectExpenseTitleComponent(viewModel)
                SelectExpenseMessageComponent(viewModel)

                Button(
                    onClick = {
                        viewModel.addExpense()
                        back()
                    },
                    modifier = Modifier
                        .fillMaxWidth(0.5f),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = CaribbeanGreen
                    )
                ) {
                    Text(
                        text = "Save",
                        fontFamily = PoppinsFontFamily,
                        fontWeight = FontWeight.Medium,
                        fontSize = 16.sp,
                        color = Void
                    )
                }

                if (viewModel.showDatePicker) {
                    ShowSelectDate(viewModel)
                }
            }
        }
    }
}

@Composable
fun SelectDateComponent(
    viewModel: AddExpenseViewModel
) {
    Column(
        modifier = Modifier
            .fillMaxWidth(0.8f)
            .height(75.dp)
            .clickable{
                viewModel.showDatePicker = true
            },
    ) {
        Text(
            text = "Date",
            fontSize = 16.sp,
            fontFamily = PoppinsFontFamily,
            fontWeight = FontWeight.Medium,
            color = Void,
        )

        Row (
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight()
                .clip(RoundedCornerShape(18.dp))
                .background(LightGreen)
                .padding(horizontal = 10.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = viewModel.currentDate.format(DateTimeFormatter.ofPattern("dd.MM.yyyy")),
                fontSize = 14.sp,
                fontFamily = PoppinsFontFamily,
                fontWeight = FontWeight.Normal,
                color = Void
            )
            IconButton(
                onClick = {
                    viewModel.showDatePicker = true
                },
                colors = IconButtonDefaults.iconButtonColors(
                    containerColor = CaribbeanGreen
                ),
                shape = RoundedCornerShape(12.dp),
                modifier = Modifier
                    .size(36.dp)
            ) {
                Icon(
                    painter = painterResource(R.drawable.calendar_vector),
                    contentDescription = "Calendar",
                    tint = Void,
                    modifier = Modifier.size(24.dp)
                )
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ShowSelectDate(
    viewModel: AddExpenseViewModel
) {
    val datePickerState = rememberDatePickerState(initialDisplayMode = DisplayMode.Input)

    DatePickerDialog(
        onDismissRequest = {
            viewModel.showDatePicker = false
        },
        confirmButton = {
            TextButton(
                onClick = {
                    datePickerState.selectedDateMillis?.let { millis ->
                        viewModel.changeDate(millis)
                    }
                    viewModel.showDatePicker = false
                }
            ) {
                Text(
                    text = "OK",
                    fontFamily = PoppinsFontFamily,
                    fontWeight = FontWeight.Medium,
                    fontSize = 16.sp,
                    color = Void,
                )
            }
        },
        dismissButton = {
            TextButton(
                onClick = {
                    viewModel.showDatePicker = false
                }
            ) {
                Text(
                    text = "Cancel",
                    fontFamily = PoppinsFontFamily,
                    fontWeight = FontWeight.Medium,
                    fontSize = 16.sp,
                    color = Void,
                )
            }
        }
    ) {
        DatePicker(datePickerState)
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
                text = viewModel.textSelectCategory.lowercase().replaceFirstChar { char ->
                    char.uppercaseChar()},
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
            TransactionCategories.entries.forEach { category ->

                if (TransactionCategories.OTHER == category) return@forEach

                DropdownMenuItem(
                    text = {
                        Text(
                            text = category.name.lowercase().replaceFirstChar { char ->
                                char.uppercaseChar()
                            },
                            fontSize = 14.sp,
                            fontFamily = PoppinsFontFamily,
                            fontWeight = FontWeight.Normal,
                            color = Void
                        )
                    },
                    onClick = {
                        viewModel.textSelectCategory = category.name
                        viewModel.expandedDropDownMenu = false
                    },
                )
                HorizontalDivider(Modifier.background(CaribbeanGreen))
            }
        }
    }
}

@Composable
fun SelectAmountComponent(
    viewModel: AddExpenseViewModel
) {
    Column(
        modifier = Modifier
            .fillMaxWidth(0.8f)
            .height(75.dp),
    ) {
        Text(
            text = "Amount",
            fontFamily = PoppinsFontFamily,
            fontWeight = FontWeight.Medium,
            fontSize = 16.sp,
            color = Void
        )

        TextField(
            value = viewModel.textSelectAmount,
            modifier = Modifier
                .fillMaxWidth()
                .clip(RoundedCornerShape(18.dp)),
            onValueChange = { newValue ->
                if (!newValue.matches(Regex("^\\d*(\\.\\d{0,2})?$"))) {
                    return@TextField
                }

                // Проверяем максимальное значение
                val number = newValue.toDoubleOrNull()

                if (number == null || number <= 999_999_999.99) {
                    viewModel.textSelectAmount = newValue
                }
            },
            singleLine = true,
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Decimal),
            colors = TextFieldDefaults.colors(
                unfocusedContainerColor = LightGreen,
                focusedContainerColor = LightGreen,
                cursorColor = CaribbeanGreen,
                unfocusedIndicatorColor = LightGreen,
                focusedIndicatorColor = LightGreen,
                focusedTextColor = Void,
                unfocusedTextColor = Void
            )
        )
    }
}

@Composable
fun SelectExpenseTitleComponent(
    viewModel: AddExpenseViewModel
) {
    Column(
        modifier = Modifier
            .fillMaxWidth(0.8f)
            .height(75.dp),
    ) {
        Text(
            text = "Expense title",
            fontFamily = PoppinsFontFamily,
            fontWeight = FontWeight.Medium,
            fontSize = 16.sp,
            color = Void
        )

        TextField(
            value = viewModel.textSelectTitle,
            modifier = Modifier
                .fillMaxWidth()
                .clip(RoundedCornerShape(18.dp)),
            onValueChange = {
                viewModel.textSelectTitle = it
            },
            singleLine = true,
            colors = TextFieldDefaults.colors(
                unfocusedContainerColor = LightGreen,
                focusedContainerColor = LightGreen,
                cursorColor = CaribbeanGreen,
                unfocusedIndicatorColor = LightGreen,
                focusedIndicatorColor = LightGreen,
                focusedTextColor = Void,
                unfocusedTextColor = Void
            )
        )
    }
}

@Composable
fun SelectExpenseMessageComponent(
    viewModel: AddExpenseViewModel
) {
    Box(
        modifier = Modifier
            .fillMaxWidth(0.8f)
            .height(90.dp),
    ) {
        TextField(
            value = viewModel.textSelectMessage,
            modifier = Modifier
                .fillMaxSize()
                .clip(RoundedCornerShape(22.dp)),
            onValueChange = {
                viewModel.textSelectMessage = it
            },
            singleLine = true,
            colors = TextFieldDefaults.colors(
                unfocusedContainerColor = LightGreen,
                focusedContainerColor = LightGreen,
                cursorColor = CaribbeanGreen,
                unfocusedIndicatorColor = LightGreen,
                focusedIndicatorColor = LightGreen,
                focusedTextColor = Void,
                unfocusedTextColor = Void
            )
        )
        Text(
            text = "Enter Message",
            fontFamily = PoppinsFontFamily,
            fontWeight = FontWeight.Medium,
            fontSize = 16.sp,
            color = CaribbeanGreen,
            modifier = Modifier
                .padding(10.dp)
                .align(Alignment.TopStart)
        )
    }
}

@Preview(showBackground = true)
@Composable
fun AddExpensesPreview() {
    AddExpenseScreen(){}
}
