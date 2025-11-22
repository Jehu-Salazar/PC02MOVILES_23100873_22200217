package com.eduardo.pc2moviles.presentation.listadoScreen

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.eduardo.pc2moviles.data.model.Equipo
import com.eduardo.pc2moviles.data.repository.EquipoRepository
import com.eduardo.pc2moviles.ui.theme.Pc_002Theme

data class EquipoUi(
    val nombre: String,
    val fundacion: Int,
    val titulos: Int,
    val imageUrl: String,
    val esEquipoGrande: Boolean
)

fun Equipo.toEquipoUi(): EquipoUi {
    return EquipoUi(
        nombre = this.nombre,
        fundacion = this.anioFundacion,
        titulos = this.titulosGanados,
        imageUrl = this.imagenUrl,
        esEquipoGrande = this.titulosGanados >= 10
    )
}

@Composable
fun ListadoScreen(
    onNuevoRegistro: () -> Unit = {}
) {
    val repository = remember { EquipoRepository() }
    var equipos by remember { mutableStateOf<List<EquipoUi>>(emptyList()) }
    var isLoading by remember { mutableStateOf(true) }
    var errorMessage by remember { mutableStateOf<String?>(null) }

    LaunchedEffect(Unit) {
        repository.obtenerEquipos()
            .onSuccess { listaEquipos ->
                equipos = listaEquipos.map { it.toEquipoUi() }
                isLoading = false
            }
            .onFailure { e ->
                errorMessage = "Error al cargar: ${e.message}"
                isLoading = false
            }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(top = 16.dp)
    ) {
        HeaderListado(onNuevoRegistro = onNuevoRegistro)

        when {
            isLoading -> {
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    CircularProgressIndicator()
                }
            }
            errorMessage != null -> {
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    Text(text = errorMessage!!)
                }
            }
            equipos.isEmpty() -> {
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    Text(text = "No hay equipos registrados")
                }
            }
            else -> {
                LazyColumn(
                    modifier = Modifier.fillMaxSize()
                ) {
                    items(equipos) { equipo ->
                        CardEquipos(
                            nombre = equipo.nombre,
                            fundacion = equipo.fundacion,
                            titulos = equipo.titulos,
                            imageUrl = equipo.imageUrl,
                            esEquipoGrande = equipo.esEquipoGrande
                        )
                    }
                }
            }
        }
    }
}

@Composable
private fun HeaderListado(
    onNuevoRegistro: () -> Unit = {}
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 8.dp)
    ) {
        Text(
            text = "Equipos Liga 1",
            fontSize = 22.sp,
            fontWeight = FontWeight.Bold
        )
        Button(
            onClick = onNuevoRegistro,
            modifier = Modifier.padding(top = 8.dp)
        ) {
            Text(text = "Nuevo Registro")
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ListadoScreenPreview() {
    Pc_002Theme {
        Surface {
            ListadoScreen()
        }
    }
}

