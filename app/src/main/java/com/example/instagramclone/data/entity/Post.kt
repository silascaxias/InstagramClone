package com.example.instagramclone.data.entity

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey

/**
 * Post
 * InstagramClone
 *
 ** Created by Silas S. Caxias on 3/18/2025.
 *
 **/

@Entity(
	tableName = "posts",
	foreignKeys = [
		ForeignKey(
			entity = User::class,
			parentColumns = ["id"],
			childColumns = ["userId"],
			onDelete = ForeignKey.CASCADE
		)
	],
	indices = [
		Index("userId")
	]
)
data class Post(
	@PrimaryKey(true)
	var id: Int = 0,
	var userId: Int,
	var imageUrl: String,
	var description: String,
	var timestamp: Long = System.currentTimeMillis()
)