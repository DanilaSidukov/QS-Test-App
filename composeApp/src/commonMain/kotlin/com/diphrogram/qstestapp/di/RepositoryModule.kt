package com.diphrogram.qstestapp.di

import com.diphrogram.qstestapp.data.repository.SearchRepository
import com.diphrogram.qstestapp.data.repository.SettingsRepository
import com.diphrogram.qstestapp.data.repository.ShowRepository
import com.diphrogram.qstestapp.domain.repository.ISearchRepository
import com.diphrogram.qstestapp.domain.repository.ISettingsRepository
import com.diphrogram.qstestapp.domain.repository.IShowRepository
import org.koin.core.module.dsl.factoryOf
import org.koin.dsl.bind
import org.koin.dsl.module

val repositoryModule = module {
	factoryOf(::ShowRepository) bind IShowRepository::class
	factoryOf(::SearchRepository) bind ISearchRepository::class
	factoryOf(::SettingsRepository) bind ISettingsRepository::class
}