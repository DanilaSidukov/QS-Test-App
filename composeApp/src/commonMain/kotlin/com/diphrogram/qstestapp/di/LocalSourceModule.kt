package com.diphrogram.qstestapp.di

import com.diphrogram.qstestapp.data.source.local.AppSettings
import com.diphrogram.qstestapp.domain.source.local.IAppSettings
import com.russhwolf.settings.Settings
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.bind
import org.koin.dsl.module

val localSourceModule = module {
	singleOf(::Settings) bind Settings::class
	singleOf(::AppSettings) bind IAppSettings::class
}