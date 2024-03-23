package com.diphrogram.qstestapp.data.source.remote

import com.diphrogram.qstestapp.common.Constants
import com.diphrogram.qstestapp.domain.entities.search.movies.SearchMovieData
import com.diphrogram.qstestapp.domain.entities.search.series.SearchSeriesData
import com.diphrogram.qstestapp.domain.source.remote.ISearchSource
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.request.parameter
import io.ktor.http.isSuccess

class SearchSource(private val httpClient: HttpClient) : ISearchSource {

	override suspend fun getSearchMovieData(query: String, page: Int): Result<SearchMovieData?> {
		return httpClient.enqueue<SearchMovieData>("${Constants.BASE_URL}search/movie") {
			parameter("query", query)
			parameter("page", page)
		}
	}

	override suspend fun getSearchSeriesData(query: String, page: Int): Result<SearchSeriesData?> {
		return httpClient.enqueue("${Constants.BASE_URL}search/tv") {
			parameter("query", query)
			parameter("page", page)
		}
	}

}