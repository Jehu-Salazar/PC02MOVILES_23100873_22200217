package com.example.pc_002.presentation.listadoScreen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.pc_002.ui.theme.Pc_002Theme

@Composable
fun HeaderListadoEquipo(
    modifier: Modifier = Modifier,
    totalEquipos: Int,
    totalTitulos: Int,
    equipoMasAntiguo: Int
) {
    Column(
        modifier = modifier.padding(horizontal = 16.dp, vertical = 8.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        Row(
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            StatCard(
                label = "Total de Equipos",
                value = totalEquipos.toString(),
                modifier = Modifier.weight(1f)
            )
            StatCard(
                label = "Total de Títulos",
                value = totalTitulos.toString(),
                modifier = Modifier.weight(1f)
            )
        }
        StatCard(
            label = "Equipo Más Antiguo",
            value = equipoMasAntiguo.toString(),
            modifier = Modifier.fillMaxWidth()
        )
    }
}

@Composable
private fun StatCard(
    label: String,
    value: String,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier,
        shape = RoundedCornerShape(12.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            Text(
                text = label,
                fontSize = 14.sp,
                color = Color.Gray
            )
            Text(
                text = value,
                fontSize = 28.sp,
                fontWeight = FontWeight.Bold,
                color = Color(0xFFE53935)
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun HeaderListadoEquipoPreview() {
    Pc_002Theme {
        HeaderListadoEquipo(
            totalEquipos = 3,
            totalTitulos = 72,
            equipoMasAntiguo = 1901
        )
    }
}
