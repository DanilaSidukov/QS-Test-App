package com.diphrogram.qstestapp.ui.screens.details

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBars
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBars
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.koin.getScreenModel
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.Navigator
import coil3.compose.AsyncImage
import com.diphrogram.qstestapp.MR
import com.diphrogram.qstestapp.common.Constants
import com.diphrogram.qstestapp.ui.screens.details.ShowViewModel.State
import com.diphrogram.qstestapp.ui.widgets.CastItems
import com.diphrogram.qstestapp.ui.widgets.Loader
import com.diphrogram.qstestapp.ui.widgets.TempErrorText
import dev.icerock.moko.resources.compose.stringResource
import org.jetbrains.compose.resources.painterResource

data class ShowScreen(
	val id: String,
	val isMovie: Boolean
) : Screen {

	@Composable
	override fun Content() {

		val showViewModel = getScreenModel<ShowViewModel>()
		val state by showViewModel.state.collectAsState()

		val navigator = LocalNavigator.current

		LaunchedEffect(Unit) {
			if (isMovie) {
				showViewModel.getMovieDetails(id)
				showViewModel.getMovieCast(id)
			} else {
				showViewModel.getSeriesDetails(id)
				showViewModel.getSeriesCast(id)
			}
		}

		Box(modifier = Modifier.fillMaxSize()
			.background(MaterialTheme.colorScheme.background)) {
			when (val uiState = state) {
				is State.Loading -> {
					Loader(
						modifier = Modifier
							.align(Alignment.Center)
					)
				}

				is State.Error -> {
					TempErrorText(
						error = uiState.showError,
						modifier = Modifier.align(Alignment.Center)
					)
				}

				is State.ShowInformation -> {
					val show = uiState.showItem
					if (show != null) {
						Scaffold(
							topBar = {
								ShowTopBar(
									title = show.title,
									icon = painterResource("drawable/ic_back.xml"),
									navigator = navigator,
								)
							}
						) { paddingValues ->
							Column(
								modifier = Modifier
									.fillMaxSize()
									.padding(
										top = paddingValues.calculateTopPadding(),
										bottom = with(LocalDensity.current) {
											WindowInsets.navigationBars.getBottom(this)
												.toDp() + 30.dp
										},
									)
									.verticalScroll(rememberScrollState()),
								verticalArrangement = Arrangement.spacedBy(4.dp),
								horizontalAlignment = Alignment.Start,
							) {
								if (show.poster != null) {
									AsyncImage(
										modifier = Modifier
											.fillMaxWidth()
											.fillMaxHeight(0.6f)
											.padding(bottom = 20.dp),
										model = "${Constants.IMAGE_URL}${show.poster}",
										contentDescription = null,
										contentScale = ContentScale.Crop
									)
								} else {
									Image(
										painter = painterResource(Constants.NO_POSTER),
										modifier = Modifier
											.fillMaxWidth()
											.fillMaxHeight(0.6f)
											.padding(bottom = 20.dp),
										contentDescription = null,
										contentScale = ContentScale.Crop
									)
								}
								Column(
									modifier = Modifier
										.padding(horizontal = 24.dp)
								) {
									ShowDataSection(stringResource(MR.strings.title), show.title)
									ShowDataSection(stringResource(MR.strings.release_date), show.releaseDate)
									ShowDataSection(stringResource(MR.strings.runtime), show.duration)
									ShowDataSection(stringResource(MR.strings.genres), show.genres)
									ShowDataSection(stringResource(MR.strings.description), show.description)

									if (uiState.castState.isNotEmpty()) {
										CastItems(
											castList = uiState.castState,
											modifier = Modifier
												.fillMaxWidth()
												.padding(top = 20.dp)
										)
									}
								}
							}
						}
					}
				}
			}
		}
	}
}

@Composable
fun ShowTopBar(title: String, icon: Painter, navigator: Navigator?) {
	Box(
		modifier = Modifier
			.fillMaxWidth()
			.padding(top = with(LocalDensity.current) {
				WindowInsets.statusBars.getTop(this)
					.toDp() + 10.dp
			},
				start = 24.dp, end = 24.dp, bottom = 10.dp)
	) {
		Icon(
			painter = icon,
			contentDescription = null,
			modifier = Modifier
				.align(Alignment.CenterStart)
				.clickable {
					navigator?.pop()
				}
		)
		Text(
			modifier = Modifier
				.fillMaxWidth()
				.height(IntrinsicSize.Max)
				.padding(horizontal = 30.dp),
			text = title,
			fontSize = 22.sp,
			textAlign = TextAlign.Center,
			style = MaterialTheme.typography.bodyMedium
		)
	}
}

@Composable
fun ShowDataSection(
	section: String,
	data: String
) {
	Text(
		text = section,
		fontSize = 18.sp,
		style = MaterialTheme.typography.titleMedium
	)
	Text(
		text = data,
		fontSize = 18.sp
	)
}
