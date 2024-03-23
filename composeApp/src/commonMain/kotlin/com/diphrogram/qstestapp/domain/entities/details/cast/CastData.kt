package com.diphrogram.qstestapp.domain.entities.details.cast

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class CastData(
    @SerialName("cast")
    val cast: List<Cast>,
    @SerialName("crew")
    val crew: List<Crew>,
    @SerialName("id")
    val id: Int
)