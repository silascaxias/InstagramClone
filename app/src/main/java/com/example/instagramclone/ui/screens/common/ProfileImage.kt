package com.example.instagramclone.ui.screens.common

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Add
import androidx.compose.material3.Card
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.example.instagramclone.R

/**
 * ProfileImage
 * InstagramClone
 *
 ** Created by Silas S. Caxias on 3/18/2025.
 *
 **/

@Composable
fun ProfileImage(
	imageUrl: String?,
	onClick: (() -> Unit)? = null,
	width: Dp,
	showAddIcon: Boolean
) {
	Box(
		modifier = if (showAddIcon) {
			Modifier.clickable { onClick?.invoke() }
		} else {
			Modifier
		}
	) {
		UserImageCard(
			imageUrl = imageUrl,
			modifier = Modifier
				.padding(8.dp)
				.size(width)
		)
		if (showAddIcon) {
			Card(
				shape = CircleShape,
				border = BorderStroke(width = 2.dp, color = Color.White),
				modifier = Modifier
					.size(32.dp)
					.align(Alignment.BottomEnd)
					.padding(bottom = 8.dp, end = 8.dp)
			) {
				Image(
					painter = rememberVectorPainter(Icons.Outlined.Add),
					contentDescription = null,
					modifier = Modifier.background(Color.Blue),
					colorFilter = ColorFilter.tint(Color.White)
				)
			}
		}
	}
}