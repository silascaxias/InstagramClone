package com.example.instagramclone.ui.screens.common

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import com.example.instagramclone.data.entity.PostWithUser

/**
 * PostCard
 * InstagramClone
 *
 ** Created by Silas S. Caxias on 3/18/2025.
 *
 **/

@Composable
fun PostItem(item: PostWithUser, onClick: () -> Unit = {}) {
	Row(
		modifier = Modifier
			.fillMaxSize()
			.padding(0.5.dp)
	) {
		PostImage(
			imageUrl = item.post.imageUrl,
			modifier = Modifier
				.weight(1f)
				.clickable { onClick() })
	}
}

@Composable
fun PostImage(imageUrl: String, modifier: Modifier) {
	Box(modifier = modifier) {
		CommonImage(data = imageUrl, contentScale = ContentScale.Crop)
	}
}