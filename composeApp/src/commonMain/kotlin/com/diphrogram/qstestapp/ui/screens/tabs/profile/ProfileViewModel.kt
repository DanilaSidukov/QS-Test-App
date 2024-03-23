package com.diphrogram.qstestapp.ui.screens.tabs.profile

import cafe.adriel.voyager.core.model.StateScreenModel
import cafe.adriel.voyager.core.model.screenModelScope
import com.diphrogram.qstestapp.domain.repository.ISettingsRepository
import kotlinx.coroutines.launch

class ProfileViewModel(
	private val settingsRepository: ISettingsRepository
) : StateScreenModel<ProfileViewModel.State>(State()) {

	data class State(
		val userName: String? = null
	)

	init {
		screenModelScope.launch {
			val userName = settingsRepository.getUserName()
			mutableState.value = State(userName)
		}
	}

	fun logOut() {
		settingsRepository.setIsUserExist(false)
		settingsRepository.setUserName(null)
	}

}