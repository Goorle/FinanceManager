package com.example.financemanager.presentation.home

import android.widget.Space
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.VerticalDivider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.financemanager.R
import com.example.financemanager.presentation.components.BottomBar
import com.example.financemanager.presentation.components.TopBarHome
import com.example.financemanager.ui.theme.CaribbeanGreen
import com.example.financemanager.ui.theme.HoneyDew
import com.example.financemanager.ui.theme.OceanBlue
import com.example.financemanager.ui.theme.PoppinsFontFamily
import com.example.financemanager.ui.theme.Void

@Composable
fun HomeScreen() {
    Scaffold(
        containerColor = CaribbeanGreen,
        topBar = {
            TopBarHome()
        },
        bottomBar = {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(HoneyDew)
            ){
                BottomBar()
            }
        }
    ) { innerPadding ->

        Column(
            modifier =  Modifier.padding(innerPadding)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight(0.2f),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.SpaceEvenly
            ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(IntrinsicSize.Min),
                horizontalArrangement = Arrangement.SpaceAround
            ) {
                Column() {
                    Row(
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Icon(
                            painter = painterResource(R.drawable.income),
                            contentDescription = "Income icon",
                            modifier = Modifier.size(12.dp),
                            tint = Void
                        )

                        Spacer(modifier = Modifier.size(5.dp))

                        Text(
                            text = "Total Balance",
                            fontFamily = PoppinsFontFamily,
                            fontSize = 12.sp,
                            fontWeight = FontWeight.Normal,
                            color = Void
                        )
                    }
                    Text(
                        text = "$7,783.00",
                        fontFamily = PoppinsFontFamily,
                        fontSize = 24.sp,
                        fontWeight = FontWeight.Bold,
                        color = HoneyDew
                    )
                }
                VerticalDivider(
                    thickness = 1.dp,
                    color = HoneyDew,
                    modifier = Modifier.padding(vertical = 5.dp)
                )
                Column() {
                    Row(
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Icon(
                            painter = painterResource(R.drawable.expense_vector),
                            contentDescription = "Expense icon",
                            modifier = Modifier.size(12.dp),
                            tint = Void
                        )

                        Spacer(modifier = Modifier.size(5.dp))

                        Text(
                            text = "Total expense",
                            fontFamily = PoppinsFontFamily,
                            fontSize = 12.sp,
                            fontWeight = FontWeight.Normal,
                            color = Void
                        )
                    }

                    Text(
                        text = "-$7,783.00",
                        fontFamily = PoppinsFontFamily,
                        fontSize = 24.sp,
                        fontWeight = FontWeight.Bold,
                        color = OceanBlue
                    )
                    }
                }
                Box(
                    modifier = Modifier
                        .fillMaxWidth(0.8f)
                        .height(25.dp)
                        .clip(RoundedCornerShape(14.dp))
                        .background(Void)
                        .align(Alignment.CenterHorizontally),
                    contentAlignment = Alignment.CenterStart
                ) {
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

                Row(
                    modifier = Modifier
                        .fillMaxWidth(),
                    horizontalArrangement = Arrangement.Center,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        painter = painterResource(R.drawable.check_vector),
                        contentDescription = "Check Icon",
                        tint = Void,
                        modifier = Modifier.size(12.dp)
                    )

                    Spacer(Modifier.size(5.dp))

                    Text(
                        text = "30% of your expenses, looks good.",
                        fontFamily = PoppinsFontFamily,
                        fontSize = 15.sp,
                        fontWeight = FontWeight.Normal,
                        color = Void,
                    )
                }
            }
            Box(
                modifier = Modifier.fillMaxSize(),

                ) {
                Box(
                    modifier = Modifier
                        .align(Alignment.BottomCenter)
                        .fillMaxWidth()
                        .fillMaxHeight(0.9f)
                        .clip(RoundedCornerShape(topStart = 72.dp, topEnd = 72.dp))
                        .background(HoneyDew),
                ) {

                }
            }
        }
        }

}

@Preview
@Composable
fun HomePreview() {
    HomeScreen()
}