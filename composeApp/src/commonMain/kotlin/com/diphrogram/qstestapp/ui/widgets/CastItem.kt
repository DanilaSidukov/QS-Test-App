package com.diphrogram.qstestapp.ui.widgets

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.platform.LocalFontFamilyResolver
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Constraints
import androidx.compose.ui.unit.Density
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil3.compose.AsyncImage
import com.diphrogram.qstestapp.common.Constants
import com.diphrogram.qstestapp.domain.entities.details.cast.Cast
import org.jetbrains.compose.resources.painterResource

@Composable
fun CastItems(
	castList: List<Cast>,
	modifier: Modifier
) {

	val localDensity = LocalDensity.current
	val localFontFamilyResolver = LocalFontFamilyResolver.current
	val style = MaterialTheme.typography.displayMedium

	val height = remember(castList) {
		getMaxHeight(
			textList = castList.map(Cast::name),
			localDensity = localDensity,
			fontFamilyResolver = localFontFamilyResolver,
			style = style
		)
	}

	LazyRow(
		modifier = modifier,
		verticalAlignment = Alignment.Top,
		horizontalArrangement = Arrangement.spacedBy(12.dp),
	) {
		items(castList.size) { index ->
			val cast = castList[index]
			Column(
				modifier = Modifier
					.width(100.dp),
				verticalArrangement = Arrangement.spacedBy(4.dp)
			) {
				if (!cast.profilePath.isNullOrBlank()) {
					AsyncImage(
						model = "${Constants.IMAGE_URL}${cast.profilePath}",
						modifier = Modifier
							.size(100.dp)
							.clip(MaterialTheme.shapes.large),
						contentScale = ContentScale.Crop,
						contentDescription = null,
					)
				} else {
					Image(
						painter = painterResource(Constants.DEFAULT_AVATAR),
						modifier = Modifier
							.size(100.dp)
							.clip(MaterialTheme.shapes.large),
						contentScale = ContentScale.Crop,
						contentDescription = null,
					)
				}
				Text(
					modifier = Modifier
						.height(height),
					text = cast.name,
					textAlign = TextAlign.Center,
					fontSize = 16.sp,
					style = style
				)
			}
		}
	}
}

fun getMaxHeight(
	textList: List<String>,
	localDensity: Density,
	fontFamilyResolver: FontFamily.Resolver,
	style: TextStyle
): Dp {
	val pixelsInt = with(localDensity) { 100.dp.toPx() }.toInt()
	val heights = textList.map {
		val paragraph = androidx.compose.ui.text.Paragraph(
			text = it,
			style = style,
			constraints = Constraints(maxWidth = pixelsInt),
			density = localDensity,
			fontFamilyResolver = fontFamilyResolver,
		)
		paragraph.height
	}
	return (heights.max()/localDensity.density).dp
}