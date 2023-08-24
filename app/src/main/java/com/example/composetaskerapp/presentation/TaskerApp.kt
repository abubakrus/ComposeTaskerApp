package com.example.composetaskerapp.presentation

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavGraph
import androidx.navigation.compose.rememberNavController
import com.example.composetaskerapp.presentation.screens.NavGraphs
import com.ramcosta.composedestinations.DestinationsNavHost

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TaskerApp() {
    val navHostController = rememberNavController()

    Scaffold{innerPaddings->
        DestinationsNavHost(
            modifier = Modifier.padding(innerPaddings),
            navGraph = NavGraphs.root,
            navController = navHostController
        )
    }
}