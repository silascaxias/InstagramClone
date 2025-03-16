package com.example.instagramclone

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController
import com.example.instagramclone.ui.theme.InstagramCloneTheme
import com.example.instagramclone.viewmodel.InstagramViewModel
import com.example.instagramclone.ui.screens.common.NotificationMessage
import com.example.instagramclone.ui.navigation.RootNavGraph
import org.koin.androidx.compose.koinViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : ComponentActivity() {
	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		enableEdgeToEdge()
		setContent {
			InstagramCloneTheme {
				Surface(
					modifier = Modifier
						.background(MaterialTheme.colorScheme.primary)
						.fillMaxSize()
						.systemBarsPadding(),
				) {
					val viewModel = koinViewModel<InstagramViewModel>()
					NotificationMessage(viewModel = viewModel)
					val navHostController = rememberNavController()
					RootNavGraph(
						navHostController = navHostController,
						viewModel = viewModel
					)
				}
			}
		}
	}
	
	override fun onDestroy() {
		super.onDestroy()
		val viewModel by viewModel<InstagramViewModel>()
		viewModel.onDestroy()
	}
}
