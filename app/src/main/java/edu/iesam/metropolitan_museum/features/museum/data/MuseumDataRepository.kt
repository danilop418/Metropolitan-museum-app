package edu.iesam.metropolitan_museum.features.museum.data

import edu.iesam.metropolitan_museum.features.museum.data.remote.api.MuseumApiRemoteDataSource
import edu.iesam.metropolitan_museum.features.museum.data.remote.api.toModel
import edu.iesam.metropolitan_museum.features.museum.domain.MuseumRepository
import edu.iesam.metropolitan_museum.features.museum.domain.WorkOfArt

class MuseumDataRepository(
    private val apiRemoteDataSource: MuseumApiRemoteDataSource
) : MuseumRepository {

    override suspend fun fetch(): Result<List<WorkOfArt>> {
        return apiRemoteDataSource.getAllWorkOfArt().map { apiModelsList ->
            apiModelsList.map { apiModel -> apiModel.toModel() }
        }
    }
}