package com.eduardo.pc2moviles.data.repository

import com.eduardo.pc2moviles.data.model.Equipo
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.tasks.await

class EquipoRepository {
    private val firestore = FirebaseFirestore.getInstance()
    private val equiposCollection = firestore.collection("equipos")

    suspend fun guardarEquipo(equipo: Equipo): Result<String> {
        return try {
            val documentRef = equiposCollection.add(
                hashMapOf(
                    "nombre" to equipo.nombre,
                    "anioFundacion" to equipo.anioFundacion,
                    "titulosGanados" to equipo.titulosGanados,
                    "imagenUrl" to equipo.imagenUrl
                )
            ).await()
            Result.success(documentRef.id)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    suspend fun obtenerEquipos(): Result<List<Equipo>> {
        return try {
            val snapshot = equiposCollection.get().await()
            val equipos = snapshot.documents.map { doc ->
                Equipo(
                    id = doc.id,
                    nombre = doc.getString("nombre") ?: "",
                    anioFundacion = doc.getLong("anioFundacion")?.toInt() ?: 0,
                    titulosGanados = doc.getLong("titulosGanados")?.toInt() ?: 0,
                    imagenUrl = doc.getString("imagenUrl") ?: ""
                )
            }
            Result.success(equipos)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}
