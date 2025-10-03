package edu.iesam.metropolitan_museum.features.museum.domain

class FetchSuperHeroeUseCase(val superHeroeRepository: MuseumRepository) {
    suspend fun fetch(): Result<List<WorkOfArt>> {
        return superHeroeRepository.fetch()
    }
}