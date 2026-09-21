package com.example.financemanager.presentation.components
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.financemanager.R
import com.example.financemanager.presentation.navigation.RoutesScreen
import com.example.financemanager.ui.theme.CaribbeanGreen
import com.example.financemanager.ui.theme.LightGreen
import com.example.financemanager.ui.theme.Void

data class BottomNavItem(
    val route: RoutesScreen,
    val icon: Painter,
)

@Composable
fun bottomNavItem(): List<BottomNavItem> = listOf(
    BottomNavItem(
        route = RoutesScreen.Home,
        icon = painterResource(R.drawable.home_vector)
    ),
    BottomNavItem(
        route = RoutesScreen.Analysis,
        icon = painterResource(R.drawable.analysis_vector)
    ),
    BottomNavItem(
        route = RoutesScreen.Transaction,
        icon = painterResource(R.drawable.transaction_vector)
    ),
    BottomNavItem(
        route = RoutesScreen.Categories,
        icon = painterResource(R.drawable.category_vector)
    ),
    BottomNavItem(
        route = RoutesScreen.Profile,
        icon = painterResource(R.drawable.profile_vector)
    ),
)

@Composable
fun BottomBar(
    items: List<BottomNavItem>,
    selectedRoute: RoutesScreen,
    onItemClick: (RoutesScreen) -> Unit
) {
    Row (
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(
                topStart = 72.dp,
                topEnd = 72.dp
            ))
            .background(LightGreen)
            .padding(24.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceEvenly
    ) {
        items.forEach { item ->
            val isSelected = item.route == selectedRoute
            Box(
                modifier = Modifier
                    .size(64.dp)
                    .clip(RoundedCornerShape(25.dp))
                    .background(if (isSelected) CaribbeanGreen else Color.Unspecified)
                    .clickable{
                        onItemClick(item.route)
                    }
                ,
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    painter = item.icon,
                    contentDescription = "Navigation Button",
                    tint = Void,
                    modifier = Modifier.size(32.dp)
                )
            }
        }
    }
}

@Preview
@Composable
fun BottomBarPreview() {
    //BottomBar()
}