package com.diphrogram.qstestapp.ui.screens.tabs.profile

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
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
import androidx.compose.ui.unit.sp
import cafe.adriel.voyager.koin.getScreenModel
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.tab.Tab
import cafe.adriel.voyager.navigator.tab.TabOptions
import com.diphrogram.qstestapp.MR
import com.diphrogram.qstestapp.common.JavaSerializable
import com.diphrogram.qstestapp.ui.screens.authorization.AuthScreen
import dev.icerock.moko.resources.compose.stringResource
import org.jetbrains.compose.resources.painterResource

object ProfileScreen : Tab, JavaSerializable {

	override val options: TabOptions
		@Composable
		get() {
			val title = stringResource(MR.strings.profile_key)
			val icon = painterResource("drawable/ic_profile.xml")

			return remember {
				TabOptions(
					index = 2u,
					title = title,
					icon = icon
				)
			}
		}

	@OptIn(ExperimentalMaterial3Api::class)
	@Composable
	override fun Content() {

		val viewModel = getScreenModel<ProfileViewModel>()
		val uiState by viewModel.state.collectAsState()

		val navigator = LocalNavigator.current?.parent

		Scaffold(
			modifier = Modifier
				.fillMaxSize()
				.background(MaterialTheme.colorScheme.background),
			topBar = {
				CenterAlignedTopAppBar(
					title = {
						Text(
							text = stringResource(MR.strings.profile_key),
							fontSize = 22.sp,
							style = MaterialTheme.typography.bodyMedium
						)
					},
					actions = {
						Row(
							modifier = Modifier.fillMaxWidth()
								.padding(horizontal = 24.dp),
							verticalAlignment = Alignment.CenterVertically,
							horizontalArrangement = Arrangement.End
						) {
							Icon(
								painter = painterResource("drawable/ic_exit.xml"),
								contentDescription = null,
								modifier = Modifier.clickable {
									viewModel.logOut()
									navigator?.replace(AuthScreen)
								}
							)
						}
					}
				)
			}
		) { paddingValues ->
			Column(
				modifier = Modifier.fillMaxSize()
					.padding(
						start = 24.dp,
						end = 24.dp,
						top = paddingValues.calculateTopPadding() + 12.dp,
						bottom = 12.dp
					),
				horizontalAlignment = Alignment.CenterHorizontally,
				verticalArrangement = Arrangement.spacedBy(12.dp)
			) {
				Text(
					text = stringResource(MR.strings.your_email),
					fontSize = 22.sp,
					style = MaterialTheme.typography.bodyMedium
				)
				Text(
					text = uiState.userName.toString(),
					fontSize = 22.sp,
					style = MaterialTheme.typography.displayMedium
				)
			}
		}
	}
}