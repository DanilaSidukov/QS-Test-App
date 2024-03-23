package com.diphrogram.qstestapp.domain.entities.shows

import com.diphrogram.qstestapp.common.Constants
import kotlinx.serialization.EncodeDefault
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Show @OptIn(ExperimentalSerializationApi::class) constructor(
    @SerialName("adult")
    val adult: Boolean,
    @SerialName("backdrop_path")
    val backdropPath: String,
    @SerialName("first_air_date")
    @EncodeDefault
    var firstAirDate: String = "",
    @SerialName("genre_ids")
    val genreIds: List<Int>,
    @SerialName("id")
    val id: Int,
    @SerialName("media_type")
    val mediaType: String,
    @SerialName("name")
    @EncodeDefault
    var name: String = "",
    @EncodeDefault
    val originCountry: List<String> = emptyList(),
    @SerialName("original_language")
    @EncodeDefault
    var originalLanguage: String = "",
    @SerialName("original_name")
    @EncodeDefault
    val originalName: String = "",
    @SerialName("original_title")
    @EncodeDefault
    var originalTitle: String = "",
    @SerialName("overview")
    val overview: String,
    @SerialName("popularity")
    val popularity: Double,
    @SerialName("poster_path")
    val posterPath: String,
    @SerialName("release_date")
    @EncodeDefault
    val releaseDate: String = "",
    @SerialName("title")
    @EncodeDefault
    var title: String = "",
    @SerialName("video")
    @EncodeDefault
    var video: Boolean = false,
    @SerialName("vote_average")
    val voteAverage: Double,
    @SerialName("vote_count")
    val voteCount: Int
) {
    val poster = "${Constants.IMAGE_URL}$posterPath"
}