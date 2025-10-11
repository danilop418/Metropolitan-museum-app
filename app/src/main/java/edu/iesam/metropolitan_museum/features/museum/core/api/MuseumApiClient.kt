package edu.iesam.metropolitan_museum.features.museum.core.api

import edu.iesam.metropolitan_museum.features.museum.data.remote.api.MuseumApiService
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

object MuseumApiClient {
    private const val BASE_URL = "https://collectionapi.metmuseum.org/public/collection/v1/"
    private val okHttpClient = OkHttpClient.Builder()
        .connectTimeout(30, TimeUnit.SECONDS)
        .readTimeout(30, TimeUnit.SECONDS)
        .addInterceptor(HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY // Para ver los logs
        })
        .build()

    val apiService: MuseumApiService by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(MuseumApiService::class.java)
    }
}