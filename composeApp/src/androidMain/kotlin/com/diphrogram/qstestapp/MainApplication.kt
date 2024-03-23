package com.diphrogram.qstestapp

import android.app.Application
import com.diphrogram.qstestapp.di.initKoin

class MainApplication : Application() {

	override fun onCreate() {
		super.onCreate()
		initKoin()
	}

}