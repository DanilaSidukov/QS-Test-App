package com.diphrogram.qstestapp.ui.widgets

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp


@Composable
fun ButtonAction(
    modifier: Modifier,
    tittle: String,
    buttonColor: Color,
    elementsColor: Color,
    action: () -> Unit,
) {
    Button(
        modifier = modifier,
        shape = RoundedCornerShape(16.dp),
        colors = ButtonDefaults.buttonColors(
            containerColor = buttonColor,
            disabledContentColor = buttonColor
        ),
        onClick = action,
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 5.dp)
        ) {
            Text(
                modifier = Modifier
                    .align(Alignment.Center)
                    .padding(vertical = 2.dp),
                text = tittle,
                color = elementsColor,
                style = MaterialTheme.typography.displayMedium,
            )
        }
    }
}