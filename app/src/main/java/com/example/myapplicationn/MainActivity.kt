package com.example.myapplicationn

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.example.myapplicationn.ui.theme.MyApplicationnTheme



import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.rememberNavController


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val navController = rememberNavController()

            // Initialize the SearchViewModel using the viewModel() function
            val viewModel: SearchViewModel = viewModel()

            MyApplicationnTheme {
                SetupNavigation(navController = navController, viewModel = viewModel)
            }
        }
    }
}