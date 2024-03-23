package com.diphrogram.qstestapp.domain.entities.search.movies

import kotlinx.serialization.EncodeDefault
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class SearchMovie(
    @SerialName("adult")
    val adult: Boolean? = null,
    @SerialName("backdrop_path")
    @EncodeDefault
    val backdropPath: String? = null,
    @SerialName("genre_ids")
    @EncodeDefault
    val genreIds: List<Int> = emptyList(),
    @SerialName("id")
    val id: Int,
    @SerialName("original_language")
    val originalLanguage: String,
    @SerialName("original_title")
    val originalTitle: String,
    @SerialName("overview")
    val overview: String,
    @EncodeDefault
    @SerialName("popularity")
    val popularity: Double? = null,
    @SerialName("poster_path")
    @EncodeDefault
    val posterPath: String? = null,
    @SerialName("release_date")
    @EncodeDefault
    val releaseDate: String? = null,
    @SerialName("title")
    val title: String,
    @SerialName("video")
    @EncodeDefault
    val video: Boolean? = null,
    @SerialName("vote_average")
    @EncodeDefault
    val voteAverage: Double? = null,
    @SerialName("vote_count")
    @EncodeDefault
    val voteCount: Int? = null
)