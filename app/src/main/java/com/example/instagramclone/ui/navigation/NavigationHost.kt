package com.example.instagramclone.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
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
	viewModel: InstagramViewModel
) {
	NavHost(navController = navHostController, startDestination = AppScreen.Auth.route) {
		authNavGraph(
			navController = navHostController,
			viewModel = viewModel
		)
		mainNavGraph(
			navController = navHostController,
			viewModel = viewModel
		)
	}
}
