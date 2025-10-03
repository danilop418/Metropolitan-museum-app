package edu.iesam.metropolitan_museum.features.museum.data.remote

import edu.iesam.metropolitan_museum.features.museum.domain.WorkOfArt
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface MuseumApiService {

    @GET("objects")
    suspend fun getObjectIds(): Response<ObjectIdsResponse>

    @GET("objects/{id}")
    suspend fun getWorkOfArt(@Path("id") id: Int): Response<WorkOfArt>
}