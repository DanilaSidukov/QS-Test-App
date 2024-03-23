package com.diphrogram.qstestapp.ui.widgets

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBars
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil3.compose.AsyncImage
import com.diphrogram.qstestapp.domain.entities.shows.Show

@Composable
fun ShowList(
	showList: List<Show>,
	onItemClicked: (String, Boolean) -> Unit
) {
	LazyVerticalGrid(
		columns = GridCells.Fixed(3),
		horizontalArrangement = Arrangement.spacedBy(12.dp),
		verticalArrangement = Arrangement.spacedBy(12.dp),
		contentPadding = PaddingValues(bottom = with(LocalDensity.current) {
			WindowInsets.navigationBars.getBottom(this).toDp() + 30.dp
		})
	) {
		items(
			count = showList.size,
			key = { it }
		) { index ->
			ShowItem(
				showList[index],
				onItemClicked
			)
		}
	}
}

@Composable
fun ShowItem(
	show: Show,
	onItemClicked: (String, Boolean) -> Unit
) {
	Column(
		modifier = Modifier
			.width(60.dp)
			.clickable {
				onItemClicked(show.id.toString(), show.mediaType == "movie")
			},
		horizontalAlignment = Alignment.CenterHorizontally,
		verticalArrangement = Arrangement.spacedBy(4.dp)
	) {
		AsyncImage(
			model = show.poster,
			modifier = Modifier
				.fillMaxWidth()
				.height(140.dp)
				.clip(MaterialTheme.shapes.large),
			contentDescription = null,
			contentScale = ContentScale.Fit
		)
		Text(
			modifier = Modifier
				.fillMaxWidth(),
			text = show.title.ifEmpty { show.name }.ifEmpty { show.originalTitle },
			fontSize = 14.sp,
			textAlign = TextAlign.Center,
			maxLines = 2,
			overflow = TextOverflow.Ellipsis
		)
	}
}