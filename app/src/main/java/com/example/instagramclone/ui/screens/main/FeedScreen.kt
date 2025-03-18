package com.example.instagramclone.ui.screens.main

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

/**
 * FeedScreen
 * InstagramClone
 *
 ** Created by Silas S. Caxias on 3/13/2025.
 *
 **/

@Composable
fun FeedScreen() {
	Column(modifier = Modifier.fillMaxSize()) {
		Column(modifier = Modifier.weight(1f)) {
			Text(text = "Feed Screen")
		}
	}
}
