package com.diphrogram.qstestapp.data.repository

import com.diphrogram.qstestapp.domain.entities.details.ShowDetails
import com.diphrogram.qstestapp.domain.entities.details.cast.Cast
import com.diphrogram.qstestapp.domain.entities.details.cast.CastData
import com.diphrogram.qstestapp.domain.entities.details.movie.Movie
import com.diphrogram.qstestapp.domain.entities.details.series.Series
import com.diphrogram.qstestapp.domain.entities.shows.Show
import com.diphrogram.qstestapp.domain.entities.shows.ShowPages
import com.diphrogram.qstestapp.domain.repository.IShowRepository
import com.diphrogram.qstestapp.domain.source.remote.IShowSource

class ShowRepository(private val showSource: IShowSource): IShowRepository {

	override suspend fun getTrendingShow(): Result<ShowPages?> {
		return showSource.getTrendingShow()
	}

	override suspend fun getMovieDetails(movieId: String): Result<ShowDetails?> {
		return showSource.getMovieDetails(movieId)
	}

	override suspend fun getMovieCast(movieId: String): Result<CastData?> {
		return showSource.getMovieCast(movieId)
	}

	override suspend fun getSeriesDetails(seriesId: String): Result<ShowDetails?> {
		return showSource.getSeriesDetails(seriesId)
	}

	override suspend fun getSeriesCast(seriesId: String): Result<CastData?> {
		return showSource.getSeriesCast(seriesId)
	}

}