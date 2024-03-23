package com.diphrogram.qstestapp.domain.source.local

interface IAppSettings {

	fun setIsUserExist(isUserExist: Boolean)
	fun getIsUserExist(): Boolean
	fun setUserName(userName: String?)
	fun getUserName(): String?

}