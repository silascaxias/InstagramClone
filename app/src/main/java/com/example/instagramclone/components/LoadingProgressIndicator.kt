package com.example.instagramclone.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color

/**
 * LoadingProgressIndicator
 * InstagramClone
 *
 ** Created by Silas S. Caxias on 3/13/2025.
 *
 **/

@Composable
fun LoadingProgressIndicator() {
	Row(
		modifier = Modifier
			.alpha(0.5f)
			.background(Color.LightGray)
			.clickable(enabled = false) { }
			.fillMaxSize(),
		horizontalArrangement = Arrangement.Center,
		verticalAlignment = Alignment.CenterVertically
	) {
		CircularProgressIndicator()
	}
}