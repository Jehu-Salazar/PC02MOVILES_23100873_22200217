package com.example.pc_002.presentation.listadoScreen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.pc_002.ui.theme.Pc_002Theme

data class EquipoUi(
    val nombre: String,
    val fundacion: Int,
    val titulos: Int,
    val imageUrl: String,
    val esEquipoGrande: Boolean
)

@Composable
fun ListadoScreen(
    equipos: List<EquipoUi>
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(top = 16.dp)
    ) {
        HeaderListado()

        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
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

@Composable
private fun HeaderListado() {
    Text(
        text = "Equipos Liga 1",
        fontSize = 22.sp,
        fontWeight = FontWeight.Bold,
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 8.dp)
    )
}

@Preview(showBackground = true)
@Composable
fun ListadoScreenPreview() {
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
        ),
        EquipoUi(
            nombre = "Sporting Cristal",
            fundacion = 1955,
            titulos = 20,
            imageUrl = "",
            esEquipoGrande = true
        )
    )

    Pc_002Theme {
        Surface {
            ListadoScreen(equipos = equiposDemo)
        }
    }
}

