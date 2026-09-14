package com.example.financemanager.presentation.home.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.financemanager.ui.theme.HoneyDew
import com.example.financemanager.ui.theme.PoppinsFontFamily
import com.example.financemanager.ui.theme.Void

@Composable
fun ProgressBarComponent() {
    Box(
        modifier = Modifier.fillMaxSize()
    ){
        Text(
            text = "30%",
            fontFamily = PoppinsFontFamily,
            fontSize = 12.sp,
            fontWeight = FontWeight.Normal,
            color = HoneyDew,
            modifier = Modifier.padding(start = 10.dp)
        )

        Box(
            modifier = Modifier
                .fillMaxWidth(0.7f)
                .height(25.dp)
                .clip(RoundedCornerShape(14.dp))
                .align(Alignment.CenterEnd)
                .background(HoneyDew),
            contentAlignment = Alignment.CenterEnd
        ) {
            Text(
                text = "$20,000.00",
                fontFamily = PoppinsFontFamily,
                fontSize = 12.sp,
                fontWeight = FontWeight.Normal,
                color = Void,
                modifier = Modifier.padding(end = 10.dp)
            )
        }
    }
}