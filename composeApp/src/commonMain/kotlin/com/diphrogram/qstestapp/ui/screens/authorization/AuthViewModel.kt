package com.diphrogram.qstestapp.ui.screens.authorization

import cafe.adriel.voyager.core.model.ScreenModel
import cafe.adriel.voyager.core.model.screenModelScope
import com.diphrogram.qstestapp.domain.repository.ISettingsRepository
import kotlinx.coroutines.launch

class AuthViewModel(
	private val settingsRepository: ISettingsRepository
) : ScreenModel {

	fun logIn(userName: String) {
		screenModelScope.launch {
			settingsRepository.setIsUserExist(true)
			settingsRepository.setUserName(userName)
		}
	}

}