package com.diphrogram.qstestapp.data.source.local

import com.diphrogram.qstestapp.domain.source.local.IAppSettings
import com.russhwolf.settings.Settings
import com.russhwolf.settings.set

class AppSettings(private val settings: Settings) : IAppSettings {

	private companion object {
		const val USER_EXIST = "user_exist"
		const val USER_NAME = "user_name"
	}

	override fun setIsUserExist(isUserExist: Boolean) {
		settings[USER_EXIST] = isUserExist
	}

	override fun getIsUserExist() = settings.getBoolean(USER_EXIST, false)

	override fun setUserName(userName: String?) {
		settings[USER_NAME] = userName
	}

	override fun getUserName() = settings.getStringOrNull(USER_NAME)

}
