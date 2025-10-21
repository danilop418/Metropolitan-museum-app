package edu.iesam.metropolitan_museum.features.museum.data

import edu.iesam.metropolitan_museum.features.museum.data.remote.api.MuseumApiRemoteDataSource
import edu.iesam.metropolitan_museum.features.museum.data.remote.api.toModel
import edu.iesam.metropolitan_museum.features.museum.domain.MuseumRepository
import edu.iesam.metropolitan_museum.features.museum.domain.WorkOfArt

class MuseumDataRepository(
    private val apiRemoteDataSource: MuseumApiRemoteDataSource
) : MuseumRepository {

    override suspend fun findAll(): Result<List<WorkOfArt>> {
        return apiRemoteDataSource.findAll().map { apiModelsList ->
            apiModelsList.map { apiModel -> apiModel.toModel() }
        }
    }

    override suspend fun findById(id: String): Result<WorkOfArt> {
        return apiRemoteDataSource.findById(id).map { apiModel ->
            apiModel.toModel()
        }
    }
}