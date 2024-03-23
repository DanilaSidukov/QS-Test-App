package com.diphrogram.qstestapp.domain.repository

import com.diphrogram.qstestapp.domain.entities.search.movies.SearchMovieData
import com.diphrogram.qstestapp.domain.entities.search.series.SearchSeriesData

interface ISearchRepository {

	suspend fun getSearchMovieData(query: String, page: Int): Result<SearchMovieData?>

	suspend fun getSearchSeriesData(query: String, page: Int): Result<SearchSeriesData?>
}