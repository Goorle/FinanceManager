package com.example.financemanager.presentation.addExpenses

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.financemanager.domain.model.Category
import com.example.financemanager.ui.theme.CaribbeanGreen
import com.example.financemanager.ui.theme.HoneyDew
import com.example.financemanager.ui.theme.LightBlue
import com.example.financemanager.ui.theme.LightGreen
import com.example.financemanager.ui.theme.OceanBlue
import com.example.financemanager.ui.theme.PoppinsFontFamily
import com.example.financemanager.ui.theme.Void

@Composable
fun CardCategory(category: Category,
                 chooseCategory: Category,
                 onClickCategory: () -> Unit) {
    Card(
        shape = RoundedCornerShape(18.dp),
        modifier = Modifier
            .size(120.dp),
        colors = CardDefaults.cardColors(
            containerColor = if (category == chooseCategory) CaribbeanGreen else LightGreen,
        ),
        elevation = CardDefaults.cardElevation(4.dp),
        onClick = onClickCategory
    ) {
        Box (
            modifier = Modifier.fillMaxSize().padding(8.dp),
            contentAlignment = Alignment.Center
        ) {
            Icon(
                painter = painterResource(category.icon),
                contentDescription = "Category icon",
                modifier = Modifier.size(48.dp),
                tint = if (category == chooseCategory) HoneyDew else Void
            )

            Text(
                text = category.displayName,
                fontFamily = PoppinsFontFamily,
                fontSize = 12.sp,
                fontWeight = FontWeight.Medium,
                color = if (category == chooseCategory) HoneyDew else Void,
                modifier = Modifier.align(Alignment.BottomCenter)
            )
        }

    }
}

@Preview(showBackground = true)
@Composable
fun CardPreview() {
    //CardCategory(Category.ENTERTAINMENT, Category.ENTERTAINMENT)
}