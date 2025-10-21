package edu.iesam.metropolitan_museum.features.museum.domain

class MuseumGetUseCase(private val repository: MuseumRepository) {
    suspend fun fetch(): Result<List<WorkOfArt>> {
        return repository.findAll()
    }
}