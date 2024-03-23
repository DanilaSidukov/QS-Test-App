package com.diphrogram.qstestapp.data.repository

import com.diphrogram.qstestapp.domain.entities.search.movies.SearchMovieData
import com.diphrogram.qstestapp.domain.entities.search.series.SearchSeriesData
import com.diphrogram.qstestapp.domain.repository.ISearchRepository
import com.diphrogram.qstestapp.domain.source.remote.ISearchSource

class SearchRepository(private val searchSource: ISearchSource): ISearchRepository {

	override suspend fun getSearchMovieData(query: String, page: Int): Result<SearchMovieData?> {
		return searchSource.getSearchMovieData(query, page)
	}

	override suspend fun getSearchSeriesData(query: String, page: Int): Result<SearchSeriesData?> {
		return searchSource.getSearchSeriesData(query, page)
	}
}

