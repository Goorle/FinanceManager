package com.example.financemanager.presentation.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.financemanager.R
import com.example.financemanager.ui.theme.CaribbeanGreen
import com.example.financemanager.ui.theme.HoneyDew
import com.example.financemanager.ui.theme.PoppinsFontFamily
import com.example.financemanager.ui.theme.Void

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopBarHome() {
    TopAppBar(
        title = {
            Column() {
                Text(
                    text = "Hi, Welcome Back",
                    color = Void,
                    fontFamily = PoppinsFontFamily,
                    fontSize = 22.sp,
                    fontWeight = FontWeight.SemiBold
                )
                Text(
                    text = "Have a nice day",
                    color = Void,
                    fontFamily = PoppinsFontFamily,
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Normal
                )
            }
        },
        actions = {
            IconButton(
                onClick = {},
                colors = IconButtonDefaults.iconButtonColors(
                    containerColor = HoneyDew
                ),
                modifier = Modifier.size(48.dp)
            ) {
                Icon(
                    painter = painterResource(R.drawable.notification_vector),
                    contentDescription = "Notification",
                    modifier = Modifier
                        .size(32.dp),
                    tint = Void
                )
            }
        },
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = CaribbeanGreen,
            subtitleContentColor = Void
        ),

        modifier = Modifier.padding(horizontal = 15.dp)
    )
}

@Preview
@Composable
fun TopBarPreview() {
    TopBarHome()
}