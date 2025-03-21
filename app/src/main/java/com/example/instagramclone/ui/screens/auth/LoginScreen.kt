package com.example.instagramclone.ui.screens.auth

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.instagramclone.R
import com.example.instagramclone.ui.screens.common.LoadingProgressIndicator
import com.example.instagramclone.viewmodel.InstagramViewModel
import com.example.instagramclone.viewmodel.onLogin

/**
 * LoginScreen
 * InstagramClone
 *
 ** Created by Silas S. Caxias on 3/13/2025.
 *
 **/

@Composable
fun LoginScreen(
	navigateToSignUp: () -> Unit,
	viewModel: InstagramViewModel
) {
	Box(
		modifier =
			Modifier.imePadding(),
	) {
		Column(
			modifier = Modifier
				.fillMaxSize(),
			horizontalAlignment = Alignment.CenterHorizontally,
			verticalArrangement = Arrangement.Center
		) {
			val email = remember { mutableStateOf(TextFieldValue()) }
			val password = remember { mutableStateOf(TextFieldValue()) }
			val focus = LocalFocusManager.current
			
			Image(
				painter = painterResource(R.drawable.instagram_logo),
				contentDescription = "Instagram Logo",
				modifier = Modifier
					.width(250.dp)
					.padding(top = 16.dp)
					.padding(8.dp),
				colorFilter = ColorFilter.tint(MaterialTheme.colorScheme.primary)
			)
			Text(
				text = "Login",
				modifier = Modifier
					.padding(8.dp),
				fontSize = 30.sp,
				fontFamily = FontFamily.Serif
			)
			OutlinedTextField(
				value = email.value,
				onValueChange = { email.value = it },
				modifier = Modifier
					.padding(8.dp),
				label = { Text(text = "Email") }
			)
			OutlinedTextField(
				value = password.value,
				onValueChange = { password.value = it },
				modifier = Modifier
					.padding(8.dp),
				label = { Text(text = "Password") },
				visualTransformation = PasswordVisualTransformation()
			)
			Button(
				modifier = Modifier.padding(8.dp),
				onClick = {
					focus.clearFocus(force = true)
					viewModel.onLogin(
						email.value.text,
						password.value.text
					)
				}
			) {
				Text(text = "LOGIN")
			}
			Text(
				text = "New here? Go to Sign Up ->",
				color = MaterialTheme.colorScheme.secondary,
				fontWeight = FontWeight.Bold,
				modifier = Modifier
					.padding(8.dp)
					.clickable {
						navigateToSignUp()
					}
			)
		}
		
		val isLoading = viewModel.isLoading.value
		if (isLoading) {
			LoadingProgressIndicator()
		}
	}
}