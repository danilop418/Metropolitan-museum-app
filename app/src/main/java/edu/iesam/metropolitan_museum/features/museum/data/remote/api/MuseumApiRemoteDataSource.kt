package edu.iesam.metropolitan_museum.features.museum.data.remote.api

import edu.iesam.metropolitan_museum.features.museum.core.api.MuseumApiClient
import edu.iesam.metropolitan_museum.features.museum.domain.ErrorApp
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class MuseumApiRemoteDataSource(
    private val apiClient: MuseumApiClient
) {
    suspend fun getAllWorkOfArt(): Result<List<WorkOfArtModel>> {
        return withContext(Dispatchers.IO) {
            val apiService = apiClient(MuseumApiClient::class.java)
            val resultMuseum = apiService.findAll()

            if (resultMuseum.isSuccessful && resultMuseum.body() != null) {
                Result.success(resultMuseum.body()!!)
            } else {
                Result.failure(ErrorApp.ServerErrorApp)
            }
        }
    }
}
