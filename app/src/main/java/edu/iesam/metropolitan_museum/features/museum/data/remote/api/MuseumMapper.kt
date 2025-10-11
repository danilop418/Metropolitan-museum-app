package edu.iesam.metropolitan_museum.features.museum.data.remote.api

import edu.iesam.metropolitan_museum.features.museum.domain.WorkOfArt

fun WorkOfArtModel.toModel(): WorkOfArt {
    return WorkOfArt(
        this.objectID,
        this.title,
        this.artistDisplayName,
        this.artistNationality,
        this.objectDate,
        this.medium,
        this.dimensions,
        this.primaryImage
    )
}