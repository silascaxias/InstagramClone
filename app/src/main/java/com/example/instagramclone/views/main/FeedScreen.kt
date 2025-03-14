package com.example.instagramclone.views.main

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.instagramclone.ui.theme.InstagramCloneTheme
import com.example.instagramclone.viewmodel.InstagramViewModel
import com.example.instagramclone.views.navigation.AppScreen

/**
 * FeedScreen
 * InstagramClone
 *
 ** Created by Silas S. Caxias on 3/13/2025.
 *
 **/

@Composable
fun FeedScreen(
	navigateToDestination: (AppScreen) -> Unit, viewModel: InstagramViewModel
) {
	Column(
		modifier = Modifier.fillMaxSize()
	) {
		Column(
			modifier = Modifier.weight(1f)
		) {
			Text(text = "Feed Screen")
		}
		BottomNavigationBar(
			selectedItem = BottomNavigationItem.FEED, navigateToDestination = navigateToDestination
		)
	}
}
