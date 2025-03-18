package com.example.instagramclone.data.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey

/**
 * UserData
 * InstagramClone
 *
 ** Created by Silas S. Caxias on 3/13/2025.
 *
 **/

@Entity(
	tableName = "users",
	indices = [
		Index(value = ["username"], unique = true),
		Index(value = ["email"], unique = true)
	]
)
data class User (
	@PrimaryKey(true)
	var id: Int = 0,
	var name: String,
	var username: String,
	var email: String,
	var password: String,
	@ColumnInfo(name = "image_url")
	var imageUrl: String? = null,
	var bio: String? = null,
)