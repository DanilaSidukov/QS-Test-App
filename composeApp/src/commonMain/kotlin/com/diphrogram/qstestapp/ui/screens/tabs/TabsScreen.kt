package com.diphrogram.qstestapp.ui.screens.tabs

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawing
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.tab.CurrentTab
import cafe.adriel.voyager.navigator.tab.TabDisposable
import cafe.adriel.voyager.navigator.tab.TabNavigator
import com.diphrogram.qstestapp.ui.screens.main.BottomNavigationSheetContent
import com.diphrogram.qstestapp.ui.screens.tabs.favorite.BookmarkScreen
import com.diphrogram.qstestapp.ui.screens.tabs.home.HomeScreen
import com.diphrogram.qstestapp.ui.screens.tabs.profile.ProfileScreen
import com.diphrogram.qstestapp.ui.screens.tabs.search.SearchScreen

object TabsScreen : Screen {

	@Composable
	override fun Content() {
		TabNavigator(
			tab = HomeScreen,
			tabDisposable = { navigator ->
				TabDisposable(
					navigator = navigator,
					tabs = listOf(HomeScreen, SearchScreen, ProfileScreen, BookmarkScreen)
				)
			}
		) {
			Scaffold(
				modifier = Modifier.fillMaxSize()
					.windowInsetsPadding(WindowInsets.safeDrawing)
					.background(MaterialTheme.colorScheme.background),
				content = { paddingValues ->
					Box(modifier = Modifier.
							padding(vertical = paddingValues.calculateTopPadding())
					) {
						CurrentTab()
					}
				},
				bottomBar = {
					BottomNavigationSheetContent(
						listOf(HomeScreen, SearchScreen, ProfileScreen, BookmarkScreen)
					)
				}
			)
		}
	}
}