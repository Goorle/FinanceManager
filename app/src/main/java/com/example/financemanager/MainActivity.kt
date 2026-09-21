package com.example.financemanager

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.example.financemanager.presentation.navigation.AppScaffold
import com.example.financemanager.ui.theme.FinanceManagerTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            FinanceManagerTheme {
                AppScaffold()
            }
        }
    }
}