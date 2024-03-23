package com.diphrogram.qstestapp.ui.screens.tabs.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import cafe.adriel.voyager.koin.getScreenModel
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.tab.Tab
import cafe.adriel.voyager.navigator.tab.TabOptions
import com.diphrogram.qstestapp.MR
import com.diphrogram.qstestapp.ui.screens.details.ShowScreen
import com.diphrogram.qstestapp.ui.widgets.Loader
import com.diphrogram.qstestapp.ui.widgets.ShowList
import com.diphrogram.qstestapp.ui.widgets.TempErrorText
import dev.icerock.moko.resources.compose.stringResource
import org.jetbrains.compose.resources.painterResource

object HomeScreen : Tab {

	override val options: TabOptions
		@Composable
		get() {
			val title = stringResource(MR.strings.home_key)
			val icon = painterResource("drawable/ic_home.xml")

			return remember {
				TabOptions(
					index = 0u,
					title = title,
					icon = icon
				)
			}
		}

	@Composable
	override fun Content() {

		val homeViewModel = getScreenModel<HomeViewModel>()
		val state by homeViewModel.state.collectAsState()

		val navigator = LocalNavigator.current?.parent

		Box(modifier = Modifier.fillMaxSize()
			.background(MaterialTheme.colorScheme.background)
		) {
			when(val uiState = state){
				is HomeViewModel.State.Loading -> {
					Loader(
						modifier = Modifier
							.align(Alignment.Center)
					)
				}
				is HomeViewModel.State.Error -> {
					TempErrorText(
						error = uiState.error,
						modifier = Modifier.align(Alignment.Center)
					)
				}
				is HomeViewModel.State.ShowList -> {
					Column(
						modifier = Modifier
							.fillMaxSize()
							.padding(horizontal = 24.dp, vertical = 12.dp)
							.background(MaterialTheme.colorScheme.background),
						verticalArrangement = Arrangement.Center,
						horizontalAlignment = Alignment.CenterHorizontally
					) {
						Text(
							text = stringResource(MR.strings.popular_shows),
							modifier = Modifier
								.fillMaxWidth()
								.padding(bottom = 12.dp),
							fontSize = 20.sp
						)
						ShowList(
							showList = uiState.showList,
							onItemClicked = { id, isMovie ->
								navigator?.push(
									ShowScreen(
										id = id,
										isMovie = isMovie
									)
								)
							}
						)
					}
				}
			}
		}

	}
}