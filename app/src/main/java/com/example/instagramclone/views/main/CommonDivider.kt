package com.example.instagramclone.views.main

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.unit.dp

/**
 * CommonDivider
 * InstagramClone
 *
 ** Created by Silas S. Caxias on 3/14/2025.
 *
 **/

@Composable
fun CommonDivider() {
	HorizontalDivider(
		modifier = Modifier.alpha(0.3f).padding(top = 8.dp, bottom = 8.dp),
		thickness = 1.dp,
		color = MaterialTheme.colorScheme.primary
	)
}