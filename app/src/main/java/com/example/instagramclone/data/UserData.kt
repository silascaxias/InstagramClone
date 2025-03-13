package com.example.instagramclone.data

/**
 * UserData
 * InstagramClone
 *
 ** Created by Silas S. Caxias on 3/13/2025.
 *
 **/

data class UserData(
	var userId: String? = null,
	var name: String? = null,
	var username: String? = null,
	var imageUrl: String? = null,
	var bio: String? = null,
	var following: List<String>? = null,
) {
	fun toMap() = mapOf(
		"userId" to userId,
		"name" to name,
		"username" to username,
		"imageUrl" to imageUrl,
		"bio" to bio,
		"following" to following,
	)
}