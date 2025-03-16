package com.example.instagramclone.views.main

import android.content.Context
import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
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
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.core.net.toUri
import com.example.instagramclone.utils.Utils
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
	viewModel: InstagramViewModel,
	context: Context
) {
	val isLoading = viewModel.isLoading.value
	
	val userData = viewModel.user.value
	var name by rememberSaveable { mutableStateOf(userData?.name ?: "") }
	var username by rememberSaveable { mutableStateOf(userData?.username ?: "") }
	var bio by rememberSaveable { mutableStateOf(userData?.bio ?: "") }
	var imageUrl by remember { mutableStateOf<String?>(userData?.imageUrl) }
	
	ProfileContent(
		name = name,
		username = username,
		bio = bio,
		imageUrl = imageUrl,
		onChangeImageUri = {
			imageUrl = it
		},
		onNameChanged = { name = it },
		onUsernameChanged = { username = it },
		onBioChanged = { bio = it },
		onSave = {
			var imageUrlInternalPath: String? = null
			imageUrl?.let { url ->
				imageUrlInternalPath =
					Utils.copyUriToInternalStorage(context, url.toUri(), "$name.jpg")
			}
			viewModel.updateUser(
				name = name,
				username = username,
				bio = bio,
				imageUrl = imageUrlInternalPath,
				onFinish = {
					navigateToProfile()
				}
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
	imageUrl: String?,
	onChangeImageUri: (String?) -> Unit,
	onNameChanged: (String) -> Unit,
	onUsernameChanged: (String) -> Unit,
	onBioChanged: (String) -> Unit,
	onSave: () -> Unit,
	onBack: () -> Unit,
	onLogout: () -> Unit
) {
	var launcher =
		rememberLauncherForActivityResult(contract = ActivityResultContracts.GetContent()) { uri: Uri? ->
			uri?.let {
				onChangeImageUri(it.toString())
			}
		}
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
					.wrapContentHeight()
					.fillMaxWidth(),
				horizontalAlignment = Alignment.CenterHorizontally
			) {
				ProfileImage(
					imageUrl = imageUrl,
					width = 200.dp,
					showAddIcon = false,
					onClick = {
						launcher.launch("image/*")
					}
				)
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
				verticalAlignment = Alignment.CenterVertically
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
			
			Row(
				modifier = Modifier
					.fillMaxWidth()
					.padding(top = 16.dp, bottom = 16.dp),
				horizontalArrangement = Arrangement.Center
			) {
				OutlinedButton(onClick = {
					onLogout()
				}) {
					Text(text = "Logout")
				}
			}
		}
	}
}

//@Preview(showBackground = true)
//@Composable
//private fun ProfileContentPreview() {
//	InstagramCloneTheme {
//		ProfileContent(
//			name = "Naruto Uzumaki",
//			username = "naruto_uzumaki",
//			bio = "This is the profile account of Naruto Uzumaki.",
//			onNameChanged = {},
//			onUsernameChanged = {},
//			onBioChanged = {},
//			onSave = {},
//			onBack = {},
//			onLogout = {}
//		)
//	}
//}