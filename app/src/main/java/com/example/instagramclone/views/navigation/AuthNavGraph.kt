package com.example.instagramclone.views.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.example.instagramclone.viewmodel.InstagramViewModel
import com.example.instagramclone.views.auth.LoginScreen
import com.example.instagramclone.views.auth.SignUpScreen

/**
 * AuthNavGraph
 * InstagramClone
 *
 ** Created by Silas S. Caxias on 3/13/2025.
 *
 **/

fun NavGraphBuilder.authNavGraph(
	navController: NavController,
	viewModel: InstagramViewModel
) {
	navigation(
		startDestination = AppScreen.Auth.Login.route,
		route = AppScreen.Auth.route
	) {
		composable(
			route = AppScreen.Auth.Login.route
		) {
			CheckSignedIn(
				navHostController = navController,
				viewModel = viewModel
			)
			LoginScreen(
				navigateToSignUp = {
					navController.navigate(AppScreen.Auth.Register.route) {
						launchSingleTop = true
					}
				},
				viewModel = viewModel
			)
		}
		composable(
			route = AppScreen.Auth.Register.route
		) {
			CheckSignedIn(
				navHostController = navController,
				viewModel = viewModel
			)
			SignUpScreen(
				navigateToLogin = {
					navController.navigateUp()
				},
				viewModel = viewModel
			)
		}
	}
}