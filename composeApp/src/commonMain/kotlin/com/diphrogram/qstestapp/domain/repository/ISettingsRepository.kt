package com.diphrogram.qstestapp.domain.repository

interface ISettingsRepository {

	fun setIsUserExist(isUserExist: Boolean)
	fun getIsUserExist() : Boolean
	fun setUserName(userName: String?)
	fun getUserName(): String?

}