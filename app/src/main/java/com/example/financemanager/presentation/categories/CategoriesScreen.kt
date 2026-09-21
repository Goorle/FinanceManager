package com.example.financemanager.presentation.categories

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.financemanager.R
import com.example.financemanager.domain.model.Category
import com.example.financemanager.presentation.components.BalanceComponent
import com.example.financemanager.presentation.components.ProgressBarComponent
import com.example.financemanager.presentation.components.StatisticComponent
import com.example.financemanager.ui.theme.HoneyDew
import com.example.financemanager.ui.theme.LightBlue
import com.example.financemanager.ui.theme.PoppinsFontFamily
import com.example.financemanager.ui.theme.Void

@Composable
fun CategoriesScreen(
    onClickCategory: (Category) -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(0.2f),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceEvenly
        ) {
            BalanceComponent()
            ProgressBarComponent()
            StatisticComponent()
        }
        Box(
            modifier = Modifier
                .fillMaxSize()
                .fillMaxHeight(0.9f)
                .clip(RoundedCornerShape(topStart = 72.dp, topEnd = 72.dp))
                .background(HoneyDew),
        ) {
            LazyVerticalGrid(
                modifier = Modifier.fillMaxSize(),
                horizontalArrangement = Arrangement.SpaceEvenly,
                verticalArrangement = Arrangement.SpaceEvenly,
                columns = GridCells.Fixed(3)
            ) {
                items(Category.entries) { category ->
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        IconButton(
                            onClick = {
                                onClickCategory(category)
                            },
                            modifier = Modifier
                                .size(100.dp),
                            colors = IconButtonDefaults.iconButtonColors(
                                containerColor = LightBlue
                            ),
                            shape = RoundedCornerShape(22.dp)
                        ) {
                            Icon(
                                painter = painterResource(category.icon),
                                contentDescription = "Categories icon",
                                modifier = Modifier.size(50.dp),
                                tint = HoneyDew
                            )
                        }
                        Text(
                            text = category.displayName,
                            fontFamily = PoppinsFontFamily,
                            fontWeight = FontWeight.Medium,
                            fontSize = 14.sp,
                            color = Void
                        )
                    }
                }
            }
        }
    }
}

@Preview
@Composable
fun CategoriesPreview() {
    CategoriesScreen(
        onClickCategory = {}
    )
}