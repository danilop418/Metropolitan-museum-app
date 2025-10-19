package edu.iesam.metropolitan_museum.features.museum.domain

class MuseumFindByIdUseCase(private val workOfArtRepository: MuseumRepository) {
    suspend fun findById(id: String): Result<WorkOfArt> {
        return workOfArtRepository.getById(id)
    }
}