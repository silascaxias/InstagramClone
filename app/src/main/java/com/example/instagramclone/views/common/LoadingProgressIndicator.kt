package com.example.instagramclone.views.common

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.width
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

/**
 * LoadingProgressIndicator
 * InstagramClone
 *
 ** Created by Silas S. Caxias on 3/13/2025.
 *
 **/

@Composable
fun LoadingProgressIndicator(width: Dp = 64.dp) {
	Row(
		modifier = Modifier
			.alpha(0.7f)
			.background(MaterialTheme.colorScheme.background)
			.clickable(enabled = false) { }
			.fillMaxSize(),
		horizontalArrangement = Arrangement.Center,
		verticalAlignment = Alignment.CenterVertically
	) {
		CircularProgressIndicator(
			modifier = Modifier.width(width),
			color = MaterialTheme.colorScheme.secondary,
			trackColor = MaterialTheme.colorScheme.surfaceVariant,
		)
	}
}