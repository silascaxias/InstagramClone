package com.example.instagramclone

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController
import com.example.instagramclone.ui.navigation.AppScreen
import com.example.instagramclone.ui.navigation.RootNavGraph
import com.example.instagramclone.ui.screens.common.BottomNavigationBar
import com.example.instagramclone.ui.screens.common.BottomNavigationItem
import com.example.instagramclone.ui.screens.common.NotificationMessage
import com.example.instagramclone.ui.theme.InstagramCloneTheme
import com.example.instagramclone.viewmodel.InstagramViewModel
import org.koin.androidx.compose.koinViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : ComponentActivity() {
	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		enableEdgeToEdge()
		setContent {
			val navHostController = rememberNavController()
			var selectedItem by remember { mutableStateOf<BottomNavigationItem>(BottomNavigationItem.PROFILE)}
			val navigateToDestination: (AppScreen) -> Unit = { destination: AppScreen ->
				navHostController.navigate(destination.route) {
					selectedItem = when (destination) {
						AppScreen.Main.Feed -> {
							BottomNavigationItem.FEED
						}
						AppScreen.Main.Profile -> {
							BottomNavigationItem.PROFILE
						}
						else -> {
							BottomNavigationItem.SEARCH
						}
					}
					popUpTo(navHostController.currentDestination?.route.toString()) {
						inclusive = true
					}
				}
			}
			
			val viewModel = koinViewModel<InstagramViewModel>()
			InstagramCloneTheme {
				Scaffold (
					modifier = Modifier
						.background(MaterialTheme.colorScheme.background)
						.fillMaxSize()
						.systemBarsPadding()
						.navigationBarsPadding(),
					bottomBar = {
						if (viewModel.signedIn.value) {
							BottomNavigationBar(
								selectedItem = selectedItem,
								navigateToDestination = navigateToDestination
							)
						}
					},
					
				){ innerPadding ->
					RootNavGraph(
						navHostController = navHostController,
						selectedItem = selectedItem,
						viewModel = viewModel,
						innerPadding = innerPadding
					)
					NotificationMessage(viewModel = viewModel)
				}
			}
		}
	}
	
	override fun onDestroy() {
		val viewModel by viewModel<InstagramViewModel>()
		viewModel.onDestroy()
		super.onDestroy()
	}
}
