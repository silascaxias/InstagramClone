package com.example.instagramclone.views.main

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import com.example.instagramclone.ui.theme.InstagramCloneTheme
import com.example.instagramclone.viewmodel.InstagramViewModel
import com.example.instagramclone.views.navigation.AppScreen

/**
 * SearchScreen
 * InstagramClone
 *
 ** Created by Silas S. Caxias on 3/14/2025.
 *
 **/

@Composable
fun SearchScreen(
	navigateToDestination: (AppScreen) -> Unit, viewModel: InstagramViewModel
) {
	Column(
		modifier = Modifier.fillMaxSize()
	) {
		Column(
			modifier = Modifier.weight(1f)
		) {
			Text(text = "Search Screen")
		}
		BottomNavigationBar(
			selectedItem = BottomNavigationItem.SEARCH,
			navigateToDestination = navigateToDestination
		)
	}
}
