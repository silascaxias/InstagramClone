package com.example.instagramclone.ui.screens.main

import android.net.Uri
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import com.example.instagramclone.ui.screens.common.CommonDivider
import com.example.instagramclone.ui.screens.common.LoadingProgressIndicator
import com.example.instagramclone.viewmodel.InstagramViewModel
import com.example.instagramclone.viewmodel.createNewPost
import androidx.core.net.toUri
import com.example.instagramclone.utils.Utils.Companion.copyUriToInternalStorage
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

/**
 * NewPostScreen
 * InstagramClone
 *
 ** Created by Silas S. Caxias on 3/17/2025.
 *
 **/

@Composable
fun NewPostScreen(
	navigateToPostList: () -> Unit,
	viewModel: InstagramViewModel,
	encodedUri: String
) {
	var imageUri by remember { mutableStateOf(encodedUri) }
	var description by remember { mutableStateOf("") }
	val focusManager = LocalFocusManager.current
	val context = LocalContext.current
	val dateFormat = SimpleDateFormat("yyyy-MM-dd_HH-mm-ss", Locale.getDefault())
	
	Box(modifier = Modifier.imePadding()) {
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
				Text(
					text = "Cancel",
					modifier = Modifier.clickable {
						navigateToPostList()
					}
				)
				Text(
					text = "Post",
					modifier = Modifier.clickable {
						focusManager.clearFocus()
						copyUriToInternalStorage(context, imageUri.toUri(), "image_${dateFormat.format(Date())}.jpg")?.let {  imageUrlInternalPath ->
							viewModel.createNewPost(
								description = description,
								imageUrl = imageUrlInternalPath.toUri().toString(),
								onSuccess = {
									navigateToPostList()
								}
							)
						}
					}
				)
			}
			
			CommonDivider()
			
			Row(
				modifier = Modifier
					.wrapContentHeight()
					.fillMaxWidth(),
				horizontalArrangement = Arrangement.Center
			) {
				Image(
					painter = rememberAsyncImagePainter(imageUri),
					contentDescription = "Post Image",
					modifier = Modifier
						.fillMaxWidth()
						.defaultMinSize(minHeight = 150.dp),
					contentScale = ContentScale.FillWidth
				)
			}
			
			Row(
				modifier = Modifier
					.fillMaxWidth()
					.padding(start = 4.dp, end = 4.dp),
				verticalAlignment = Alignment.CenterVertically
			) {
				OutlinedTextField(
					value = description,
					onValueChange = { description = it },
					modifier = Modifier
						.fillMaxWidth()
						.height(150.dp),
					label = {
						Text(text = "Description")
					},
					singleLine = false,
					colors = TextFieldDefaults.colors(
						unfocusedIndicatorColor = MaterialTheme.colorScheme.primary,
						focusedIndicatorColor = MaterialTheme.colorScheme.primary,
						focusedContainerColor = Color.Transparent,
						unfocusedContainerColor = Color.Transparent,
						cursorColor = MaterialTheme.colorScheme.primary
					)
				)
			}
		}
		
		val isLoading = viewModel.isLoading.value
		if (isLoading)
			LoadingProgressIndicator()
	}
}