package com.diphrogram.qstestapp.ui.widgets

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBars
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.GridItemSpan
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
import com.diphrogram.qstestapp.MR
import com.diphrogram.qstestapp.common.Constants
import com.diphrogram.qstestapp.domain.entities.search.movies.SearchMovie
import com.diphrogram.qstestapp.domain.entities.search.series.SearchSeries
import dev.icerock.moko.resources.compose.stringResource
import org.jetbrains.compose.resources.painterResource

@Composable
fun SearchList(
	movieList: List<SearchMovie>?,
	seriesList: List<SearchSeries>?,
	onItemClicked: (String, Boolean) -> Unit
) {
	LazyVerticalGrid(
		modifier = Modifier.padding(
			top = 20.dp,
			bottom = 120.dp
		),
		columns = GridCells.Fixed(3),
		horizontalArrangement = Arrangement.spacedBy(12.dp),
		verticalArrangement = Arrangement.spacedBy(12.dp)
	) {
		item(span = { GridItemSpan(3) }) {
			Text(
				text = stringResource(MR.strings.movies),
				fontSize = 20.sp,
				style = MaterialTheme.typography.bodyMedium
			)
		}
		if (movieList != null){
			items(
				count = movieList.size,
				key = { it }
			) { index ->
				MovieItem(
					movieList[index],
					onItemClicked
				)
			}
		}
		item(span = { GridItemSpan(3) }) {
			Text(
				text = stringResource(MR.strings.series),
				fontSize = 20.sp,
				style = MaterialTheme.typography.bodyMedium
			)
		}
		if (seriesList != null){
			items(
				count = seriesList.size,
				key = { it }
			) {index ->
				SeriesItem(
					seriesList[index],
					onItemClicked
				)
			}
		}
		item(
			span = { GridItemSpan(3) }
		) {
			Spacer(modifier = Modifier
				.fillMaxWidth()
				.height(10.dp))
		}
	}
}

@Composable
fun MovieItem(
	movie: SearchMovie,
	onItemClicked: (String, Boolean) -> Unit
) {
	Column(
		modifier = Modifier
			.width(60.dp)
			.clickable {
				onItemClicked(movie.id.toString(), true)
			},
		horizontalAlignment = Alignment.CenterHorizontally,
		verticalArrangement = Arrangement.spacedBy(4.dp)
	) {
		if (movie.posterPath != null){
			AsyncImage(
				model = "${Constants.IMAGE_URL}${movie.posterPath}",
				modifier = Modifier
					.fillMaxWidth()
					.height(140.dp)
					.clip(MaterialTheme.shapes.large),
				contentDescription = null,
				contentScale = ContentScale.Fit
			)
		} else {
			Image(
				painter = painterResource(Constants.NO_POSTER),
				modifier = Modifier
					.fillMaxWidth()
					.height(140.dp)
					.clip(MaterialTheme.shapes.large),
				contentDescription = null,
				contentScale = ContentScale.Fit
			)
		}
		Text(
			modifier = Modifier
				.fillMaxWidth(),
			text = movie.title.ifEmpty { movie.originalTitle },
			fontSize = 14.sp,
			textAlign = TextAlign.Center,
			maxLines = 2,
			overflow = TextOverflow.Ellipsis
		)
	}
}


@Composable
fun SeriesItem(
	series: SearchSeries,
	onItemClicked: (String, Boolean) -> Unit
) {
	Column(
		modifier = Modifier
			.width(60.dp)
			.clickable {
				onItemClicked(series.id.toString(), false)
			},
		horizontalAlignment = Alignment.CenterHorizontally,
		verticalArrangement = Arrangement.spacedBy(4.dp)
	) {
		if (series.posterPath != null) {
			AsyncImage(
				model = "${Constants.IMAGE_URL}${series.posterPath}",
				modifier = Modifier
					.fillMaxWidth()
					.height(140.dp)
					.clip(MaterialTheme.shapes.large),
				contentDescription = null,
				contentScale = ContentScale.Fit
			)
		} else {
			Image(
				painter = painterResource(Constants.NO_POSTER),
				modifier = Modifier
					.fillMaxWidth()
					.height(140.dp)
					.clip(MaterialTheme.shapes.large),
				contentDescription = null,
				contentScale = ContentScale.Fit
			)
		}
		Text(
			modifier = Modifier.fillMaxWidth(),
			text = if (!series.name.isNullOrBlank()) {
				series.name
			} else {
				series.originalName.orEmpty()
			},
			fontSize = 14.sp,
			textAlign = TextAlign.Center,
			maxLines = 2,
			overflow = TextOverflow.Ellipsis
		)
	}
}