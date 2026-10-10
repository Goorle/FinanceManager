package com.example.financemanager.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
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
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.financemanager.domain.model.Category
import com.example.financemanager.domain.model.Transaction
import com.example.financemanager.domain.model.TransactionType
import com.example.financemanager.ui.theme.CaribbeanGreen
import com.example.financemanager.ui.theme.HoneyDew
import com.example.financemanager.ui.theme.OceanBlue
import com.example.financemanager.ui.theme.PoppinsFontFamily
import com.example.financemanager.ui.theme.VividBlue
import com.example.financemanager.ui.theme.Void
import java.text.NumberFormat
import java.time.LocalDate
import java.util.Locale

@Composable
fun CardWithMessage(
    transaction: Transaction
) {
    val formatter = NumberFormat.getNumberInstance(Locale.FRANCE).apply {
        minimumFractionDigits = 2
        maximumFractionDigits = 2
    }

    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RectangleShape,
        colors = CardDefaults.cardColors(
            containerColor = HoneyDew
        )
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(IntrinsicSize.Min),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Absolute.SpaceBetween
        ) {
            Box(
                modifier = Modifier
                    .size(60.dp)
                    .clip(RoundedCornerShape(22.dp))
                    .background(VividBlue),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    painter = painterResource(getIconIdFromCategory(transaction.category)),
                    contentDescription = "Image category",
                    tint = HoneyDew,
                    modifier = Modifier.size(32.dp)
                    )
            }

            Column(
                modifier = Modifier
                    .weight(2f)
                    .padding(horizontal = 7.dp)
                    .fillMaxHeight(),
                verticalArrangement = Arrangement.SpaceEvenly
            ) {
                Text(
                    modifier = Modifier.width(100.dp),
                    text = transaction.title,
                    fontFamily = PoppinsFontFamily,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Medium,
                    color = Void,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )

                Text(
                    modifier = Modifier.width(100.dp),
                    text = transaction.message,
                    fontFamily = PoppinsFontFamily,
                    fontSize = 12.sp,
                    fontWeight = FontWeight.Medium,
                    color = OceanBlue,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
            }

            Text(
                modifier = Modifier.weight(2f),
                textAlign = TextAlign.End,
                text = if (transaction.type == TransactionType.EXPENSE) "-${formatter.format(transaction.amount)}₽"
                else "+${formatter.format(transaction.amount)}₽",
                fontFamily = PoppinsFontFamily,
                fontSize = 14.sp,
                fontWeight = FontWeight.Medium,
                color = if (transaction.type == TransactionType.EXPENSE) OceanBlue else CaribbeanGreen
            )
        }
    }
}

fun getIconIdFromCategory(category: Category): Int {
    return category.icon
}

@Preview(showBackground = true)
@Composable
fun CardPreview() {
    val transaction = Transaction(
        id = 1,
        title = "Salary",
        message = "TEST MESSAGE",
        amount = 120.4,
        type = TransactionType.EXPENSE,
        category = Category.ENTERTAINMENT,
        date = LocalDate.now()
    )

    CardWithMessage(transaction)
}