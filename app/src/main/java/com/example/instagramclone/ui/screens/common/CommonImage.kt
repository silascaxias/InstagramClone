package com.example.instagramclone.ui.screens.common

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.outlined.Person
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImagePainter
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import coil.size.Size
import com.example.instagramclone.R

/**
 * CommonImage
 * InstagramClone
 *
 ** Created by Silas S. Caxias on 3/14/2025.
 *
 **/

@Composable
fun CommonImage(
	data: String?,
	modifier: Modifier = Modifier.fillMaxSize(),
	contentScale: ContentScale = ContentScale.FillWidth
) {
	val painter = rememberAsyncImagePainter(
		model = ImageRequest.Builder(LocalContext.current)
			.data(data)
			.size(Size.ORIGINAL)
			.build()
	)
	if (data.isNullOrEmpty()) {
		Image(
			painter = rememberVectorPainter(Icons.Filled.Person),
			contentDescription = null,
			modifier = modifier,
			contentScale = contentScale
		)
	} else if (painter.state is AsyncImagePainter.State.Loading) {
		LoadingProgressIndicator(width = 50.dp)
	} else {
		Image(
			painter = painter,
			contentDescription = null,
			modifier = modifier,
			contentScale = contentScale
		)
	}
}
