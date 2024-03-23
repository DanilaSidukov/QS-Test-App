package com.diphrogram.qstestapp.domain.entities.details.series

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ProductionCompanies(
    @SerialName("id")
    val id: Int? = null,
    @SerialName("logo_path")
    val logoPath: String? = null,
    @SerialName("name")
    val name: String? = null,
    @SerialName("origin_country")
    val originCountry: String? = null
)