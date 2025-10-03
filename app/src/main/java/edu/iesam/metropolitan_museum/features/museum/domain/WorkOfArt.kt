package edu.iesam.metropolitan_museum.features.museum.domain

data class WorkOfArt(
    val objectID: Int,
    val title: String?,
    val artistDisplayName: String?,
    val artistNationality: String?,
    val objectDate: String?,
    val medium: String?,
    val dimensions: String?,
    val primaryImage: String?
) {
}