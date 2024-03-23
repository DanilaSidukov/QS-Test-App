package com.diphrogram.qstestapp.domain.entities.search.series

import kotlinx.serialization.EncodeDefault
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class SearchSeries @OptIn(ExperimentalSerializationApi::class) constructor(
    @SerialName("adult")
    val adult: Boolean? = null,
    @SerialName("backdrop_path")
    @EncodeDefault
    val backdropPath: String? = null,
    @SerialName("first_air_date")
    val firstAirDate: String,
    @SerialName("genre_ids")
    val genreIds: List<Int>,
    @SerialName("id")
    val id: Int,
    @SerialName("name")
    @EncodeDefault
    val name: String? = null,
    @SerialName("origin_country")
    val originCountry: List<String> = emptyList(),
    @SerialName("original_language")
    val originalLanguage: String? = null,
    @SerialName("original_name")
    val originalName: String? = null,
    @SerialName("overview")
    val overview: String? = null,
    @SerialName("popularity")
    val popularity: Double? = null,
    @SerialName("poster_path")
    val posterPath: String? = null,
    @SerialName("vote_average")
    val voteAverage: Double? = null,
    @SerialName("vote_count")
    val voteCount: Int? = null
)