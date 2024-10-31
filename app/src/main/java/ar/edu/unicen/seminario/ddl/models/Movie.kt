package ar.edu.unicen.seminario.ddl.models

data class Movie(
    val id: Int,
    val title: String,
    val overview: String,
    val poster_path: String?,
    val releaseDate: String?
)
