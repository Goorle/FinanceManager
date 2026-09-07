package com.example.financemanager.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.ShapeDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.financemanager.R
import com.example.financemanager.ui.theme.HoneyDew
import com.example.financemanager.ui.theme.LightGreen
import com.example.financemanager.ui.theme.Void

@Composable
fun BottomBar() {
    val listNavigationIcons = listOf<Int>(
        R.drawable.home_vector,
        R.drawable.analysis_vector,
        R.drawable.transaction_vector,
        R.drawable.category_vector,
        R.drawable.profile_vector,
    )
    NavigationBar (
        modifier = Modifier
            .clip(RoundedCornerShape(
                topStart = 72.dp,
                topEnd = 72.dp
            )),
        containerColor = LightGreen,

    ) {
        LazyRow(
            modifier = Modifier
                .fillMaxWidth()
                .padding(30.dp),
            horizontalArrangement = Arrangement.SpaceAround
        ) {
            items(listNavigationIcons) { iconId ->
                IconButtonNavigationBar(iconId)
            }
        }
    }
}

@Composable
fun IconButtonNavigationBar(idDrawable: Int) {
    IconButton(
        onClick = {},
        modifier = Modifier.size(32.dp),
        shape = RectangleShape,
        colors = IconButtonDefaults.iconButtonColors(
            containerColor = Color.Transparent,
            contentColor = Color.Unspecified,
        ),
    ) {
        Icon(
            painter = painterResource(idDrawable),
            contentDescription = "Navigation Button",
            tint = Void
        )
    }
}

@Preview
@Composable
fun BottomBarPreview() {
    BottomBar()
}