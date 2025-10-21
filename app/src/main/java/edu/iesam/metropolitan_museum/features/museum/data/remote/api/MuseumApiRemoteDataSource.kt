package edu.iesam.metropolitan_museum.features.museum.data.remote.api

import android.util.Log
import edu.iesam.metropolitan_museum.features.museum.core.api.MuseumApiClient
import edu.iesam.metropolitan_museum.features.museum.domain.ErrorApp
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class MuseumApiRemoteDataSource(private val apiClient: MuseumApiClient) {
    suspend fun findAll(): Result<List<WorkOfArtModel>> {
        return withContext(Dispatchers.IO) {
            Log.d("API_DEBUG", "=== INICIO PETICIÓN ===")
            Log.d("API_DEBUG", "Thread: ${Thread.currentThread().name}")

            val apiService = apiClient.createService(MuseumApiService::class.java)
            Log.d("API_DEBUG", "ApiService creado")

            val response = apiService.findAll()
            Log.d("API_DEBUG", "Response code: ${response.code()}")
            Log.d("API_DEBUG", "Response message: ${response.message()}")

            if (response.isSuccessful) {
                val characters = response.body()!!

                Log.d("API_DEBUG", "ÉXITO: ${characters.size} personajes")
                Result.success(characters)
            } else {
                Log.e("API_DEBUG", "Error servidor: ${response.code()}")
                Result.failure(ErrorApp.ServerErrorApp)
            }
        }
    }

    suspend fun findById(id: String): Result<WorkOfArtModel> {
        return withContext(Dispatchers.IO) {
            Log.d("API_DEBUG", "Buscando Cuadro ID: $id")
            val apiService = apiClient.createService(MuseumApiService::class.java)
            val response = apiService.findById(id)

            if (response.isSuccessful && response.body() != null) {
                Log.d("API_DEBUG", "Cuadro encontrado: ${response.body()!!.title}")
                Result.success(response.body()!!)
            } else {
                Log.e("API_DEBUG", "Error: ${response.code()}")
                Result.failure(ErrorApp.ServerErrorApp)
            }
        }
    }
}