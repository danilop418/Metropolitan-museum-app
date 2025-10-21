package edu.iesam.metropolitan_museum.features.museum.presentation

import edu.iesam.metropolitan_museum.features.museum.domain.WorkOfArt

data class WorkOfArtUiModel(
    val objectID: Int,
    val title: String?,
    val artistDisplayName: String?,
    val artistNationality: String?,
    val objectDate: String?,
    val medium: String?,
    val dimensions: String?,
    val primaryImage: String?
)

fun WorkOfArt.toUiModel(): WorkOfArtUiModel {
    return WorkOfArtUiModel(
        objectID = this.objectID,
        title = this.title,
        artistDisplayName = this.artistDisplayName,
        artistNationality = this.artistNationality,
        objectDate = this.objectDate,
        medium = this.medium,
        dimensions = this.dimensions,
        primaryImage = this.primaryImage
    )
}