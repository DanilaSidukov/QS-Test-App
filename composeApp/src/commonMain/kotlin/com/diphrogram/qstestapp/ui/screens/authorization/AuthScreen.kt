package com.diphrogram.qstestapp.ui.screens.authorization

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.navigationBars
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Snackbar
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.koin.getScreenModel
import cafe.adriel.voyager.navigator.LocalNavigator
import com.diphrogram.qstestapp.MR
import com.diphrogram.qstestapp.common.Constants
import com.diphrogram.qstestapp.ui.screens.main.MainScreen
import com.diphrogram.qstestapp.ui.widgets.ButtonAction
import dev.icerock.moko.resources.compose.stringResource
import kotlinx.coroutines.launch

sealed interface FieldValidation {
	object Success : FieldValidation
	data class Empty(val message: String) : FieldValidation
	data class Incorrect(val message: String) : FieldValidation
}

object AuthScreen : Screen {

	@Composable
	override fun Content() {

		val viewModel = getScreenModel<AuthViewModel>()

		val scope = rememberCoroutineScope()
		val snackbarHostState = remember { SnackbarHostState() }

		val navigator = LocalNavigator.current

		val inputEmail = stringResource(MR.strings.input_your_email)
		val incorrectEmail = stringResource(MR.strings.incorrect_email)
		var textEmail by remember { mutableStateOf("") }

		val inputPassword = stringResource(MR.strings.input_your_password)
		val incorrectPassword = stringResource(MR.strings.incorrect_password)
		var textPassword by remember { mutableStateOf("") }

		fun checkIsEmailValid(email: String): FieldValidation {
			return if (email.trim().isEmpty()) {
				FieldValidation.Empty(inputEmail)
			} else if (email.matches(Constants.EMAIL_REGULAR_EXPRESSION.toRegex()) && email.length <= 100) {
				FieldValidation.Success
			} else {
				FieldValidation.Incorrect(incorrectEmail)
			}
		}

		fun checkIsPasswordValid(password: String): FieldValidation {
			return if (password.trim().isEmpty()) {
				FieldValidation.Empty(inputPassword)
			} else if (password.length >= 8) {
				FieldValidation.Success
			} else {
				FieldValidation.Incorrect(incorrectPassword)
			}
		}

		fun showInputError(isEmail: Boolean, message: String) {
			if (isEmail) {
				scope.launch { snackbarHostState.showSnackbar(message) }
				return
			} else {
				scope.launch { snackbarHostState.showSnackbar(message) }
				return
			}
		}

		Scaffold(
			modifier = Modifier
				.fillMaxSize()
				.background(MaterialTheme.colorScheme.background),
			snackbarHost = {
				SnackbarHost(
					modifier = Modifier
						.fillMaxWidth()
						.padding(start = 24.dp, end = 24.dp, bottom = 80.dp),
					hostState = snackbarHostState
				) { snackbarData ->
					Snackbar(
						snackbarData = snackbarData,
						contentColor = MaterialTheme.colorScheme.secondary,
						containerColor = MaterialTheme.colorScheme.onPrimary,
					)
				}
			}
		) {
			Column(
				modifier = Modifier
					.fillMaxSize()
					.padding(horizontal = 24.dp)
			) {
				Column(
					modifier = Modifier
						.weight(1f),
					horizontalAlignment = Alignment.CenterHorizontally,
					verticalArrangement = Arrangement.Center
				) {
					Text(
						modifier = Modifier
							.fillMaxWidth(),
						text = stringResource(MR.strings.login_greetings),
						style = MaterialTheme.typography.displayMedium,
						fontSize = 18.sp,
						textAlign = TextAlign.Center,
						color = MaterialTheme.colorScheme.secondary
					)
					TextField(
						modifier = Modifier
							.fillMaxWidth()
							.padding(start = 16.dp, end = 16.dp, top = 56.dp, bottom = 16.dp)
							.background(MaterialTheme.colorScheme.onPrimary),
						value = textEmail,
						textStyle = MaterialTheme.typography.displayMedium,
						onValueChange = { newValue ->
							textEmail = newValue
						},
						singleLine = true,
						keyboardOptions = KeyboardOptions(
							keyboardType = KeyboardType.Email
						),
						placeholder = {
							Text(
								text = stringResource(MR.strings.hint_email),
								color = MaterialTheme.colorScheme.secondary,
								modifier = Modifier
									.fillMaxWidth(),
								style = MaterialTheme.typography.displayMedium
							)
						}
					)
					TextField(
						modifier = Modifier
							.fillMaxWidth()
							.padding(horizontal = 16.dp, vertical = 16.dp),
						visualTransformation = PasswordVisualTransformation(),
						keyboardOptions = KeyboardOptions(
							keyboardType = KeyboardType.Password,
						),
						value = textPassword,
						textStyle = MaterialTheme.typography.displayMedium,
						onValueChange = { newValue ->
							textPassword = newValue
						},
						singleLine = true,
						placeholder = {
							Text(
								text = stringResource(MR.strings.hint_password),
								color = MaterialTheme.colorScheme.secondary,
								modifier = Modifier
									.fillMaxWidth(),
								style = MaterialTheme.typography.displayMedium
							)
						}
					)
				}
				Column {
					ButtonAction(
						modifier = Modifier
							.padding(bottom = WindowInsets.navigationBars.asPaddingValues().calculateBottomPadding() + 30.dp),
						tittle = stringResource(MR.strings.button_login),
						buttonColor = MaterialTheme.colorScheme.secondary,
						elementsColor = MaterialTheme.colorScheme.onPrimary,
						action = {
							val emailValid = checkIsEmailValid(textEmail)
							val passwordValid = checkIsPasswordValid(textPassword)

							val isEmailValid = when (emailValid) {
								is FieldValidation.Empty -> {
									showInputError(true, emailValid.message)
									false
								}

								is FieldValidation.Incorrect -> {
									showInputError(true, emailValid.message)
									false
								}

								FieldValidation.Success -> true
							}

							val isPasswordValid = when (passwordValid) {
								is FieldValidation.Empty -> {
									if (isEmailValid) {
										showInputError(false, passwordValid.message)
									}
									false
								}

								is FieldValidation.Incorrect -> {
									if (isEmailValid) {
										showInputError(false, passwordValid.message)
									}
									false
								}

								FieldValidation.Success -> true
							}

							if (isEmailValid && isPasswordValid) {
								viewModel.logIn(textEmail)
								navigator?.replace(MainScreen)
							}
						}
					)
				}
			}
		}
	}
}

