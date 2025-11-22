package com.example.pc_002.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.pc_002.presentation.listadoScreen.EquipoUi
import com.example.pc_002.presentation.listadoScreen.ListadoScreen
import com.example.pc_002.presentation.registro.RegistroScreen

sealed class Screen(val route: String) {
    data object Registro : Screen("registro")
    data object Listado : Screen("listado")
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
            RegistroScreen(
                onGuardarExitoso = {
                    navController.navigate(Screen.Listado.route)
                }
            )
        }

        composable(Screen.Listado.route) {
            // Datos de ejemplo; reemplaza con tu lista real
            val equiposDemo = listOf(
                EquipoUi(
                    nombre = "Universitario de Deportes",
                    fundacion = 1924,
                    titulos = 27,
                    imageUrl = "",
                    esEquipoGrande = true
                ),
                EquipoUi(
                    nombre = "Alianza Lima",
                    fundacion = 1901,
                    titulos = 26,
                    imageUrl = "",
                    esEquipoGrande = true
                )
            )
            ListadoScreen(
                equipos = equiposDemo,
                onNuevoRegistro = {
                    navController.navigate(Screen.Registro.route)
                }
            )
        }

        // composable(Screen.Login.route) { LoginScreen(...) }
    }
}
