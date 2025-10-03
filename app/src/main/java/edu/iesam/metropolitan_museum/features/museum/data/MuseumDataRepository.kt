package edu.iesam.metropolitan_museum.features.museum.data

import edu.iesam.metropolitan_museum.features.museum.data.remote.MuseumApiRemoteDataSource
import edu.iesam.metropolitan_museum.features.museum.domain.MuseumRepository
import edu.iesam.metropolitan_museum.features.museum.domain.WorkOfArt

class SuperHeroesDataRepository(
    private val apiRemoteDataSource: MuseumApiRemoteDataSource
) : MuseumRepository {

    override suspend fun fetch(): Result<List<WorkOfArt>> {
        return apiRemoteDataSource.getWorkOfArt()
    }
}