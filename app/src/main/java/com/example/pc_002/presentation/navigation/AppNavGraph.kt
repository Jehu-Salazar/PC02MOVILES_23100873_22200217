package com.example.pc_002.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.pc_002.presentation.registro.RegistroScreen

sealed class Screen(val route: String) {
    data object Registro : Screen("registro")
    data object Login : Screen("login")
    // Agrega aquí más pantallas: Home, Details, etc.
}

@Composable
fun AppNavGraph(
    navController: NavHostController = rememberNavController()
) {
    NavHost(
        navController = navController,
        startDestination = Screen.Registro.route
    ) {
        composable(Screen.Registro.route) {
            RegistroScreen()
        }

        // composable(Screen.Login.route) { LoginScreen(...) }
    }
}
