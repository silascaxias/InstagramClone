package com.example.instagramclone.views.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import com.example.instagramclone.viewmodel.InstagramViewModel
import com.example.instagramclone.views.main.BottomNavigationBar

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
