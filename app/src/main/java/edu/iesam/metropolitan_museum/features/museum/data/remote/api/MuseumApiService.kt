package edu.iesam.metropolitan_museum.features.museum.data.remote.api

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface MuseumApiService {

    @GET("objects")
    suspend fun findAll(): Response<List<WorkOfArtModel>>

    @GET("objects/{id}")
    suspend fun findById(@Path("id") id: String): Response<WorkOfArtModel>
}