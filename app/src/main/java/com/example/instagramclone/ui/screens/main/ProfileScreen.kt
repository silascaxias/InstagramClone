package com.example.instagramclone.ui.screens.main

import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import com.example.instagramclone.ui.navigation.AppScreen
import com.example.instagramclone.ui.screens.common.LoadingProgressIndicator
import com.example.instagramclone.ui.screens.common.PostItem
import com.example.instagramclone.ui.screens.common.ProfileImage
import com.example.instagramclone.viewmodel.InstagramViewModel
import com.example.instagramclone.viewmodel.deleteAllPosts

/**
 * ProfileScreen
 * InstagramClone
 *
 ** Created by Silas S. Caxias on 3/14/2025.
 *
 **/

@Composable
fun ProfileScreen(
	navigateToNewPost: (String) -> Unit,
	viewModel: InstagramViewModel,
	navigateToEditProfile: () -> Unit
) {
	var newPostImageLauncher =
		rememberLauncherForActivityResult(contract = ActivityResultContracts.GetContent()) { uri: Uri? ->
			uri?.let {
				val encodedUri = Uri.encode(uri.toString())
				val route = AppScreen.Main.NewPost.createRoute(encodedUri = encodedUri)
				navigateToNewPost(route)
			}
		}
	
	val userData = viewModel.currentUser.value
	val isLoading = viewModel.isLoading.value
	val posts = viewModel.userPosts.collectAsState(emptyList())
	
	Column {
		Column(
			modifier = Modifier
				.weight(1f)
				.padding(top = 12.dp, bottom = 12.dp)
		) {
			Row {
				Column {
					ProfileImage(
						imageUrl = userData?.imageUrl,
						width = 80.dp,
						showAddIcon = true,
						onClick = {
							newPostImageLauncher.launch("image/*")
						})
				}
				Column {
					Row (modifier = Modifier.padding(start = 28.dp, bottom = 6.dp)) {
						Text(text = userData?.name ?: "test", fontWeight = FontWeight.Bold)
					}
					Row {
						Text(
							text = buildAnnotatedString {
								withStyle(style = SpanStyle(fontWeight = FontWeight.Bold)) {
									append("${posts.value.size}\n")
								}
								append("posts")
							},
							modifier = Modifier
								.weight(1f)
								.align(Alignment.CenterVertically),
							textAlign = TextAlign.Center
						)
						Text(
							text = buildAnnotatedString {
								withStyle(style = SpanStyle(fontWeight = FontWeight.Bold)) {
									append("54\n")
								}
								append("followers")
							},
							modifier = Modifier
								.weight(1f)
								.align(Alignment.CenterVertically),
							textAlign = TextAlign.Center
						)
						Text(
							text = buildAnnotatedString {
								withStyle(style = SpanStyle(fontWeight = FontWeight.Bold)) {
									append("93\n")
								}
								append("following")
							},
							modifier = Modifier
								.weight(1f)
								.align(Alignment.CenterVertically),
							textAlign = TextAlign.Center
						)
					}
				}
			}
			
			Column(modifier = Modifier.padding(8.dp)) {
				val userNameToDisplay =
					if (userData?.username == null) "" else "@${userData.username}"
				Text(text = userNameToDisplay)
				Text(text = userData?.bio ?: "", fontWeight = FontWeight.Bold)
			}
			
			OutlinedButton(
				onClick = {
					navigateToEditProfile()
				},
				modifier = Modifier
					.padding(8.dp)
					.fillMaxWidth(),
				colors = ButtonDefaults.buttonColors(
					containerColor = Color.Transparent
				),
				elevation = ButtonDefaults.buttonElevation(
					defaultElevation = 0.dp,
					pressedElevation = 0.dp,
					disabledElevation = 0.dp
				),
				shape = RoundedCornerShape(10)
			) {
				Text("EDIT PROFILE", color = MaterialTheme.colorScheme.primary)
			}
			
			Column(
				modifier = Modifier
					.weight(1f)
					.fillMaxSize()
			) {
				if (isLoading) {
					LoadingProgressIndicator()
				} else if (posts.value.isEmpty()) {
					Column(
						horizontalAlignment = Alignment.CenterHorizontally,
						verticalArrangement = Arrangement.Center
					) {
						Text(text = "No posts available")
					}
				} else {
					LazyVerticalGrid(
						columns = GridCells.Fixed(3),
						modifier = Modifier.fillMaxSize()
					) {
						items(posts.value.size) { postIndex ->
							PostItem(posts.value[postIndex])
						}
					}
				}
			}
			
			Row(
				modifier = Modifier
					.fillMaxWidth()
					.padding(top = 16.dp, bottom = 16.dp),
				horizontalArrangement = Arrangement.Center
			) {
				OutlinedButton(onClick = {
					viewModel.deleteAllPosts()
				}) {
					Text(text = "Delete All Posts")
				}
			}
		}
	}
	if (isLoading) {
		LoadingProgressIndicator()
	}
}
