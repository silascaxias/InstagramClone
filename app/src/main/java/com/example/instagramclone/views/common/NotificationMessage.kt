package com.example.instagramclone.views.common

import android.widget.Toast
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import com.example.instagramclone.viewmodel.InstagramViewModel

/**
 * MainCommonBits
 * InstagramClone
 *
 ** Created by Silas S. Caxias on 3/13/2025.
 *
 **/

@Composable
fun NotificationMessage(viewModel: InstagramViewModel) {
	val notification = viewModel.popUpNotification.value
	val notificationMessage = notification?.getContentOrNull()
	
	if (notificationMessage != null) {
		Toast.makeText(LocalContext.current, notificationMessage, Toast.LENGTH_LONG).show()
	}
}
