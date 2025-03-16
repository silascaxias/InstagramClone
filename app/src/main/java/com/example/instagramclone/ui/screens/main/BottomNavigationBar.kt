package com.example.instagramclone.ui.screens.main

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.instagramclone.R
import com.example.instagramclone.ui.theme.InstagramCloneTheme
import com.example.instagramclone.ui.navigation.AppScreen

/**
 * BottomNavigationMenu
 * InstagramClone
 *
 ** Created by Silas S. Caxias on 3/14/2025.
 *
 **/

enum class BottomNavigationItem(
	val route: AppScreen, val icon: Int
) {
	FEED(AppScreen.Main.FEED, R.drawable.ic_home),
	SEARCH(AppScreen.Main.SEARCH, R.drawable.ic_search),
	PROFILE(AppScreen.Main.PROFILE, R.drawable.ic_person),
}

@Composable
fun BottomNavigationBar(
	selectedItem: BottomNavigationItem,
	navigateToDestination: (AppScreen) -> Unit,
) {
	Row(
		modifier = Modifier
			.fillMaxWidth()
			.wrapContentHeight()
			.padding(top = 4.dp, bottom = 4.dp)
			.background(MaterialTheme.colorScheme.background)
	) {
		BottomNavigationItem.entries.forEach { item ->
			Image(
				painter = painterResource(id = item.icon),
				contentDescription = item.name,
				modifier = Modifier
					.size(40.dp)
					.padding(5.dp)
					.weight(1f)
					.clickable {
						navigateToDestination(item.route)
					},
				colorFilter = navigationItemColorFilter(item == selectedItem)
			)
		}
	}
}

@Composable
private fun navigationItemColorFilter(isSelectedItem: Boolean): ColorFilter {
	return if (isSelectedItem)
		ColorFilter.tint(MaterialTheme.colorScheme.primary) else ColorFilter.tint(
		MaterialTheme.colorScheme.secondary
	)
}

@Preview(showBackground = true)
@Composable
fun BottomNavigationBarPreview() {
	InstagramCloneTheme {
		BottomNavigationBar(
			selectedItem = BottomNavigationItem.FEED,
			navigateToDestination = {}
		)
	}
}