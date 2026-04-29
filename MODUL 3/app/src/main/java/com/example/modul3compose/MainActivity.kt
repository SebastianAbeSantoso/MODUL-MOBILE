package com.example.modul3compose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.modul3compose.ui.theme.Modul3ComposeTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Modul3ComposeTheme(darkTheme = true) {
                val navController = rememberNavController()
                val viewModel: ComicViewModel = viewModel()

                NavHost(navController, startDestination = "main") {
                    composable("main") {
                        MainScreen(navController, viewModel)
                    }
                    composable ("details"){
                        DetailsScreen ( viewModel)
                    }
                    composable("settings"){
                        SettingsScreen()
                    }
                }
            }
        }
    }
}