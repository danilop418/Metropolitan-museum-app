package edu.iesam.metropolitan_museum.features.museum.data.remote

import edu.iesam.metropolitan_museum.features.museum.domain.WorkOfArt
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.coroutineScope

class MuseumApiRemoteDataSource(
    private val apiService: MuseumApiService = MuseumApiClient.apiService
) {

    suspend fun getWorkOfArt(limit: Int = 10): Result<List<WorkOfArt>> {
        return try {
            val idsResponse = apiService.getObjectIds()

            if (!idsResponse.isSuccessful || idsResponse.body() == null) {
                return Result.failure(Exception("Error obteniendo IDs"))
            }

            val objectIDs = idsResponse.body()!!.objectIDs.take(limit)

            val works = fetchWorksInParallel(objectIDs)

            Result.success(works)

        } catch (e: Exception) {
            e.printStackTrace()
            Result.failure(e)
        }
    }

    private suspend fun fetchWorksInParallel(ids: List<Int>): List<WorkOfArt> = coroutineScope {
        ids.map { id ->
            async {
                try {
                    val response = apiService.getWorkOfArt(id)
                    if (response.isSuccessful) response.body() else null
                } catch (e: Exception) {
                    e.printStackTrace()
                    null
                }
            }
        }.awaitAll().filterNotNull()
    }
}