package com.example.instagramclone.ui.screens.common

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Favorite
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.instagramclone.R
import com.example.instagramclone.data.entity.Post
import com.example.instagramclone.data.entity.PostWithUser
import com.example.instagramclone.data.entity.User
import com.example.instagramclone.ui.theme.InstagramCloneTheme
import com.example.instagramclone.utils.Utils.Companion.shareImage
import java.io.File
import java.text.DateFormat

/**
 * LargePostItem
 * InstagramClone
 *
 ** Created by Silas S. Caxias on 3/19/2025.
 *
 **/

@Composable
fun LargePostItem(
    currentUser: User?,
    postWithUser: PostWithUser,
    onDelete: () -> Unit = {},
    modifier: Modifier = Modifier
) {
    val (post, user) = postWithUser
    val context = LocalContext.current

    Column(
        modifier = modifier
    ) {
        Row(
            modifier = Modifier
                .padding(end = 12.dp)
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                ProfileImage(
                    imageUrl = user.imageUrl,
                    width = 48.dp,
                    showAddIcon = false
                )

                Text(text = "@${user.username}", modifier = Modifier.padding(bottom = 4.dp))
            }

            if (currentUser?.id != postWithUser.user.id) {
                OutlinedButton(
                    onClick = {},
                    shape = RoundedCornerShape(24)
                ) {
                    Text(text = "Follow")
                }
            } else {
                MinimalDropdownMenu(
                    options = listOf(
                        Pair("Delete") { onDelete() }
                    )
                )
            }
        }

        Row(modifier = Modifier.wrapContentHeight()) {
            CommonImage(
                modifier = Modifier
                    .fillMaxWidth()
                    .defaultMinSize(minHeight = 150.dp),
                data = post.imageUrl,
                contentScale = ContentScale.FillWidth
            )
        }

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(12.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Row(
                horizontalArrangement = Arrangement.spacedBy(8.dp),
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(5.dp)
                ) {
                    Image(
                        painter = rememberVectorPainter(Icons.Outlined.Favorite),
                        contentDescription = "Like",
                        colorFilter = ColorFilter.tint(MaterialTheme.colorScheme.primary),
                        modifier = Modifier.size(28.dp)
                    )
                    Text(text = "100", color = MaterialTheme.colorScheme.primary)
                }
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(5.dp)
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.ic_comments),
                        contentDescription = "Comment",
                        colorFilter = ColorFilter.tint(MaterialTheme.colorScheme.primary),
                        modifier = Modifier.size(28.dp)
                    )
                    Text(text = "5", color = MaterialTheme.colorScheme.primary)
                }
                Image(
                    painter = painterResource(id = R.drawable.ic_send),
                    contentDescription = "Share",
                    colorFilter = ColorFilter.tint(MaterialTheme.colorScheme.primary),
                    modifier = Modifier
                        .size(28.dp)
                        .clickable {
                            shareImage(context = context, imageFile = File(post.imageUrl))
                        }
                )
            }
            Image(
                painter = painterResource(id = R.drawable.ic_save),
                contentDescription = "Save Post",
                colorFilter = ColorFilter.tint(MaterialTheme.colorScheme.primary),
                modifier = Modifier.size(28.dp)
            )
        }

        Row(modifier = Modifier.padding(horizontal = 12.dp)) {
            Text(
                text = buildAnnotatedString {
                    withStyle(style = SpanStyle(fontWeight = FontWeight.Bold)) {
                        append("${user.username} ")
                    }
                    append(post.description)
                }
            )
        }
        val dateFormat = DateFormat.getDateInstance().format(post.timestamp)
        Row(modifier = Modifier.padding(horizontal = 12.dp)) {
            Text(
                text = dateFormat,
                color = MaterialTheme.colorScheme.tertiary,
                fontSize = 12.sp,
                fontWeight = FontWeight.Bold
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun LargePostItemPreview() {
    val firstUser = User(
        name = "Test",
        username = "test",
        imageUrl = null,
        id = 1,
        email = "test@gmail.com",
        password = "123456",
        bio = "This is my bio."
    )
    val secondUser = User(
        name = "Test",
        username = "test",
        imageUrl = null,
        id = 1,
        email = "test@gmail.com",
        password = "123456",
        bio = "This is my bio."
    )
    InstagramCloneTheme {
        LargePostItem(
            postWithUser = PostWithUser(
                post = Post(
                    imageUrl = "",
                    id = 0,
                    userId = 2,
                    description = "This is a description.",
                    timestamp = 1742400286570
                ),
                user = firstUser
            ),
            currentUser = secondUser
        )
    }
}

@Composable
fun MinimalDropdownMenu(
    options: List<Pair<String, () -> Unit>>
) {
    var expanded by remember { mutableStateOf(false) }

    Box(
        modifier = Modifier
    ) {
        IconButton(onClick = { expanded = !expanded }) {
            Image(
                painter = painterResource(R.drawable.ic_dots_menu),
                contentDescription = "Menu",
                colorFilter = ColorFilter.tint(MaterialTheme.colorScheme.primary),
                modifier = Modifier.size(28.dp)
            )
        }
        DropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false }
        ) {
            options.forEach {
                DropdownMenuItem(
                    text = { Text(it.first) },
                    onClick = {
                        it.second()
                        expanded = false
                    }
                )
                if (it != options.last()) {
                    HorizontalDivider()
                }
            }
        }
    }
}