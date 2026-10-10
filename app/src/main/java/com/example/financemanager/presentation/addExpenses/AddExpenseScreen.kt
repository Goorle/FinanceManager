package com.example.financemanager.presentation.addExpenses

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.DatePicker
import androidx.compose.material3.DatePickerDialog
import androidx.compose.material3.DisplayMode
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.financemanager.R
import com.example.financemanager.domain.model.Category
import com.example.financemanager.ui.theme.CaribbeanGreen
import com.example.financemanager.ui.theme.RedDark
import com.example.financemanager.ui.theme.RedLight
import com.example.financemanager.ui.theme.HoneyDew
import com.example.financemanager.ui.theme.LightGreen
import com.example.financemanager.ui.theme.PoppinsFontFamily
import com.example.financemanager.ui.theme.Void
import java.time.format.DateTimeFormatter


@Composable
fun AddExpenseScreen(
    viewModel: AddExpenseViewModel = hiltViewModel(),
    snackbarHostState: SnackbarHostState,
    onExpenseSaved: (Category) -> Unit
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    val scrollState = rememberScrollState()

    LaunchedEffect(Unit) {
        viewModel.events.collect { event ->
            when(event){
                is TransactionEvent.Error -> snackbarHostState.showSnackbar(event.message)
                TransactionEvent.SavedSuccessfully -> onExpenseSaved(Category.valueOf(uiState.selectedCategory))
            }

        }
    }
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
                SelectDateComponent(
                    viewModel,
                    uiState
                )
                SelectCategoryComponent(viewModel, uiState)
                SelectAmountComponent(viewModel, uiState)
                SelectExpenseTitleComponent(viewModel, uiState)
                SelectExpenseMessageComponent(viewModel, uiState)

                Button(
                    onClick = {
                        viewModel.saveTransaction()
                    },
                    modifier = Modifier
                        .fillMaxWidth(0.5f),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = CaribbeanGreen
                    )
                ) {
                    Text(
                        text = stringResource(R.string.save_button),
                        fontFamily = PoppinsFontFamily,
                        fontWeight = FontWeight.Medium,
                        fontSize = 16.sp,
                        color = Void
                    )
                }

                if (uiState.showDatePicker) {
                    ShowSelectDate(viewModel)
                }
            }
        }
    }
}


