package com.diphrogram.qstestapp.ui.screens.tabs.search

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBars
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import cafe.adriel.voyager.core.lifecycle.JavaSerializable
import cafe.adriel.voyager.koin.getScreenModel
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.tab.Tab
import cafe.adriel.voyager.navigator.tab.TabOptions
import com.diphrogram.qstestapp.MR
import com.diphrogram.qstestapp.ui.screens.details.ShowScreen
import com.diphrogram.qstestapp.ui.widgets.Loader
import com.diphrogram.qstestapp.ui.widgets.SearchList
import com.diphrogram.qstestapp.ui.widgets.TempErrorText
import dev.icerock.moko.resources.compose.stringResource
import org.jetbrains.compose.resources.painterResource

data class RestoreSearchData(
	var searchData: SearchViewModel.State.SearchData? = null,
	var currentPage: Int? = null,
	var inputValue: String? = null
)

object SearchScreen : Tab, JavaSerializable {

	private var data: RestoreSearchData? = null

	override val options: TabOptions
		@Composable
		get() {
			val title = stringResource(MR.strings.search_key)
			val icon = painterResource("drawable/ic_search.xml")

			return remember {
				TabOptions(
					index = 1u,
					title = title,
					icon = icon
				)
			}
		}

	@Composable
	override fun Content() {
		val navigator = LocalNavigator.current?.parent

		val icon = painterResource("drawable/ic_search.xml")
		val iconNavigatePage = painterResource("drawable/ic_navigation.xml")

		var searchText by remember { mutableStateOf<String?>(null) }
		var previousText by remember { mutableStateOf<String>("") }
		var inputText by remember { mutableStateOf("") }
		var currentPage by remember { mutableStateOf(1) }
		var totalPage by remember { mutableStateOf(1) }

		val searchViewModel = getScreenModel<SearchViewModel>()
		val state by searchViewModel.state.collectAsState()

		LaunchedEffect(Unit) {
			if (data != null) {
				data?.currentPage?.let { page ->
					currentPage = page
				}
				data?.inputValue?.let { input ->
					searchText = input
					inputText = input
				}
				searchViewModel.setStateSearchData(data?.searchData)
			}
		}

		Scaffold(
			topBar = {
				TextField(
					modifier = Modifier
						.fillMaxWidth()
						.background(MaterialTheme.colorScheme.background)
						.padding(start = 24.dp, end = 24.dp, top = 12.dp),
					value = inputText,
					onValueChange = { newValue ->
						inputText = newValue
					},
					placeholder = {
						Text(
							text = stringResource(MR.strings.search_hint)
						)
					},
					trailingIcon = {
						Icon(
							painter = icon,
							contentDescription = null,
							modifier = Modifier
								.size(24.dp)
								.clickable {
									searchText = inputText
									if (previousText != searchText && inputText.isNotBlank()) {
										searchViewModel.getSearchMovie(inputText, 1)
										searchViewModel.getSearchSeries(inputText, 1)
									}
								}
						)
					}
				)
			},
		) { paddingValues ->
			Box(modifier = Modifier.fillMaxSize()) {
				when (val uiState = state) {
					is SearchViewModel.State.Error -> {
						TempErrorText(
							error = uiState.showError,
							modifier = Modifier.align(Alignment.Center)
						)
					}

					SearchViewModel.State.Loading -> {
						Loader(
							modifier = Modifier
								.align(Alignment.Center)
						)
					}

					SearchViewModel.State.Idle -> {
						Column(
							modifier = Modifier.fillMaxSize(),
							verticalArrangement = Arrangement.Center,
							horizontalAlignment = Alignment.CenterHorizontally
						) {
							Text(
								text = stringResource(MR.strings.search_description),
								textAlign = TextAlign.Center
							)
						}
					}

					is SearchViewModel.State.SearchData -> {
						searchText?.let { text ->
							previousText = text
						}
						val movieList = uiState.moveList?.results
						val seriesList = uiState.seriesList?.results
						data = RestoreSearchData(
							searchData = SearchViewModel.State.SearchData(
								uiState.moveList,
								uiState.seriesList
							),
							inputValue = searchText,
							currentPage = currentPage
						)
						totalPage = getTotalPage(
							uiState.moveList?.totalPages,
							uiState.seriesList?.totalPages
						)
						Box(
							modifier = Modifier.fillMaxSize()
								.padding(
									top = paddingValues.calculateTopPadding()
								)
								.background(MaterialTheme.colorScheme.background)
						) {
							Column(
								modifier = Modifier
									.fillMaxSize()
									.padding(horizontal = 24.dp),
								horizontalAlignment = Alignment.CenterHorizontally,
							) {
								SearchList(
									movieList = movieList,
									seriesList = seriesList,
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
							Row(
								modifier = Modifier
									.fillMaxWidth()
									.height(120.dp)
									.align(Alignment.BottomCenter)
									.background(MaterialTheme.colorScheme.onPrimary)
									.padding(
										bottom = with(LocalDensity.current) {
											WindowInsets.navigationBars.getBottom(this).toDp()
										}),
								verticalAlignment = Alignment.CenterVertically,
								horizontalArrangement = Arrangement.SpaceAround
							) {
								val backCondition = currentPage > 1 && searchText != null
								val nextCondition = currentPage != totalPage && searchText != null
								Icon(
									painter = iconNavigatePage,
									modifier = Modifier
										.alpha(alpha = if (backCondition) 1f else 0.5f)
										.clickable(
											enabled = backCondition
										) {
											if (backCondition) {
												searchViewModel.getSearchMovie(
													searchText!!,
													currentPage - 1
												)
												searchViewModel.getSearchSeries(
													searchText!!,
													currentPage - 1
												)
												currentPage -= 1
											}
										},
									contentDescription = null
								)
								Text(
									text = "${stringResource(MR.strings.page)}$currentPage",
									fontSize = 20.sp,
									style = MaterialTheme.typography.bodyMedium
								)
								Icon(
									painter = iconNavigatePage,
									modifier = Modifier
										.rotate(180f)
										.alpha(alpha = if (nextCondition) 1f else 0.5f)
										.clickable(
											enabled = nextCondition
										) {
											if (nextCondition) {
												searchViewModel.getSearchMovie(
													searchText!!,
													currentPage + 1
												)
												searchViewModel.getSearchSeries(
													searchText!!,
													currentPage + 1
												)
												currentPage += 1
											}
										},
									contentDescription = null
								)
							}
						}
					}
				}
			}
		}
	}
}

fun getTotalPage(pagesOne: Int?, pagesTwo: Int?): Int {
	return if (pagesOne != null && pagesTwo != null) {
		maxOf(pagesOne, pagesTwo)
	} else if (pagesOne != null && pagesTwo == null) {
		pagesOne
	} else pagesTwo!!
}