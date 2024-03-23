package com.diphrogram.qstestapp.domain.entities.details.series

import com.diphrogram.qstestapp.domain.entities.details.movie.Genre
import kotlinx.serialization.EncodeDefault
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Series @OptIn(ExperimentalSerializationApi::class) constructor(
    @SerialName("adult")
    val adult: Boolean? = null,
    @SerialName("backdrop_path")
    @EncodeDefault
    val backdropPath: String? = null,
    @SerialName("created_by")
    @EncodeDefault
    val createdBy: List<CreatedByItem> = emptyList(),
    @SerialName("episode_run_time")
    @EncodeDefault
    val episodeRunTime: List<Int> = emptyList(),
    @SerialName("first_air_date")
    val firstAirDate: String? = null,
    @SerialName("genres")
    val genres: List<Genre>,
    @EncodeDefault
    val homepage: String? = null,
    @SerialName("id")
    val id: Int,
    @SerialName("in_production")
    @EncodeDefault
    val inProduction: Boolean? = null,
    @SerialName("languages")
    val languages: List<String>,
    @EncodeDefault
    val lastAirDate: String? = null,
    @EncodeDefault
    val lastEpisodeToAir: LastEpisodeToAir? = null,
    @SerialName("name")
    val name: String,
    @SerialName("networks")
    @EncodeDefault
    val networks: List<Network> = emptyList(),
    @SerialName("next_episode_to_air")
    @EncodeDefault
    val nextEpisodeToAir: NextEpisodeToAir? = null,
    val numberOfEpisodes: Int? = null,
    val numberOfSeasons: Int? = null,
    @SerialName("origin_country")
    val originCountry: List<String>,
    @SerialName("original_language")
    val originalLanguage: String,
    @SerialName("original_name")
    val originalName: String,
    @SerialName("overview")
    val overview: String,
    val popularity: Double,
    @SerialName("poster_path")
    val posterPath: String?,
    @EncodeDefault
    val productionCompanies: List<ProductionCompanies> = emptyList(),
    @EncodeDefault
    val productionCountries: List<ProductionCountry> = emptyList(),
    val seasons: List<Season>,
    @EncodeDefault
    val spokenLanguages: List<SpokenLanguage> = emptyList(),
    @EncodeDefault
    val status: String? = null,
    @EncodeDefault
    val tagline: String? = null,
    @EncodeDefault
    val type: String? = null,
    @EncodeDefault
    val voteAverage: Double? = null,
    @EncodeDefault
    val voteCount: Int? = null
)