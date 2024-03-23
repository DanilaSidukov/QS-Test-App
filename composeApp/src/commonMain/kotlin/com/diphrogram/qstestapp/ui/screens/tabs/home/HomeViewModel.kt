package com.diphrogram.qstestapp.ui.screens.tabs.home

import cafe.adriel.voyager.core.model.StateScreenModel
import cafe.adriel.voyager.core.model.screenModelScope
import com.diphrogram.qstestapp.domain.entities.shows.Show
import com.diphrogram.qstestapp.domain.repository.IShowRepository
import kotlinx.coroutines.launch

class HomeViewModel(
	private val showRepository: IShowRepository
) : StateScreenModel<HomeViewModel.State>(State.Loading) {

	sealed class State {
		data object Loading : State()
		data class Error(val error: String) : State()
		data class ShowList(val showList: List<Show>) : State()
	}

	init {
		screenModelScope.launch {
			showRepository.getTrendingShow()
				.onSuccess { showPages ->
					showPages?.results?.let { list ->
						mutableState.value = State.ShowList(list)
					}
				}
				.onFailure { error ->
					mutableState.value = State.Error(error.message.toString())
				}
		}
	}
}