@Composable
fun SelectDateComponent(
    viewModel: AddExpenseViewModel,
    uiState: AddExpenseUiState
) {
    Column(
        modifier = Modifier
            .fillMaxWidth(0.8f)
            .height(75.dp)
            .clickable {
                viewModel.showDateDialog(true)
            },
    ) {
        Text(
            text = stringResource(R.string.date),
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
                text = uiState.selectedDate.format(DateTimeFormatter.ofPattern("dd.MM.yyyy")),
                fontSize = 14.sp,
                fontFamily = PoppinsFontFamily,
                fontWeight = FontWeight.Normal,
                color = Void
            )
            IconButton(
                onClick = {
                    viewModel.showDateDialog(true)
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
    viewModel: AddExpenseViewModel,
) {
    val datePickerState = rememberDatePickerState(initialDisplayMode = DisplayMode.Input)

    DatePickerDialog(
        onDismissRequest = {
            viewModel.showDateDialog( false)
        },
        confirmButton = {
            TextButton(
                onClick = {
                    datePickerState.selectedDateMillis?.let { millis ->
                        viewModel.selectedDate(millis)
                    }
                    viewModel.showDateDialog(false)
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
                    viewModel.showDateDialog(false)
                }
            ) {
                Text(
                    text = stringResource(R.string.cancel),
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
    viewModel: AddExpenseViewModel,
    uiState: AddExpenseUiState
) {
    Column(
        modifier = Modifier
            .fillMaxWidth(0.8f)
    ) {
        Text(
            text = stringResource(R.string.category),
            fontSize = 16.sp,
            fontFamily = PoppinsFontFamily,
            fontWeight = FontWeight.Medium,
            color = if (uiState.categoryError != null) RedDark else Void,
        )

        LazyRow(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            items(Category.entries) { category ->
                CardCategory(category, Category.valueOf(uiState.selectedCategory)) {
                    viewModel.selectCategory(category.name)
                }
            }
        }
    }
}

@Composable
fun SelectAmountComponent(
    viewModel: AddExpenseViewModel,
    uiState: AddExpenseUiState
) {
    Column(
        modifier = Modifier
            .fillMaxWidth(0.8f)
            .height(75.dp),
    ) {
        Text(
            text = stringResource(R.string.amount),
            fontFamily = PoppinsFontFamily,
            fontWeight = FontWeight.Medium,
            fontSize = 16.sp,
            color = if(uiState.amountError != null) RedDark else Void
        )

        TextField(
            value = uiState.amount,
            suffix = {
                Text(
                    text = "₽",
                    fontFamily = PoppinsFontFamily,
                    color = Void
                )
            },
            isError = uiState.amountError != null,
            modifier = Modifier
                .fillMaxWidth()
                .clip(RoundedCornerShape(18.dp)),
            onValueChange = { newValue ->
                val regex = Regex("^\\d{0,9}([.,]\\d{0,2})?$")

                if (newValue.matches(regex)) {
                    viewModel.onAmountChanged(newValue)
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
                unfocusedTextColor = Void,
                errorTextColor = RedDark,
                errorContainerColor = RedLight,
                errorIndicatorColor = RedLight,
            )
        )
    }
}

@Composable
fun SelectExpenseTitleComponent(
    viewModel: AddExpenseViewModel,
    uiState: AddExpenseUiState
) {
    Column(
        modifier = Modifier
            .fillMaxWidth(0.8f)
            .height(75.dp),
    ) {
        Text(
            text = stringResource( R.string.title_expense),
            fontFamily = PoppinsFontFamily,
            fontWeight = FontWeight.Medium,
            fontSize = 16.sp,
            color = if(uiState.titleError != null) RedDark else Void
        )

        TextField(
            value = uiState.selectTitle,
            isError = uiState.titleError != null,
            modifier = Modifier
                .fillMaxWidth()
                .clip(RoundedCornerShape(18.dp)),
            onValueChange = {
                viewModel.titleChanged(it)
            },
            singleLine = true,
            colors = TextFieldDefaults.colors(
                unfocusedContainerColor = LightGreen,
                focusedContainerColor = LightGreen,
                cursorColor = CaribbeanGreen,
                unfocusedIndicatorColor = LightGreen,
                focusedIndicatorColor = LightGreen,
                focusedTextColor = Void,
                unfocusedTextColor = Void,
                errorTextColor = RedDark,
                errorContainerColor = RedLight,
                errorIndicatorColor = RedLight,
            )
        )
    }
}

@Composable
fun SelectExpenseMessageComponent(
    viewModel: AddExpenseViewModel,
    uiState: AddExpenseUiState
) {
    Box(
        modifier = Modifier
            .fillMaxWidth(0.8f)
            .height(90.dp),
    ) {
        TextField(
            value = uiState.selectMessage,
            isError = uiState.messageError != null,
            modifier = Modifier
                .fillMaxSize()
                .clip(RoundedCornerShape(22.dp)),
            onValueChange = {
                viewModel.messageChanged(it)
            },
            singleLine = true,
            colors = TextFieldDefaults.colors(
                unfocusedContainerColor = LightGreen,
                focusedContainerColor = LightGreen,
                cursorColor = CaribbeanGreen,
                unfocusedIndicatorColor = LightGreen,
                focusedIndicatorColor = LightGreen,
                focusedTextColor = Void,
                unfocusedTextColor = Void,
                errorTextColor = RedDark,
                errorContainerColor = RedLight,
                errorIndicatorColor = RedLight,
            )
        )
        Text(
            text = stringResource(R.string.enter_message),
            fontFamily = PoppinsFontFamily,
            fontWeight = FontWeight.Medium,
            fontSize = 16.sp,
            color = if(uiState.messageError != null) RedDark else CaribbeanGreen,
            modifier = Modifier
                .padding(10.dp)
                .align(Alignment.TopStart)
        )
    }
}

@Preview(showBackground = true)
@Composable
fun AddExpensesPreview() {
//    AddExpenseScreen(){}
}
