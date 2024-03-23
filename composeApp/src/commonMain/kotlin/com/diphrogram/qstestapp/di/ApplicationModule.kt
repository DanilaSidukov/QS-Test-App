package com.diphrogram.qstestapp.di

fun applicationModule() = listOf(
	httpClientModule,
	remoteSourceModule,
	repositoryModule,
	viewModelModule,
	localSourceModule
)