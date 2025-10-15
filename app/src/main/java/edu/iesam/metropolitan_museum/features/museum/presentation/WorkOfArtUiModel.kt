package edu.iesam.metropolitan_museum.features.museum.presentation

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
