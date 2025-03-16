package com.example.instagramclone.ui.navigation

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
	const val PROFILE = "profile"
	const val SEARCH = "search"
	const val EDIT_PROFILE = "edit_profile"
}

sealed class AppScreen(val route: String) {
	data object Auth : AppScreen(Routes.AUTH) {
		data object Login : AppScreen(Routes.LOGIN)
		data object Register : AppScreen(Routes.REGISTER)
	}
	
	data object Main : AppScreen(Routes.MAIN) {
		data object FEED : AppScreen(Routes.FEED)
		data object PROFILE : AppScreen(Routes.PROFILE)
		data object SEARCH : AppScreen(Routes.SEARCH)
		data object EDIT_PROFILE : AppScreen(Routes.EDIT_PROFILE)
	}
}