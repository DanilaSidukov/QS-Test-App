package com.diphrogram.qstestapp.domain.entities.search.movies

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class SearchMovieData(
    @SerialName("page")
    val page: Int,
    @SerialName("results")
    val results: List<SearchMovie>,
    @SerialName("total_pages")
    val totalPages: Int,
    @SerialName("total_results")
    val totalResults: Int
)