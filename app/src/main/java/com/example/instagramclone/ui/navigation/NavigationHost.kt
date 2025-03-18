package com.example.instagramclone.ui.navigation

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import com.example.instagramclone.ui.screens.common.BottomNavigationItem
import com.example.instagramclone.viewmodel.InstagramViewModel

/**
 * NavigationHost
 * InstagramClone
 *
 ** Created by Silas S. Caxias on 3/13/2025.
 *
 **/

@Composable
fun RootNavGraph(
	navHostController: NavHostController,
	viewModel: InstagramViewModel,
	selectedItem: BottomNavigationItem,
	innerPadding: PaddingValues
) {
	NavHost(
		navController = navHostController,
		startDestination = AppScreen.Auth.route,
		modifier = Modifier.padding(innerPadding)
	) {
		authNavGraph(
			navController = navHostController,
			viewModel = viewModel
		)
		mainNavGraph(
			navController = navHostController,
			selectedItem = selectedItem,
			viewModel = viewModel
		)
	}
}
