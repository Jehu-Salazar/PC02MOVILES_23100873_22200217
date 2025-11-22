package com.eduardo.pc2moviles.presentation.listadoScreen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.eduardo.pc2moviles.ui.theme.Pc_002Theme

@Composable
fun CardEquipos(
    modifier: Modifier = Modifier,
    nombre: String,
    fundacion: Int,
    titulos: Int,
    imageUrl: String,
    esEquipoGrande: Boolean
) {
    Card(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 8.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = Color(0xFFF8F8F8))
    ) {
        Column {
            // Imagen del equipo con Coil
            if (imageUrl.isNotBlank()) {
                AsyncImage(
                    model = imageUrl,
                    contentDescription = "Logo de $nombre",
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(200.dp),
                    contentScale = ContentScale.Crop
                )
            } else {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(200.dp)
                        .background(Color(0xFFE0E0E0)),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = "Sin imagen",
                        color = Color.DarkGray,
                        fontSize = 14.sp
                    )
                }
            }

            Column(modifier = Modifier.padding(16.dp)) {
                Text(
                    text = nombre,
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.Black
                )
                Spacer(modifier = Modifier.height(12.dp))
                InfoRow(label = "Fundado en", value = fundacion.toString())
                Spacer(modifier = Modifier.height(8.dp))
                InfoRow(label = "Títulos", value = "$titulos campeonatos")
                Spacer(modifier = Modifier.height(16.dp))
                if (esEquipoGrande) {
                    ChipEquipoGrande()
                }
            }
        }
    }
}

@Composable
private fun InfoRow(label: String, value: String) {
    Column(
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = label,
            fontSize = 12.sp,
            color = Color.Gray
        )
        Spacer(modifier = Modifier.height(2.dp))
        Text(
            text = value,
            fontSize = 14.sp,
            color = Color.Gray
        )
    }
}

@Composable
private fun ChipEquipoGrande() {
    Surface(
        shape = RoundedCornerShape(50),
        color = Color(0xFFFFF5E1),
        contentColor = Color(0xFFF9A825)
    ) {
        Row(
            modifier = Modifier.padding(horizontal = 12.dp, vertical = 6.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(
                modifier = Modifier
                    .size(8.dp)
                    .background(Color(0xFFF9A825), shape = RoundedCornerShape(50))
            )
            Spacer(modifier = Modifier.size(6.dp))
            Text(
                text = "Equipo grande",
                fontWeight = FontWeight.Bold,
                fontSize = 12.sp
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun CardEquiposPreview() {
    Pc_002Theme {
        Surface {
            CardEquipos(
                nombre = "Universitario de Deportes",
                fundacion = 1924,
                titulos = 27,
                imageUrl = "",
                esEquipoGrande = true
            )
        }
    }
}
