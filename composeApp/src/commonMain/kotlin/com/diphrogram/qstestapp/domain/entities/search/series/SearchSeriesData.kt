package com.diphrogram.qstestapp.domain.entities.search.series

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class SearchSeriesData(
    @SerialName("page")
    val page: Int,
    @SerialName("results")
    val results: List<SearchSeries>,
    @SerialName("total_pages")
    val totalPages: Int,
    @SerialName("total_results")
    val totalResults: Int
)