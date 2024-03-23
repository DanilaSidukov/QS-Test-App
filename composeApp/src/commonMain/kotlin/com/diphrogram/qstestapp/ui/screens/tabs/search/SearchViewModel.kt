package com.diphrogram.qstestapp.ui.screens.tabs.search

import cafe.adriel.voyager.core.model.StateScreenModel
import cafe.adriel.voyager.core.model.screenModelScope
import com.diphrogram.qstestapp.domain.entities.search.movies.SearchMovieData
import com.diphrogram.qstestapp.domain.entities.search.series.SearchSeriesData
import com.diphrogram.qstestapp.domain.repository.ISearchRepository
import kotlinx.coroutines.launch

class SearchViewModel(
	private val searchRepository: ISearchRepository
) : StateScreenModel<SearchViewModel.State>(State.Idle) {

	sealed class State {
		data object Idle : State()
		data object Loading : State()
		data class Error(val showError: String) : State()
		data class SearchData(
			val moveList: SearchMovieData? = null,
			val seriesList: SearchSeriesData? = null
		) : State()
	}

	fun getSearchMovie(query: String, page: Int) {
		screenModelScope.launch {
			mutableState.value = State.Loading
			searchRepository.getSearchMovieData(query, page)
				.onSuccess { searchMovieData ->
					if (searchMovieData?.results != null) {
						mutableState.value = when (val state = mutableState.value) {
							is State.SearchData -> {
								state.copy(moveList = searchMovieData)
							}

							else -> {
								State.SearchData(moveList = searchMovieData)
							}
						}
					}
				}
				.onFailure { error ->
					mutableState.value = State.Error(error.message.toString())
				}
		}
	}

	fun getSearchSeries(query: String, page: Int) {
		screenModelScope.launch {
			searchRepository.getSearchSeriesData(query, page)
				.onSuccess { searchSeriesData ->
					if (searchSeriesData?.results != null) {
						mutableState.value = when (val state = mutableState.value) {
							is State.SearchData -> {
								state.copy(seriesList = searchSeriesData)
							}

							else -> {
								State.SearchData(seriesList = searchSeriesData)
							}
						}
					}
				}
				.onFailure { error ->
					mutableState.value = State.Error(error.message.toString())
				}
		}
	}

	fun setStateSearchData(data: State.SearchData?) {
		mutableState.value = State.SearchData(data?.moveList, data?.seriesList)
	}

}