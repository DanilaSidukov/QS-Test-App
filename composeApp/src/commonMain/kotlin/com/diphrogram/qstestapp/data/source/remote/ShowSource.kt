package com.diphrogram.qstestapp.data.source.remote

import com.diphrogram.qstestapp.common.Constants
import com.diphrogram.qstestapp.domain.entities.details.ShowDetails
import com.diphrogram.qstestapp.domain.entities.details.cast.Cast
import com.diphrogram.qstestapp.domain.entities.details.cast.CastData
import com.diphrogram.qstestapp.domain.entities.details.movie.Movie
import com.diphrogram.qstestapp.domain.entities.details.series.Series
import com.diphrogram.qstestapp.domain.entities.shows.Show
import com.diphrogram.qstestapp.domain.entities.shows.ShowPages
import com.diphrogram.qstestapp.domain.source.remote.IShowSource
import com.diphrogram.qstestapp.domain.toShowDetails
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.statement.HttpResponse
import io.ktor.http.isSuccess

class ShowSource(private val httpClient: HttpClient) : IShowSource {

	override suspend fun getTrendingShow(): Result<ShowPages?> {
		return httpClient.enqueue<ShowPages>("${Constants.BASE_URL}trending/all/week")
	}

	override suspend fun getMovieDetails(movieId: String): Result<ShowDetails?> {
		return httpClient
			.enqueue<Movie?>("${Constants.BASE_URL}movie/${movieId}")
			.map { movie ->
				movie?.toShowDetails()
			}
	}

	override suspend fun getMovieCast(movieId: String): Result<CastData?> {
		return httpClient.enqueue("${Constants.BASE_URL}movie/${movieId}/credits")
	}

	override suspend fun getSeriesDetails(seriesId: String): Result<ShowDetails?> {
		return httpClient
			.enqueue<Series?>("${Constants.BASE_URL}tv/$seriesId")
			.map { series ->
				series?.toShowDetails()
			}
	}

	override suspend fun getSeriesCast(seriesId: String): Result<CastData?> {
		return httpClient.enqueue("${Constants.BASE_URL}tv/$seriesId/credits")
	}

}