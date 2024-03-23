package com.diphrogram.qstestapp.data.repository

import com.diphrogram.qstestapp.domain.repository.ISettingsRepository
import com.diphrogram.qstestapp.domain.source.local.IAppSettings

class SettingsRepository(
	private val settings: IAppSettings
) : ISettingsRepository {

	override fun setIsUserExist(isUserExist: Boolean) = settings.setIsUserExist(isUserExist)

	override fun getIsUserExist() = settings.getIsUserExist()

	override fun setUserName(userName: String?) = settings.setUserName(userName)

	override fun getUserName() = settings.getUserName()

}