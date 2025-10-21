package edu.iesam.metropolitan_museum.features.museum.core.api

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MuseumApiClient {
    private val BASE_URL = "https://collectionapi.metmuseum.org/public/collection/v1/"
    private val retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    fun <T> createService(typeClass: Class<T>): T {
        return retrofit.create(typeClass)
    }
}