package com.example.instagramclone.views.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.example.instagramclone.viewmodel.InstagramViewModel
import com.example.instagramclone.views.main.FeedScreen
import com.example.instagramclone.views.main.ProfileScreen
import com.example.instagramclone.views.main.SearchScreen

/**
 * MainNavGraph
 * InstagramClone
 *
 ** Created by Silas S. Caxias on 3/13/2025.
 *
 **/

fun NavGraphBuilder.mainNavGraph(
	navController: NavHostController,
	viewModel: InstagramViewModel
) {
	val navigateToDestination: (AppScreen) -> Unit = { destination: AppScreen ->
		navController.navigate(destination.route)
	}
	
	navigation(
		startDestination = AppScreen.Main.PROFILE.route,
		route = AppScreen.Main.route,
	) {
		composable(
			route = AppScreen.Main.FEED.route
		) {
			FeedScreen(
				navigateToDestination = navigateToDestination,
				viewModel = viewModel
			)
		}
		composable(
			route = AppScreen.Main.SEARCH.route
		) {
			SearchScreen(
				navigateToDestination = navigateToDestination,
				viewModel = viewModel
			)
		}
		composable(
			route = AppScreen.Main.PROFILE.route
		) {
			ProfileScreen(
				navigateToDestination = navigateToDestination,
				viewModel = viewModel
			)
		}
	}
}