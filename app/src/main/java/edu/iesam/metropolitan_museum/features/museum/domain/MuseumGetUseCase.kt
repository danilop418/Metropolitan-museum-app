package edu.iesam.metropolitan_museum.features.museum.domain

class MuseumGetUseCase(val workOfArtRepository: MuseumRepository) {
    suspend fun fetch(): Result<List<WorkOfArt>> {
        return workOfArtRepository.fetch()
    }
}