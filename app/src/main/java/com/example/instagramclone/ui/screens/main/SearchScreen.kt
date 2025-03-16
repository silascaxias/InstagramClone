package com.example.instagramclone.ui.screens.main

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.instagramclone.ui.navigation.AppScreen
import com.example.instagramclone.viewmodel.InstagramViewModel

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
	Column(modifier = Modifier.fillMaxSize()) {
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
