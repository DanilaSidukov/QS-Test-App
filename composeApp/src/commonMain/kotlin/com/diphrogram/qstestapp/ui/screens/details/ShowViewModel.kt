package com.diphrogram.qstestapp.ui.screens.details

import cafe.adriel.voyager.core.model.StateScreenModel
import cafe.adriel.voyager.core.model.screenModelScope
import com.diphrogram.qstestapp.domain.entities.details.ShowDetails
import com.diphrogram.qstestapp.domain.entities.details.cast.Cast
import com.diphrogram.qstestapp.domain.repository.IShowRepository
import kotlinx.coroutines.launch

class ShowViewModel(
	private val showRepository: IShowRepository
) : StateScreenModel<ShowViewModel.State>(State.Loading) {

	sealed class State {
		data object Loading : State()
		data class Error(val showError: String) : State()
		data class ShowInformation(
			val showItem: ShowDetails? = null,
			val castState: List<Cast> = emptyList()
		) : State()
	}

	fun getMovieDetails(movieId: String) {
		screenModelScope.launch {
			showRepository.getMovieDetails(movieId)
				.onSuccess { showDetails ->
					if (showDetails != null) {
						mutableState.value = when (val state = mutableState.value) {
							is State.ShowInformation -> {
								state.copy(showItem = showDetails)
							}

							else -> {
								State.ShowInformation(showItem = showDetails)
							}
						}
					}
				}
				.onFailure { error ->
					mutableState.value = State.Error(error.message.toString())
				}
		}
	}

	fun getMovieCast(movieId: String) {
		screenModelScope.launch {
			showRepository.getMovieCast(movieId)
				.onSuccess { castData ->
					if (castData != null) {
						mutableState.value = when (val state = mutableState.value) {
							is State.ShowInformation -> {
								state.copy(castState = castData.cast)
							}

							is State.Loading -> {
								State.ShowInformation(castState = castData.cast)
							}

							else -> {
								mutableState.value
							}
						}
					}
				}
		}
	}

	fun getSeriesDetails(seriesId: String) {
		screenModelScope.launch {
			showRepository.getSeriesDetails(seriesId)
				.onSuccess { showDetails ->
					showDetails?.let {
						mutableState.value = State.ShowInformation(showDetails)
					}
				}
				.onFailure { error ->
					mutableState.value = State.Error(error.message.toString())
				}
		}
	}

	fun getSeriesCast(seriesId: String) {
		screenModelScope.launch {
			showRepository.getSeriesCast(seriesId)
				.onSuccess { castData ->
					if (castData != null) {
						mutableState.value = when (val state = mutableState.value) {
							is State.ShowInformation -> {
								state.copy(castState = castData.cast)
							}

							is State.Loading -> {
								State.ShowInformation(castState = castData.cast)
							}

							else -> {
								mutableState.value
							}
						}
					}
				}
		}
	}
}