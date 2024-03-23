package com.diphrogram.qstestapp.di

import org.koin.core.context.startKoin

fun initKoin() {
	startKoin {
		modules(applicationModule())
	}
}
