package edu.iesam.metropolitan_museum.features.museum.domain

class MuseumFindAllUseCase(val workOfArtRepository: MuseumRepository) {
    suspend fun findAll(): Result<List<WorkOfArt>> {
        return workOfArtRepository.findAll()
    }
}