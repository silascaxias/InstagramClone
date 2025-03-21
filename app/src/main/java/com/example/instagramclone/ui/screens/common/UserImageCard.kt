package com.example.instagramclone.ui.screens.common

import androidx.compose.foundation.Image
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import com.example.instagramclone.R
import com.example.instagramclone.ui.theme.InstagramCloneTheme

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
	modifier: Modifier
) {
	Card(shape = CircleShape, modifier = modifier) {
		if (imageUrl.isNullOrEmpty()) {
			Image(
				painter = painterResource(R.drawable.ic_person),
				contentDescription = null,
				colorFilter = ColorFilter.tint(MaterialTheme.colorScheme.primary)
			)
		} else {
			CommonImage(
				data = imageUrl
			)
		}
	}
}

@Preview
@Composable
private fun UserImageCardPreview() {
	InstagramCloneTheme {
		UserImageCard (
			imageUrl = "",
			modifier = Modifier
		)
	}
}
