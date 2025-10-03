package edu.iesam.metropolitan_museum.features.museum.domain

interface MuseumRepository {
    suspend fun fetch(): Result<List<WorkOfArt>>
}