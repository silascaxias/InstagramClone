package com.example.instagramclone.views.navigation

/**
 * AppRouter
 * InstagramClone
 *
 ** Created by Silas S. Caxias on 3/13/2025.
 *
 **/

private object Routes {
	// Auth Graph Routes
	const val AUTH = "auth"
	const val LOGIN = "login"
	const val REGISTER = "signup"
	
	// Main Graph Routes
	const val MAIN = "main"
	const val FEED = "feed"
}

sealed class AppScreen(val route: String) {
	data object Auth : AppScreen(Routes.AUTH) {
		data object Login : AppScreen(Routes.LOGIN)
		data object Register : AppScreen(Routes.REGISTER)
	}
	
	data object Main : AppScreen(Routes.MAIN) {
		data object Feed : AppScreen(Routes.FEED)
	}
}