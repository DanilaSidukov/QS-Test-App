package com.diphrogram.qstestapp.ui.screens.main

import cafe.adriel.voyager.core.model.StateScreenModel
import cafe.adriel.voyager.core.model.screenModelScope
import com.diphrogram.qstestapp.domain.repository.ISettingsRepository
import kotlinx.coroutines.launch

class MainViewModel(
	settingsRepository: ISettingsRepository
) : StateScreenModel<MainViewModel.State>(State.Idle) {

	sealed interface State {
		data object Idle : State
		data object LoggedIn : State
		data object NotAuthorized : State
	}

	init {
		screenModelScope.launch {
			val isLogged = settingsRepository.getIsUserExist()
			mutableState.value = if (isLogged) {
				State.LoggedIn
			} else {
				State.NotAuthorized
			}
		}
	}

}