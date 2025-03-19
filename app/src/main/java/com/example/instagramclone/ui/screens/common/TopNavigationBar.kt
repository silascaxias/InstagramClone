package com.example.instagramclone.ui.screens.common

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp

/**
 * TopNavigationBar
 * InstagramClone
 *
 ** Created by Silas S. Caxias on 3/19/2025.
 *
 **/

@Composable
fun TopNavigationBar(
	title: String,
	modifier: Modifier = Modifier,
	onBackClick: () -> Unit
) {
	Column {
		Box(
			modifier = modifier
				.fillMaxWidth()
				.background(Color.Transparent)
				.padding(horizontal = 8.dp)
		) {
			IconButton(
				onClick = onBackClick,
				modifier = Modifier.align(Alignment.CenterStart)
			) {
				Icon(
					imageVector = Icons.AutoMirrored.Filled.ArrowBack,
					contentDescription = "Back"
				)
			}
			Text(
				text = title,
				style = MaterialTheme.typography.titleLarge,
				textAlign = TextAlign.Center,
				modifier = Modifier
					.align(Alignment.Center)
			)
		}
		CommonDivider(width = 0.5.dp, modifier = Modifier
			.alpha(0.3f)
			.padding(4.dp))
	}
}