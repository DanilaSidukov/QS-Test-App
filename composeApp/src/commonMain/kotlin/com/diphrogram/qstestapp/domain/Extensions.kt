package com.diphrogram.qstestapp.domain

import com.diphrogram.qstestapp.domain.entities.details.ShowDetails
import com.diphrogram.qstestapp.domain.entities.details.movie.Genre
import com.diphrogram.qstestapp.domain.entities.details.movie.Movie
import com.diphrogram.qstestapp.domain.entities.details.series.Series

fun Movie?.toShowDetails(): ShowDetails? {
	if (this == null) return null
	val isRuntimeExist = this.runtime != null
	val duration = if (isRuntimeExist) "${this.runtime} m" else "-"
	val releaseDate = if (!this.releaseDate.isNullOrEmpty()) this.releaseDate else "-"
	return ShowDetails(
		title = this.title.ifEmpty { this.originalTitle },
		releaseDate = releaseDate,
		duration = duration,
		genres = this.genres.toGenreList(),
		poster = posterPath,
		description = this.overview
	)
}

fun Series.toShowDetails(): ShowDetails {
	val isRuntimeExist = this.episodeRunTime.isNotEmpty()
	val duration = if (isRuntimeExist) "${this.episodeRunTime.sum()} m" else "-"
	return ShowDetails(
		title = this.name.ifEmpty { this.originalName },
		releaseDate = this.firstAirDate ?: "-",
		duration = duration,
		genres = this.genres.toGenreList(),
		poster = posterPath,
		description = this.overview
	)
}

fun List<Genre>.toGenreList(): String {
	return if (this.isNotEmpty()) this.map(Genre::name).joinToString() else "-"
}