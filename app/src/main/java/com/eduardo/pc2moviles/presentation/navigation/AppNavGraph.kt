package com.eduardo.pc2moviles.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.eduardo.pc2moviles.presentation.listadoScreen.ListadoScreen
import com.eduardo.pc2moviles.presentation.registro.RegistroScreen

sealed class Screen(val route: String) {
    data object Registro : Screen("registro")
    data object Listado : Screen("listado")
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
            RegistroScreen(
                onRegistroExitoso = {
                    navController.navigate(Screen.Listado.route) {
                        popUpTo(Screen.Registro.route) { inclusive = true }
                    }
                }
            )
        }

        composable(Screen.Listado.route) {
            ListadoScreen(
                onNuevoRegistro = {
                    navController.navigate(Screen.Registro.route)
                }
            )
        }
    }
}
