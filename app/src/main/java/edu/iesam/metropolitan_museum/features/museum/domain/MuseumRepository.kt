package edu.iesam.metropolitan_museum.features.museum.domain

interface MuseumRepository {
    suspend fun findAll(): Result<List<WorkOfArt>>
    suspend fun findById(id: String): Result<WorkOfArt>
}