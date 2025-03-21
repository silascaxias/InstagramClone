package com.example.instagramclone.ui.screens.main

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.instagramclone.R
import com.example.instagramclone.data.entity.PostWithUser
import com.example.instagramclone.data.entity.User
import com.example.instagramclone.ui.screens.common.CommonImage
import com.example.instagramclone.ui.screens.common.LargePostItem
import com.example.instagramclone.ui.screens.common.LoadingProgressIndicator
import com.example.instagramclone.ui.screens.common.ProfileImage
import com.example.instagramclone.ui.screens.common.TopNavigationBar
import com.example.instagramclone.viewmodel.InstagramViewModel
import com.example.instagramclone.viewmodel.deletePost
import com.example.instagramclone.viewmodel.getPost
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

/**
 * PostScreen
 * InstagramClone
 *
 ** Created by Silas S. Caxias on 3/19/2025.
 *
 **/

@Composable
fun PostScreen(
    postId: Int,
    viewModel: InstagramViewModel,
    navigateToBackScreen: () -> Unit
) {
    val postWithUser = viewModel.getPost(postId = postId).collectAsState(initial = null).value
    val isLoading = viewModel.isLoading.value
    postWithUser?.let {
        Column {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentHeight()
            ) {
                TopNavigationBar(
                    title = "Post",
                    onBackClick = navigateToBackScreen,
                )
            }

            LargePostItem(
                currentUser = viewModel.currentUser.value,
                postWithUser = postWithUser,
                modifier = Modifier
                    .weight(1f)
                    .padding(bottom = 12.dp),
                onDelete = {
                    viewModel.deletePost(postWithUser.post.id) {
                        navigateToBackScreen()
                    }
                }
            )
        }
    }
    if (isLoading)
        LoadingProgressIndicator()
}
