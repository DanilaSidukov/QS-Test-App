package com.diphrogram.qstestapp

import androidx.compose.runtime.Composable
import cafe.adriel.voyager.navigator.Navigator
import com.diphrogram.qstestapp.theme.AppTheme
import com.diphrogram.qstestapp.ui.screens.main.MainScreen

@Composable
fun App() {
	AppTheme {
		Navigator(MainScreen)
	}
}
