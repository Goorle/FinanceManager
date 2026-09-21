package com.example.financemanager.presentation.components.topbars

import androidx.compose.foundation.layout.fillMaxWidth
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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.financemanager.R
import com.example.financemanager.ui.theme.CaribbeanGreen
import com.example.financemanager.ui.theme.HoneyDew
import com.example.financemanager.ui.theme.PoppinsFontFamily
import com.example.financemanager.ui.theme.Void

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DefaultTopBar(title: String,
                  onClickBack: () -> Unit,
                  onClickNotification: () -> Unit
                  ) {
    TopAppBar(
        title = {
            Text(
                text = title,
                color = Void,
                fontFamily = PoppinsFontFamily,
                fontSize = 22.sp,
                fontWeight = FontWeight.SemiBold,
                textAlign = TextAlign.Center,
                modifier = Modifier.fillMaxWidth()
            )
        },

        navigationIcon = {
            IconButton(onClick = onClickBack
            ) {
                Icon(
                    painter = painterResource(R.drawable.bring_back_vector),
                    contentDescription = "Back",
                    modifier = Modifier.size(24.dp),
                    tint = HoneyDew
                )
            }
        },

        actions = {
            IconButton(
                onClick = onClickNotification,
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