package com.example.instagramclone.ui.screens.main

import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.instagramclone.R
import com.example.instagramclone.ui.screens.common.LargePostItem
import com.example.instagramclone.ui.screens.common.LoadingProgressIndicator
import com.example.instagramclone.viewmodel.InstagramViewModel
import com.example.instagramclone.viewmodel.deletePost

/**
 * FeedScreen
 * InstagramClone
 *
 ** Created by Silas S. Caxias on 3/13/2025.
 *
 **/

@Composable
fun FeedScreen(
	viewModel: InstagramViewModel
) {
	val posts = viewModel.allPosts.collectAsState(emptyList())
	val isLoading = viewModel.isLoading.value
	
	Column(
		modifier = Modifier
			.fillMaxSize()
			.padding(top = 12.dp, bottom = 12.dp)
			.verticalScroll(rememberScrollState())
	) {
		Row(
			modifier = Modifier
				.fillMaxWidth()
				.padding(horizontal = 12.dp),
			horizontalArrangement = Arrangement.SpaceBetween,
			verticalAlignment = Alignment.CenterVertically
		) {
			Image(
				painter = painterResource(R.drawable.instagram_logo),
				contentDescription = "Instagram Logo",
				contentScale = ContentScale.FillWidth,
				modifier = Modifier
					.width(130.dp)
					.height(60.dp),
				colorFilter = ColorFilter.tint(MaterialTheme.colorScheme.primary)
			)
			Image(
				painter = rememberVectorPainter(Icons.Default.FavoriteBorder),
				contentDescription = "Like",
				colorFilter = ColorFilter.tint(MaterialTheme.colorScheme.primary),
				modifier = Modifier.size(28.dp)
			)
		}
		
		Column(
			modifier = Modifier.fillMaxSize()
		) {
			if (isLoading) {
				LoadingProgressIndicator()
			} else if (posts.value.isNotEmpty()) {
				posts.value.forEach { postWithUser ->
					Row {
						LargePostItem(
							currentUser = viewModel.currentUser.value,
							postWithUser = postWithUser,
							modifier = Modifier.padding(bottom = 12.dp),
							onDelete = {
								viewModel.deletePost(postWithUser.post.id)
							}
						)
					}
				}
			}
		}
	}

	if (posts.value.isEmpty()) {
		Column(
			horizontalAlignment = Alignment.CenterHorizontally,
			verticalArrangement = Arrangement.Center,
			modifier = Modifier.fillMaxSize()
		) {
			Text(text = "No posts available.")
		}
	}
}
