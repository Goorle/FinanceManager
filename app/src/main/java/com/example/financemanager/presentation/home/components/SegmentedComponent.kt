package com.example.financemanager.presentation.home.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.SegmentedButton
import androidx.compose.material3.SegmentedButtonDefaults
import androidx.compose.material3.SingleChoiceSegmentedButtonRow
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.financemanager.ui.theme.CaribbeanGreen
import com.example.financemanager.ui.theme.Cyprus
import com.example.financemanager.ui.theme.LightGreen
import com.example.financemanager.ui.theme.PoppinsFontFamily
import com.example.financemanager.ui.theme.Void

@Composable
fun SegmentedComponent() {
    var selectedIndex by remember{ mutableStateOf(0) }
    val options = listOf("Daily", "Weekly", "Monthly")
    Box {
        SingleChoiceSegmentedButtonRow(
            modifier = Modifier
                .clip(RoundedCornerShape(19.dp))
                .background(LightGreen)
                .fillMaxWidth(0.9f),
            space = 15.dp,

        ) {
            options.forEachIndexed { index, label ->
                SegmentedButton(
                    onClick = {
                        selectedIndex = index
                    },
                    selected = index == selectedIndex,
                    label = {
                        Text(
                            text = label,
                            fontFamily = PoppinsFontFamily,
                            fontWeight = FontWeight.Medium,
                            fontSize = 14.sp,
                            color = Void
                        )
                    },
                    shape = SegmentedButtonDefaults.itemShape(
                        index = index,
                        count = options.size,
                        baseShape = RoundedCornerShape(19.dp)
                    ),
                    colors = SegmentedButtonDefaults.colors(
                        activeContainerColor = CaribbeanGreen,
                        activeBorderColor = LightGreen,
                        inactiveBorderColor = LightGreen,
                    ),
                    icon = {}

                )
            }
        }
    }
}


@Preview
@Composable
fun SegmentedPreview() {
    SegmentedComponent()
}