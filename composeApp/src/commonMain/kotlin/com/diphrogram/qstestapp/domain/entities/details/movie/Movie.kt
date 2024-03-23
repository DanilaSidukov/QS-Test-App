package com.diphrogram.qstestapp.domain.entities.details.movie

import kotlinx.serialization.EncodeDefault
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Movie @OptIn(ExperimentalSerializationApi::class) constructor(
    @SerialName("adult")
    val adult: Boolean? = null,
    @SerialName("backdrop_path")
    @EncodeDefault
    val backdropPath: String? = null,
    @EncodeDefault
    @SerialName("belongs_to_collection")
    val belongsToCollection: BelongsToCollection? = null,
    @SerialName("budget")
    @EncodeDefault
    val budget: Int? = null,
    @SerialName("genres")
    val genres: List<Genre>,
    @SerialName("homepage")
    @EncodeDefault
    val homepage: String? = null,
    @SerialName("id")
    val id: Int,
    @SerialName("imdb_id")
    @EncodeDefault
    val imdbId: String? = null,
    @SerialName("original_language")
    val originalLanguage: String,
    @SerialName("original_title")
    val originalTitle: String,
    @SerialName("overview")
    val overview: String,
    @SerialName("popularity")
    @EncodeDefault
    val popularity: Double? = null,
    @SerialName("poster_path")
    val posterPath: String? = null,
    @SerialName("production_companies")
    @EncodeDefault
    val productionCompanies: List<ProductionCompany> = emptyList(),
    @SerialName("production_countries")
    @EncodeDefault
    val productionCountries: List<ProductionCountry> = emptyList(),
    @SerialName("release_date")
    val releaseDate: String? = null,
    val revenue: Int,
    @SerialName("runtime")
    val runtime: Int? = null,
    @EncodeDefault
    val spokenLanguages: List<SpokenLanguage> = emptyList(),
    val status: String,
    val tagline: String,
    @SerialName("title")
    val title: String,
    val video: Boolean,
    @EncodeDefault
    val voteAverage: Double? = null,
    @EncodeDefault
    val voteCount: Int? = null
)