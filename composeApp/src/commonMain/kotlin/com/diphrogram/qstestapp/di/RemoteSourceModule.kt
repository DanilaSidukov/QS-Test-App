package com.diphrogram.qstestapp.di

import com.diphrogram.qstestapp.data.source.remote.SearchSource
import com.diphrogram.qstestapp.data.source.remote.ShowSource
import com.diphrogram.qstestapp.domain.source.remote.ISearchSource
import com.diphrogram.qstestapp.domain.source.remote.IShowSource
import org.koin.core.module.dsl.factoryOf
import org.koin.dsl.bind
import org.koin.dsl.module

val remoteSourceModule = module {
	factoryOf(::ShowSource) bind IShowSource::class
	factoryOf(::SearchSource) bind ISearchSource::class
}