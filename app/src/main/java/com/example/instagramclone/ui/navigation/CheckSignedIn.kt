package com.example.instagramclone.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.navigation.NavController
import com.example.instagramclone.viewmodel.InstagramViewModel

/**
 * CheckSignedIn
 * InstagramClone
 *
 ** Created by Silas S. Caxias on 3/14/2025.
 *
 **/

@Composable
fun CheckSignedIn(
	navHostController: NavController,
	viewModel: InstagramViewModel
) {
	val alreadySignedIn = remember { mutableStateOf(false) }
	val signedIn = viewModel.signedIn.value
	if (signedIn && !alreadySignedIn.value) {
		alreadySignedIn.value = true
		navHostController.navigate(AppScreen.Main.route) {
			popUpTo(AppScreen.Auth.route) {  inclusive = true }
		}
	}
}