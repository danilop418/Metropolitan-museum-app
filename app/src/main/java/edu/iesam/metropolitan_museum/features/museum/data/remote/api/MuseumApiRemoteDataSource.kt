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
            val apiService = apiClient.createService(MuseumApiService::class.java)
            val result = runCatching { apiService.getAllWorkOfArt() }

            result.fold(
                onSuccess = { response ->
                    if (response.isSuccessful && response.body() != null)
                        Result.success(response.body()!!)
                    else
                        Result.failure(ErrorApp.ServerErrorApp)
                },
                onFailure = {
                    Result.failure(ErrorApp.InternetConexionError)
                }
            )
        }
    }
}
