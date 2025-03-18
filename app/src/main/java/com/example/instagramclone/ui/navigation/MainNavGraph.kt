package com.example.instagramclone.ui.navigation

import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.example.instagramclone.ui.screens.common.BottomNavigationItem
import com.example.instagramclone.ui.screens.main.EditProfileScreen
import com.example.instagramclone.viewmodel.InstagramViewModel
import com.example.instagramclone.ui.screens.main.FeedScreen
import com.example.instagramclone.ui.screens.main.NewPostScreen
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
	viewModel: InstagramViewModel,
	selectedItem: BottomNavigationItem
) {
	val navigateToDestination: (AppScreen) -> Unit = { destination: AppScreen ->
		navController.navigate(destination.route) {
			popUpTo(navController.currentDestination?.route.toString()) {
				inclusive = true
			}
		}
	}
	
	navigation(
		startDestination = AppScreen.Main.Profile.route,
		route = AppScreen.Main.route,
	) {
		composable(
			route = AppScreen.Main.Feed.route
		) {
			FeedScreen()
		}
		composable(
			route = AppScreen.Main.Search.route
		) {
			SearchScreen()
		}
		composable(
			route = AppScreen.Main.Profile.route
		) {
			ProfileScreen(
				navigateToNewPost = { route ->
					navController.navigate(route)
				},
				navigateToEditProfile = {
					navController.navigate(AppScreen.Main.EditProfile.route)
				},
				viewModel = viewModel
			)
		}
		composable(
			route = AppScreen.Main.EditProfile.route
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
		composable(
			route = AppScreen.Main.NewPost.route
		) { navBackStackEntry ->
			navBackStackEntry.arguments?.getString("imageUri")?.let { imageUri ->
				NewPostScreen(
					navigateToPostList = {
						navController.popBackStack()
					},
					viewModel = viewModel,
					encodedUri = imageUri
				)
			}
		}
	}
}