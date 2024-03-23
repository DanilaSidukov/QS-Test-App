package com.diphrogram.qstestapp.ui.widgets

import androidx.compose.foundation.layout.size
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun Loader(
	modifier: Modifier
) {
	CircularProgressIndicator(
		color = MaterialTheme.colorScheme.onBackground,
		modifier = modifier.size(60.dp),
		strokeWidth = 6.dp
	)
}