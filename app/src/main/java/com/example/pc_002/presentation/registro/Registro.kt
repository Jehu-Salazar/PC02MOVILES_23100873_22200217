package com.example.pc_002.presentation.registro

import android.util.Patterns
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.pc_002.ui.theme.Pc_002Theme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RegistroScreen() {
    var nombreEquipo by remember { mutableStateOf("") }
    var anioFundacion by remember { mutableStateOf("") }
    var titulosGanados by remember { mutableStateOf("") }
    var urlImagen by remember { mutableStateOf("") }

    var nombreError by remember { mutableStateOf<String?>(null) }
    var anioError by remember { mutableStateOf<String?>(null) }
    var titulosError by remember { mutableStateOf<String?>(null) }
    var urlError by remember { mutableStateOf<String?>(null) }

    fun validateFields(): Boolean {
        nombreError = if (nombreEquipo.isBlank()) "El nombre no puede estar vacío" else null
        anioError = when {
            anioFundacion.isBlank() -> "El año no puede estar vacío"
            anioFundacion.length != 4 || anioFundacion.toIntOrNull() == null -> "Año inválido"
            else -> null
        }
        titulosError = when {
            titulosGanados.isBlank() -> "La cantidad de títulos no puede estar vacía"
            titulosGanados.toIntOrNull() == null -> "Debe ser un número"
            else -> null
        }
        urlError = when {
            urlImagen.isBlank() -> "La URL no puede estar vacía"
            !Patterns.WEB_URL.matcher(urlImagen).matches() -> "URL inválida"
            else -> null
        }

        return nombreError == null && anioError == null && titulosError == null && urlError == null
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = "Registro Liga 1") },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.surface
                )
            )
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(horizontal = 16.dp)
                .verticalScroll(rememberScrollState()),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Top
        ) {
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 20.dp),
                elevation = CardDefaults.cardElevation(defaultElevation = 8.dp),
                colors = CardDefaults.cardColors(
                    containerColor = MaterialTheme.colorScheme.background
                )
            ) {
                Column(
                    modifier = Modifier.padding(24.dp),
                    horizontalAlignment = Alignment.Start
                ) {
                    Text(
                        text = "Registro de Equipos",
                        fontSize = 24.sp,
                        fontWeight = FontWeight.Bold
                    )
                    Text(
                        text = "Liga 1 - Fútbol Profesional Peruano",
                        fontSize = 14.sp,
                        color = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.7f)
                    )

                    Spacer(modifier = Modifier.height(24.dp))

                    OutlinedTextField(
                        value = nombreEquipo,
                        onValueChange = {
                            nombreEquipo = it
                            nombreError = null
                        },
                        label = { Text("Nombre del equipo") },
                        placeholder = { Text("Ej: Universitario de Deportes") },
                        modifier = Modifier.fillMaxWidth(),
                        isError = nombreError != null,
                        supportingText = {
                            if (nombreError != null) {
                                Text(text = nombreError!!)
                            }
                        }
                    )

                    Spacer(modifier = Modifier.height(8.dp))

                    OutlinedTextField(
                        value = anioFundacion,
                        onValueChange = {
                            anioFundacion = it
                            anioError = null
                        },
                        label = { Text("Año de fundación") },
                        placeholder = { Text("Ej: 1924") },
                        modifier = Modifier.fillMaxWidth(),
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                        isError = anioError != null,
                        supportingText = {
                            if (anioError != null) {
                                Text(text = anioError!!)
                            }
                        }
                    )

                    Spacer(modifier = Modifier.height(8.dp))

                    OutlinedTextField(
                        value = titulosGanados,
                        onValueChange = {
                            titulosGanados = it
                            titulosError = null
                        },
                        label = { Text("Títulos ganados") },
                        placeholder = { Text("Ej: 27") },
                        modifier = Modifier.fillMaxWidth(),
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                        isError = titulosError != null,
                        supportingText = {
                            if (titulosError != null) {
                                Text(text = titulosError!!)
                            }
                        }
                    )

                    Spacer(modifier = Modifier.height(8.dp))

                    OutlinedTextField(
                        value = urlImagen,
                        onValueChange = {
                            urlImagen = it
                            urlError = null
                        },
                        label = { Text("URL de la imagen del equipo") },
                        placeholder = { Text("https://ejemplo.com/logo-equipo.png") },
                        modifier = Modifier.fillMaxWidth(),
                        isError = urlError != null,
                        supportingText = {
                            if (urlError != null) {
                                Text(text = urlError!!)
                            }
                        }
                    )

                    Spacer(modifier = Modifier.height(24.dp))

                    Button(
                        onClick = {
                            if (validateFields()) {
                                // TODO: guardar datos
                            }
                        },
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(50.dp),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = MaterialTheme.colorScheme.primary
                        ),
                        shape = MaterialTheme.shapes.medium
                    ) {
                        Text(text = "Guardar equipo", fontSize = 16.sp)
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun RegistroScreenPreview() {
    Pc_002Theme {
        RegistroScreen()
    }
}
