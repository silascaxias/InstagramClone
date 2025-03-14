package com.example.instagramclone.views.main

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.instagramclone.R
import com.example.instagramclone.viewmodel.InstagramViewModel
import com.example.instagramclone.views.common.UserImageCard
import com.example.instagramclone.views.navigation.AppScreen

/**
 * ProfileScreen
 * InstagramClone
 *
 ** Created by Silas S. Caxias on 3/14/2025.
 *
 **/

@Composable
fun ProfileScreen(
	navigateToDestination: (AppScreen) -> Unit, viewModel: InstagramViewModel
) {
	val userData = viewModel.userData.value
	val isLoading = viewModel.isLoading.value
	
	Column {
		Column(
			modifier = Modifier
				.weight(1f)
				.padding(top = 12.dp, bottom = 12.dp)
		) {
			Row {
				ProfileImage(imageUrl = userData?.imageUrl, onClick = {
				
				})
				Text(
					text = "15\nposts",
					modifier = Modifier
						.weight(1f)
						.align(Alignment.CenterVertically),
					textAlign = TextAlign.Center
				)
				Text(
					text = "54\nfollowers",
					modifier = Modifier
						.weight(1f)
						.align(Alignment.CenterVertically),
					textAlign = TextAlign.Center
				)
				Text(
					text = "93\nfollowing",
					modifier = Modifier
						.weight(1f)
						.align(Alignment.CenterVertically),
					textAlign = TextAlign.Center
				)
			}
			Column(modifier = Modifier.padding(8.dp)) {
				val userNameToDisplay =
					if (userData?.username == null) "" else "@${userData.username}"
				Text(text = userData?.name ?: "test", fontWeight = FontWeight.Bold)
				Text(text = userNameToDisplay)
				Text(text = userData?.bio ?: "")
			}
			OutlinedButton(
				onClick = {},
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
				modifier = Modifier.weight(1f)
			) {
				Text(text = "Post list")
			}
		}
		BottomNavigationBar(
			selectedItem = BottomNavigationItem.PROFILE,
			navigateToDestination = navigateToDestination
		)
	}
}

@Composable
fun ProfileImage(
	imageUrl: String?, onClick: () -> Unit
) {
	Box(
		modifier = Modifier
			.padding(top = 16.dp)
			.clickable { onClick() }
	) {
		UserImageCard(
			imageUrl = imageUrl,
			modifier = Modifier
				.padding(8.dp)
				.size(80.dp)
		)
		Card(
			shape = CircleShape,
			border = BorderStroke(width = 2.dp, color = Color.White),
			modifier = Modifier
				.size(32.dp)
				.align(Alignment.BottomEnd)
				.padding(bottom = 8.dp, end = 8.dp)
		) {
			Image(
				painter = painterResource(id = R.drawable.ic_add),
				contentDescription = null,
				modifier = Modifier.background(Color.Blue),
				colorFilter = ColorFilter.tint(Color.White)
			)
		}
	}
}