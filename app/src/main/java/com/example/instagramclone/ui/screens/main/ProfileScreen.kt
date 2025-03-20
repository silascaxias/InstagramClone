package com.example.instagramclone.ui.screens.main

import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
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
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import com.example.instagramclone.R
import com.example.instagramclone.ui.navigation.AppScreen
import com.example.instagramclone.ui.screens.common.LoadingProgressIndicator
import com.example.instagramclone.ui.screens.common.PostItem
import com.example.instagramclone.ui.screens.common.ProfileImage
import com.example.instagramclone.viewmodel.InstagramViewModel
import com.example.instagramclone.viewmodel.getUserPosts
import com.example.instagramclone.viewmodel.showMessage

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
	navigateToEditProfile: () -> Unit,
	navigateToPost: (Int) -> Unit
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
	val postsWithUsers = viewModel.getUserPosts().collectAsState(emptyList())
	
	Column {
		Column(
			modifier = Modifier
				.weight(1f)
				.padding(top = 12.dp, bottom = 12.dp)
		) {
			Row (modifier = Modifier.padding(end = 12.dp)){
				Column {
					ProfileImage(
						imageUrl = userData?.imageUrl,
						width = 90.dp,
						showAddIcon = true,
						onClick = {
							newPostImageLauncher.launch("image/*")
						})
				}
				Column(
					modifier = Modifier.padding(start = 20.dp),
					horizontalAlignment = Alignment.Start
				) {
					Row(modifier = Modifier.padding(bottom = 6.dp)) {
						Text(text = userData?.name ?: "", fontWeight = FontWeight.Bold)
					}
					Row(
						modifier = Modifier
							.fillMaxWidth(),
						horizontalArrangement = Arrangement.SpaceBetween
					) {
						Text(
							text = buildAnnotatedString {
								withStyle(style = SpanStyle(fontWeight = FontWeight.Bold)) {
									append("${postsWithUsers.value.size}\n")
								}
								append("posts")
							},
							textAlign = TextAlign.Start
						)
						Text(
							text = buildAnnotatedString {
								withStyle(style = SpanStyle(fontWeight = FontWeight.Bold)) {
									append("54\n")
								}
								append("followers")
							},
							textAlign = TextAlign.Start
						)
						Text(
							text = buildAnnotatedString {
								withStyle(style = SpanStyle(fontWeight = FontWeight.Bold)) {
									append("93\n")
								}
								append("following")
							},
							textAlign = TextAlign.Start
						)
					}
				}
			}
			
			Column(modifier = Modifier.padding(8.dp)) {
				val userNameToDisplay =
					if (userData?.username == null) "" else "@${userData.username}"
				Text(text = userNameToDisplay, modifier = Modifier.padding(bottom = 4.dp))
				Text(text = userData?.bio ?: "", fontWeight = FontWeight.Bold)
			}
			
			Row(
				modifier = Modifier
					.fillMaxWidth()
					.wrapContentHeight()
					.padding(8.dp),
				horizontalArrangement = Arrangement.spacedBy(8.dp)
			) {
				Row(
					modifier = Modifier.weight(1f),
					horizontalArrangement = Arrangement.spacedBy(8.dp)
				) {
					OutlinedButton(
						onClick = { navigateToEditProfile() },
						modifier = Modifier.weight(1f),
						colors = ButtonDefaults.buttonColors(containerColor = Color.Transparent),
						elevation = ButtonDefaults.buttonElevation(0.dp),
						shape = RoundedCornerShape(16),
						border = BorderStroke(1.dp, MaterialTheme.colorScheme.primary),
					) {
						Text("Edit", color = MaterialTheme.colorScheme.primary, fontWeight = FontWeight.Bold)
					}
					
					OutlinedButton(
						onClick = { viewModel.showMessage("Not implemented...") },
						modifier = Modifier.weight(1f),
						colors = ButtonDefaults.buttonColors(containerColor = Color.Transparent),
						elevation = ButtonDefaults.buttonElevation(0.dp),
						shape = RoundedCornerShape(16),
						border = BorderStroke(1.dp, MaterialTheme.colorScheme.primary)
					) {
						Text("Share Profile", color = MaterialTheme.colorScheme.primary, fontWeight = FontWeight.Bold)
					}
				}
				
				OutlinedButton(
					onClick = { viewModel.showMessage("Not implemented...") },
					colors = ButtonDefaults.buttonColors(containerColor = Color.Transparent),
					elevation = ButtonDefaults.buttonElevation(0.dp),
					shape = RoundedCornerShape(16),
					border = BorderStroke(1.dp, MaterialTheme.colorScheme.primary)
				) {
					Image(
						painter = painterResource(id = R.drawable.ic_add_person),
						contentDescription = "Add Person",
						colorFilter = ColorFilter.tint(MaterialTheme.colorScheme.primary)
					)
				}
			}
			
			Column(
				modifier = Modifier
					.weight(1f)
					.fillMaxSize()
			) {
				if (isLoading) {
					LoadingProgressIndicator()
				} else if (postsWithUsers.value.isEmpty()) {
					Column(
						horizontalAlignment = Alignment.CenterHorizontally,
						verticalArrangement = Arrangement.Center
					) {
						Text(text = "No posts available")
					}
				} else {
					LazyVerticalGrid(
						userScrollEnabled = true,
						columns = GridCells.Fixed(3),
						modifier = Modifier.fillMaxSize(),
					) {
						items(postsWithUsers.value.size) { postIndex ->
							val postWithUser = postsWithUsers.value[postIndex]
							PostItem(postWithUser) {
								navigateToPost(postWithUser.post.id)
							}
						}
					}
				}
			}
		}
	}
	if (isLoading) {
		LoadingProgressIndicator()
	}
}
