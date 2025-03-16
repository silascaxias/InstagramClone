package com.example.instagramclone.views.common

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImagePainter
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest

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
	contentScale: ContentScale = ContentScale.FillWidth
) {
	val painter = rememberAsyncImagePainter(
		model = ImageRequest.Builder(LocalContext.current)
			.data(data)
			.size(coil.size.Size.ORIGINAL)
			.build()
	)
	if (painter.state is AsyncImagePainter.State.Loading) {
		LoadingProgressIndicator(width = 50.dp)
	} else {
		Image(
			painter = painter,
			contentDescription = null,
			modifier = Modifier.fillMaxSize(),
			contentScale = contentScale
		)
	}
}
