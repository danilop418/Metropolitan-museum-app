package edu.iesam.metropolitan_museum.features.museum.presentation

import edu.iesam.metropolitan_museum.features.museum.domain.WorkOfArt

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
