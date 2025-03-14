package com.example.instagramclone.views.main

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.instagramclone.ui.theme.InstagramCloneTheme
import com.example.instagramclone.viewmodel.InstagramViewModel
import com.example.instagramclone.views.common.LoadingProgressIndicator

/**
 * EditProfileScreen
 * InstagramClone
 *
 ** Created by Silas S. Caxias on 3/14/2025.
 *
 **/

@Composable
fun EditProfileScreen(
	navigateToAuth: () -> Unit,
	navigateToProfile: () -> Unit,
	viewModel: InstagramViewModel
) {
	val isLoading = viewModel.isLoading.value
	
	val userData = viewModel.userData.value
	var name by rememberSaveable { mutableStateOf(userData?.name ?: "") }
	var username by rememberSaveable { mutableStateOf(userData?.username ?: "") }
	var bio by rememberSaveable { mutableStateOf(userData?.bio ?: "") }
	
	ProfileContent(
		name = name,
		username = username,
		bio = bio,
		onNameChanged = { name = it },
		onUsernameChanged = { username = it },
		onBioChanged = { bio = it },
		onSave = {
			viewModel.createOrUpdateProfile(
				name = name,
				username = username,
				bio = bio
			)
		},
		onBack = navigateToProfile,
		onLogout = {
			viewModel.onLogout()
			navigateToAuth()
		}
	)
	
	if (isLoading)
		LoadingProgressIndicator()
}

@Composable
fun ProfileContent(
	name: String,
	username: String,
	bio: String,
	onNameChanged: (String) -> Unit,
	onUsernameChanged: (String) -> Unit,
	onBioChanged: (String) -> Unit,
	onSave: () -> Unit,
	onBack: () -> Unit,
	onLogout: () -> Unit
) {
	Box(
		modifier =
			Modifier.imePadding(),
	) {
		Column(
			modifier = Modifier
				.verticalScroll(rememberScrollState())
				.padding(8.dp)
				.imePadding(),
		) {
			Row(
				modifier = Modifier
					.fillMaxWidth()
					.padding(8.dp),
				horizontalArrangement = Arrangement.SpaceBetween
			) {
				Text(text = "Back", modifier = Modifier.clickable { onBack() })
				Text(text = "Save", modifier = Modifier.clickable { onSave() })
			}
			
			CommonDivider()
			
			Column(
				modifier = Modifier
					.height(200.dp)
					.fillMaxWidth()
					.background(Color.Gray)
			) {
			
			}
			
			CommonDivider()
			
			Row(
				modifier = Modifier
					.fillMaxWidth()
					.padding(start = 4.dp, end = 4.dp),
				verticalAlignment = Alignment.CenterVertically
			) {
				Text(text = "Name", modifier = Modifier.width(100.dp))
				TextField(
					value = name,
					onValueChange = onNameChanged,
					colors = TextFieldDefaults.colors(
						focusedContainerColor = Color.Transparent,
						unfocusedContainerColor = Color.Transparent,
						focusedTextColor = MaterialTheme.colorScheme.primary,
						focusedIndicatorColor = MaterialTheme.colorScheme.primary,
						unfocusedIndicatorColor = MaterialTheme.colorScheme.primary,
						cursorColor = MaterialTheme.colorScheme.primary
					)
				)
			}
			
			Row(
				modifier = Modifier
					.fillMaxWidth()
					.padding(start = 4.dp, end = 4.dp),
				verticalAlignment = Alignment.CenterVertically
			) {
				Text(text = "Username", modifier = Modifier.width(100.dp))
				TextField(
					value = username,
					onValueChange = onUsernameChanged,
					colors = TextFieldDefaults.colors(
						focusedContainerColor = Color.Transparent,
						unfocusedContainerColor = Color.Transparent,
						focusedTextColor = MaterialTheme.colorScheme.primary,
						focusedIndicatorColor = MaterialTheme.colorScheme.primary,
						unfocusedIndicatorColor = MaterialTheme.colorScheme.primary,
						cursorColor = MaterialTheme.colorScheme.primary
					)
				)
			}
			
			Row(
				modifier = Modifier
					.fillMaxWidth()
					.padding(start = 4.dp, end = 4.dp),
				verticalAlignment = Alignment.Top
			) {
				Text(text = "Bio", modifier = Modifier.width(100.dp))
				TextField(
					value = bio,
					onValueChange = onBioChanged,
					colors = TextFieldDefaults.colors(
						focusedContainerColor = Color.Transparent,
						unfocusedContainerColor = Color.Transparent,
						focusedTextColor = MaterialTheme.colorScheme.primary,
						focusedIndicatorColor = MaterialTheme.colorScheme.primary,
						unfocusedIndicatorColor = MaterialTheme.colorScheme.primary,
						cursorColor = MaterialTheme.colorScheme.primary
					),
					singleLine = false,
					modifier = Modifier.height(150.dp)
				)
			}
		}
		
		Row(
			modifier = Modifier
				.fillMaxWidth()
				.padding(top = 16.dp, bottom = 16.dp),
			horizontalArrangement = Arrangement.Center
		) {
			Text(text = "Logout", modifier = Modifier.clickable {
				onLogout()
			})
		}
	}
}

@Preview(showBackground = true)
@Composable
private fun ProfileContentPreview() {
	InstagramCloneTheme {
		ProfileContent(
			name = "Naruto Uzumaki",
			username = "naruto_uzumaki",
			bio = "This is the profile account of Naruto Uzumaki.",
			onNameChanged = {},
			onUsernameChanged = {},
			onBioChanged = {},
			onSave = {},
			onBack = {},
			onLogout = {}
		)
	}
}