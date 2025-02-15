package com.diphrogram.qstestapp.domain.entities.details.cast

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Cast(
    @SerialName("adult")
    val adult: Boolean? = null,
    @SerialName("character")
    val character: String? = null,
    @SerialName("credit_id")
    val creditId: String? = null,
    @SerialName("gender")
    val gender: Int? = null,
    @SerialName("id")
    val id: Int? = null,
    @SerialName("known_for_department")
    val knownForDepartment: String? = null,
    @SerialName("name")
    val name: String,
    @SerialName("order")
    val order: Int? = null,
    @SerialName("original_name")
    val originalName: String? = null,
    @SerialName("popularity")
    val popularity: Double? = null,
    @SerialName("profile_path")
    val profilePath: String? = null
)