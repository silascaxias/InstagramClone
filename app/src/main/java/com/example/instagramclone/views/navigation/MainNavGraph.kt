package com.example.instagramclone.views.navigation

import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.example.instagramclone.viewmodel.InstagramViewModel
import com.example.instagramclone.views.main.FeedScreen

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
	navigation(
		startDestination = AppScreen.Main.Feed.route,
		route = AppScreen.Main.route
	) {
		composable(
			route = AppScreen.Main.Feed.route
		) {
			FeedScreen(
				navController = navController,
				viewModel = viewModel
			)
		}
	}
}