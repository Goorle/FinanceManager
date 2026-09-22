package com.example.financemanager.presentation.categories.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.financemanager.R
import com.example.financemanager.domain.model.Category
import com.example.financemanager.domain.model.Transaction
import com.example.financemanager.domain.model.TransactionCategories
import com.example.financemanager.domain.model.TransactionType
import com.example.financemanager.ui.theme.CaribbeanGreen
import com.example.financemanager.ui.theme.HoneyDew
import com.example.financemanager.ui.theme.OceanBlue
import com.example.financemanager.ui.theme.PoppinsFontFamily
import com.example.financemanager.ui.theme.VividBlue
import com.example.financemanager.ui.theme.Void
import java.time.LocalDate
import java.time.format.DateTimeFormatter.ofPattern
import java.util.UUID

@Composable
fun CardTransaction(transaction: Transaction) {
    Card(
        modifier = Modifier
            .fillMaxWidth(),
        colors = CardDefaults.cardColors(
            containerColor = HoneyDew
        )
    ) {
        Row(
            modifier = Modifier.height(IntrinsicSize.Min),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(7.dp)
        ) {
            Box(
                modifier = Modifier
                    .size(64.dp)
                    .clip(RoundedCornerShape(22.dp))
                    .background(VividBlue)
                ,
                contentAlignment = Alignment.Center
            ) {
                val painterId = getIconIdFromCategory(transaction.category)
                Icon(
                    painter = painterResource(painterId),
                    contentDescription = "Icon transaction",
                    tint = HoneyDew,
                    modifier = Modifier.size(32.dp)
                )
            }

            Column(
                modifier = Modifier.fillMaxHeight(),
                verticalArrangement = Arrangement.SpaceEvenly
            ) {
                Text(
                    text= transaction.message,
                    fontFamily = PoppinsFontFamily,
                    fontWeight = FontWeight.Medium,
                    color = Void,
                    fontSize = 14.sp
                )

                Text(
                    text = transaction.date.format(ofPattern("d MMMM yyyy")),
                    fontFamily = PoppinsFontFamily,
                    fontSize = 12.sp,
                    fontWeight = FontWeight.Medium,
                    color = OceanBlue,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
            }

            Box(
                modifier = Modifier.fillMaxWidth(),
                contentAlignment = Alignment.CenterEnd
            ) {
                Text(
                    textAlign = TextAlign.Center,
                    text = if (transaction.type == TransactionType.EXPENSE) "-$${transaction.amount}" else "+$${transaction.amount}",
                    fontFamily = PoppinsFontFamily,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Medium,
                    color = if (transaction.type == TransactionType.EXPENSE) OceanBlue else CaribbeanGreen
                )
            }
        }
    }
}

fun getIconIdFromCategory(category: TransactionCategories): Int {
    return when(category) {
        TransactionCategories.FOOD -> {
            R.drawable.food_vector
        }
        TransactionCategories.TRANSPORT -> {
            R.drawable.transport_vector
        }
        TransactionCategories.MEDICINE -> {
            R.drawable.medicine_vector
        }
        TransactionCategories.GROCERIES -> {
            R.drawable.products_vector
        }
        TransactionCategories.RENT -> {
            R.drawable.rent_vector
        }
        TransactionCategories.GIFTS ->{
            R.drawable.presents_vector
        }
        TransactionCategories.SAVINGS -> {
            R.drawable.savings_vector
        }
        TransactionCategories.ENTERTAINMENT -> {
            R.drawable.entertainment_vector
        }
        TransactionCategories.SALARY -> {
            R.drawable.salary_vector
        }
        TransactionCategories.OTHER -> {
            R.drawable.plus_vector
        }
    }
}

@Preview(showBackground = true)
@Composable
fun CardPreview() {
    val transaction = Transaction(
        id = UUID.randomUUID(),
        title = "Salary",
        message = "TEST MESSAGE",
        amount = 120.4,
        type = TransactionType.EXPENSE,
        category = TransactionCategories.ENTERTAINMENT,
        date = LocalDate.now()
    )
    CardTransaction(transaction)
}