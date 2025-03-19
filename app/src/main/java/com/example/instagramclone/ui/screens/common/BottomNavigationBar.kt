package com.example.instagramclone.ui.screens.common

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.instagramclone.R
import com.example.instagramclone.ui.navigation.AppScreen
import com.example.instagramclone.ui.theme.InstagramCloneTheme

/**
 * BottomNavigationBar
 * InstagramClone
 *
 ** Created by Silas S. Caxias on 3/18/2025.
 *
 **/

enum class BottomNavigationItem(
	val route: AppScreen, val filledIcon: Int, val outlineIcon: Int
) {
	FEED(AppScreen.Main.Feed, R.drawable.ic_home_filled, R.drawable.ic_home_outlined),
	SEARCH(AppScreen.Main.Search, R.drawable.ic_search_filled, R.drawable.ic_search_outlined),
	PROFILE(AppScreen.Main.Profile, R.drawable.ic_profile_filled, R.drawable.ic_profile_outlined),
}

@Composable
fun BottomNavigationBar(
	selectedItem: BottomNavigationItem,
	navigateToDestination: (AppScreen) -> Unit,
) {
	Column(modifier = Modifier.wrapContentHeight()) {
		CommonDivider(width = 0.5.dp, modifier = Modifier
			.alpha(0.3f)
			.padding(bottom = 8.dp))
		Row(
			modifier = Modifier.Companion
				.fillMaxWidth()
				.wrapContentHeight()
		) {
			BottomNavigationItem.entries.forEach { item ->
				Image(
					painter = painterResource(id = navigationItemIcon(item, selectedItem == item)),
					contentDescription = item.name,
					modifier = Modifier.Companion
						.size(40.dp)
						.padding(5.dp)
						.weight(1f)
						.clickable (
							interactionSource = remember { MutableInteractionSource() },
							indication = null
						){
							navigateToDestination(item.route)
						},
					colorFilter = ColorFilter.Companion.tint(MaterialTheme.colorScheme.onBackground)
				)
			}
		}
	}
}

@Composable
private fun navigationItemIcon(item: BottomNavigationItem, isSelectedItem: Boolean): Int {
	return if (isSelectedItem) item.filledIcon else item.outlineIcon
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