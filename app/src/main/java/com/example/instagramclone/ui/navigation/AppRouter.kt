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
	const val NEW_POST = "new_post/{imageUri}"
	const val POST = "post/{postId}"
}

sealed class AppScreen(val route: String) {
	data object Auth : AppScreen(Routes.AUTH) {
		data object Login : AppScreen(Routes.LOGIN)
		data object Register : AppScreen(Routes.REGISTER)
	}
	
	data object Main : AppScreen(Routes.MAIN) {
		data object Feed : AppScreen(Routes.FEED)
		data object Profile : AppScreen(Routes.PROFILE)
		data object Search : AppScreen(Routes.SEARCH)
		data object EditProfile : AppScreen(Routes.EDIT_PROFILE)
		data object NewPost : AppScreen(Routes.NEW_POST) {
			fun createRoute(encodedUri: String) = "new_post/$encodedUri"
		}
		data object Post : AppScreen(Routes.POST) {
			fun createRoute(postId: Int) = "post/$postId"
		}
	}
}