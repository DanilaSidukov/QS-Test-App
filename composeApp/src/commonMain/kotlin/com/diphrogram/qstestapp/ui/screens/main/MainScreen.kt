package com.diphrogram.qstestapp.ui.screens.main

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawing
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.koin.getScreenModel
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.Navigator
import cafe.adriel.voyager.navigator.tab.CurrentTab
import cafe.adriel.voyager.navigator.tab.LocalTabNavigator
import cafe.adriel.voyager.navigator.tab.Tab
import cafe.adriel.voyager.navigator.tab.TabDisposable
import cafe.adriel.voyager.navigator.tab.TabNavigator
import com.diphrogram.qstestapp.ui.screens.authorization.AuthScreen
import com.diphrogram.qstestapp.ui.screens.tabs.TabsScreen
import com.diphrogram.qstestapp.ui.screens.tabs.home.HomeScreen
import com.diphrogram.qstestapp.ui.screens.tabs.profile.ProfileScreen
import com.diphrogram.qstestapp.ui.screens.tabs.search.SearchScreen

object MainScreen : Screen {

	@Composable
	override fun Content() {

		val viewModel = getScreenModel<MainViewModel>()
		val uiState by viewModel.state.collectAsState()
		val navigator = LocalNavigator.current

		when (uiState) {
			MainViewModel.State.Idle -> {
				// nothing
			}

			is MainViewModel.State.LoggedIn -> {
				navigator?.replace(TabsScreen)
			}

			is MainViewModel.State.NotAuthorized -> {
				Scaffold(
					modifier = Modifier.fillMaxSize()
						.windowInsetsPadding(WindowInsets.safeDrawing)
						.background(MaterialTheme.colorScheme.background),
					content = {
						navigator?.replace(AuthScreen)
					},
				)
			}
		}
	}
}

@Composable
fun BottomNavigationSheetContent(tabs: List<Tab>) {
	Row(
		modifier = Modifier
			.fillMaxWidth()
			.navigationBarsPadding()
			.background(MaterialTheme.colorScheme.onPrimary)
			.padding(16.dp),
		horizontalArrangement = Arrangement.SpaceAround
	) {
		tabs.forEach { tab ->
			BottomSheetItem(tab)
		}
	}
}

@Composable
fun BottomSheetItem(tab: Tab) {
	val tabNavigator = LocalTabNavigator.current
	Column(
		horizontalAlignment = Alignment.CenterHorizontally,
		modifier = Modifier.clickable(
			interactionSource = remember { MutableInteractionSource() },
			indication = rememberRipple(
				bounded = false,
				radius = 40.dp
			)
		) {
			tabNavigator.current = tab
		},
	) {
		tab.options.icon?.let { icon ->
			Icon(
				modifier = Modifier.size(25.dp),
				painter = icon,
				contentDescription = null,
				tint = if (tab == tabNavigator.current) {
					MaterialTheme.colorScheme.primary
				} else {
					MaterialTheme.colorScheme.onBackground
				}
			)
		}
		Text(
			modifier = Modifier.padding(top = 5.dp),
			text = tab.options.title,
			style = MaterialTheme.typography.bodySmall,
			color = if (tab == tabNavigator.current) {
				MaterialTheme.colorScheme.primary
			} else {
				MaterialTheme.colorScheme.onBackground
			}
		)
	}
}