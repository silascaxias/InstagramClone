package com.example.instagramclone.views.main

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.instagramclone.viewmodel.InstagramViewModel
import com.example.instagramclone.views.navigation.AppScreen

/**
 * FeedScreen
 * InstagramClone
 *
 ** Created by Silas S. Caxias on 3/13/2025.
 *
 **/

@Composable
fun FeedScreen(
	navController: NavController,
	viewModel: InstagramViewModel
) {
	Column(
		modifier = Modifier.fillMaxWidth(),
		verticalArrangement = Arrangement.Center,
		horizontalAlignment = Alignment.CenterHorizontally
	) {
		Text(text = "Feed Screen")
		Button(
			modifier = Modifier.width(100.dp).height(50.dp),
			onClick = {
				viewModel.onLogout()
				navController.navigate(AppScreen.Auth.route) {
					popUpTo(AppScreen.Main.route) {
						inclusive = true
					}
				}
			}
		) {
			Text(text = "Logout")
		}
	}
}