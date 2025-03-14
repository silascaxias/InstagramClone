package com.example.instagramclone.views.common

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Card
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.instagramclone.R

/**
 * UserImageCard
 * InstagramClone
 *
 ** Created by Silas S. Caxias on 3/14/2025.
 *
 **/

@Composable
fun UserImageCard(
	imageUrl: String?,
	modifier: Modifier = Modifier
		.padding(8.dp)
		.size(64.dp)
) {
	Card(
		shape = CircleShape, modifier = modifier
	) {
		if (imageUrl.isNullOrEmpty()) {
			Image(
				painter = painterResource(R.drawable.ic_person),
				contentDescription = null
			)
		} else {
			CommonImage(
				data = imageUrl
			)
		}
	}
}