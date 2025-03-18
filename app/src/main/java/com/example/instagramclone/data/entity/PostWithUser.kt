package com.example.instagramclone.data.entity

import androidx.room.Embedded
import androidx.room.Relation

/**
 * PostWithUser
 * InstagramClone
 *
 ** Created by Silas S. Caxias on 3/18/2025.
 *
 **/

data class PostWithUser(
	@Embedded val post: Post,
	@Relation(
		parentColumn = "userId",
		entityColumn = "id"
	)
	val user: User
)
