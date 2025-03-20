package com.example.instagramclone.ui.screens.common

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Person
import androidx.compose.material.icons.outlined.Search
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.graphics.vector.rememberVectorPainter
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
	val route: AppScreen, val filledIcon: ImageVector, val outlineIcon: ImageVector
) {
	FEED(AppScreen.Main.Feed, Icons.Filled.Home, Icons.Outlined.Home),
	SEARCH(AppScreen.Main.Search, Icons.Filled.Search, Icons.Outlined.Search),
	PROFILE(AppScreen.Main.Profile, Icons.Filled.Person, Icons.Outlined.Person),
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
					painter = rememberVectorPainter(image = navigationItemIcon(item, selectedItem == item)),
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
private fun navigationItemIcon(item: BottomNavigationItem, isSelectedItem: Boolean): ImageVector {
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