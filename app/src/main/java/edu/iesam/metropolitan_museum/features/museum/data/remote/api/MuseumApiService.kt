package edu.iesam.metropolitan_museum.features.museum.data.remote.api

import retrofit2.Response
import retrofit2.http.GET

interface MuseumApiService {

    @GET("objects")
    suspend fun getAllWorkOfArt(): Response<List<WorkOfArtModel>>
}