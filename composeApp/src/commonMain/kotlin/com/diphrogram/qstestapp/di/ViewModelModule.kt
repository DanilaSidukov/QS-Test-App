package com.diphrogram.qstestapp.di

import com.diphrogram.qstestapp.ui.screens.authorization.AuthViewModel
import com.diphrogram.qstestapp.ui.screens.details.ShowViewModel
import com.diphrogram.qstestapp.ui.screens.tabs.home.HomeViewModel
import com.diphrogram.qstestapp.ui.screens.main.MainViewModel
import com.diphrogram.qstestapp.ui.screens.tabs.profile.ProfileViewModel
import com.diphrogram.qstestapp.ui.screens.tabs.search.SearchViewModel
import org.koin.core.module.dsl.factoryOf
import org.koin.dsl.module

val viewModelModule = module {
	factoryOf(::HomeViewModel)
	factoryOf(::ShowViewModel)
	factoryOf(::SearchViewModel)
	factoryOf(::MainViewModel)
	factoryOf(::AuthViewModel)
	factoryOf(::ProfileViewModel)
}