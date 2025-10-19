package edu.iesam.metropolitan_museum.features.museum.domain

interface MuseumRepository {
    suspend fun findAll(): Result<List<WorkOfArt>>

    suspend fun getById(id: String): Result<WorkOfArt>
}