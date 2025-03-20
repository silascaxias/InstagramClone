package com.example.instagramclone.ui.navigation

import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.example.instagramclone.ui.screens.main.EditProfileScreen
import com.example.instagramclone.ui.screens.main.FeedScreen
import com.example.instagramclone.ui.screens.main.NewPostScreen
import com.example.instagramclone.ui.screens.main.PostScreen
import com.example.instagramclone.ui.screens.main.ProfileScreen
import com.example.instagramclone.ui.screens.main.SearchScreen
import com.example.instagramclone.viewmodel.InstagramViewModel

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
		startDestination = AppScreen.Main.Profile.route,
		route = AppScreen.Main.route,
	) {
		composable(
			route = AppScreen.Main.Feed.route
		) {
			FeedScreen(
				viewModel = viewModel
			)
		}
		composable(
			route = AppScreen.Main.Search.route
		) {
			SearchScreen(
				viewModel = viewModel,
				navigateToPost = { postId ->
					navController.navigate(AppScreen.Main.Post.createRoute(postId))
				}
			)
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
				navigateToPost = { postId ->
					navController.navigate(AppScreen.Main.Post.createRoute(postId))
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
		composable (
			route = AppScreen.Main.Post.route
		) { navBackStackEntry ->
			navBackStackEntry.arguments?.getString("postId")?.let { postId ->
				PostScreen(
					postId = postId.toInt(),
					viewModel = viewModel,
					navigateToBackScreen = {
						navController.popBackStack()
					}
				)
			}
		}
	}
}