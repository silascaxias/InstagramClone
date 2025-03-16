package com.example.instagramclone.ui.navigation

import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.example.instagramclone.ui.screens.main.EditProfileScreen
import com.example.instagramclone.viewmodel.InstagramViewModel
import com.example.instagramclone.ui.screens.main.FeedScreen
import com.example.instagramclone.ui.screens.main.ProfileScreen
import com.example.instagramclone.ui.screens.main.SearchScreen

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
		navController.navigate(destination.route) {
			popUpTo(navController.currentDestination?.route.toString()) {
				inclusive = true
			}
		}
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
				navigateToEditProfile = {
					navController.navigate(AppScreen.Main.EDIT_PROFILE.route)
				},
				navigateToDestination = navigateToDestination,
				viewModel = viewModel
			)
		}
		composable(
			route = AppScreen.Main.EDIT_PROFILE.route
		) {
			EditProfileScreen(
				navigateToAuth = {
					navController.navigate(AppScreen.Auth.route) {
						popUpTo(AppScreen.Main.route) { inclusive = true }
					}
				},
				navigateToProfile = {
					navController.popBackStack()
				},
				viewModel = viewModel,
				context = LocalContext.current
			)
		}
	}
}