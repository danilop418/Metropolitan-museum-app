package edu.iesam.metropolitan_museum.features.museum.data.remote

import edu.iesam.metropolitan_museum.features.museum.domain.ErrorApp
import edu.iesam.metropolitan_museum.features.museum.domain.WorkOfArt
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.coroutineScope
import java.io.IOException
import java.net.SocketTimeoutException
import java.net.UnknownHostException

class MuseumApiRemoteDataSource(
    private val apiService: MuseumApiService = MuseumApiClient.apiService
) {

    suspend fun getWorkOfArt(limit: Int = 10): Result<List<WorkOfArt>> {
        return runCatching {
            val idsResponse = apiService.getObjectIds()

            if (!idsResponse.isSuccessful) {
                throw when (idsResponse.code()) {
                    in 500..599 -> ErrorApp.ServerErrorApp
                    else -> ErrorApp.ServerErrorApp
                }
            }

            val body = idsResponse.body() ?: throw ErrorApp.ServerErrorApp

            val objectIDs = body.objectIDs.take(limit)

            val works = fetchWorksInParallel(objectIDs)

            works

        }.mapError { exception ->
            when (exception) {
                is ErrorApp -> exception
                is UnknownHostException -> ErrorApp.InternetConexionError
                is SocketTimeoutException -> ErrorApp.InternetConexionError
                is IOException -> ErrorApp.InternetConexionError
                else -> ErrorApp.ServerErrorApp
            }.also { it.printStackTrace() }
        }
    }

    private suspend fun fetchWorksInParallel(ids: List<Int>): List<WorkOfArt> = coroutineScope {
        ids.map { id ->
            async {
                runCatching {
                    val response = apiService.getWorkOfArt(id)

                    if (response.isSuccessful && response.body() != null) {
                        response.body()!!
                    } else {
                        null
                    }
                }.getOrNull()
            }
        }.awaitAll().filterNotNull()
    }
}

private fun <T> Result<T>.mapError(transform: (Throwable) -> Throwable): Result<T> {
    return this.fold(
        onSuccess = { Result.success(it) },
        onFailure = { Result.failure(transform(it)) }
    )
}
