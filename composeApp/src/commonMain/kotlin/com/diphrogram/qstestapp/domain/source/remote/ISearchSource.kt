package com.diphrogram.qstestapp.domain.source.remote

import com.diphrogram.qstestapp.domain.entities.search.movies.SearchMovieData
import com.diphrogram.qstestapp.domain.entities.search.series.SearchSeriesData

interface ISearchSource {

	suspend fun getSearchMovieData(query: String, page: Int): Result<SearchMovieData?>

	suspend fun getSearchSeriesData(query: String, page: Int): Result<SearchSeriesData?>

}