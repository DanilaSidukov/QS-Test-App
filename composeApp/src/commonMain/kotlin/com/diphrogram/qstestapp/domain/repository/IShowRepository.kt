package com.diphrogram.qstestapp.domain.repository

import com.diphrogram.qstestapp.domain.entities.details.ShowDetails
import com.diphrogram.qstestapp.domain.entities.details.cast.Cast
import com.diphrogram.qstestapp.domain.entities.details.cast.CastData
import com.diphrogram.qstestapp.domain.entities.details.movie.Movie
import com.diphrogram.qstestapp.domain.entities.details.series.Series
import com.diphrogram.qstestapp.domain.entities.shows.Show
import com.diphrogram.qstestapp.domain.entities.shows.ShowPages

interface IShowRepository {

	suspend fun getTrendingShow(): Result<ShowPages?>
	suspend fun getMovieDetails(movieId: String): Result<ShowDetails?>
	suspend fun getMovieCast(movieId: String): Result<CastData?>
	suspend fun getSeriesDetails(seriesId: String): Result<ShowDetails?>
	suspend fun getSeriesCast(seriesId: String): Result<CastData?>

}